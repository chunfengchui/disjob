package com.huangyiming.disjob.java.event;

import java.util.concurrent.locks.ReentrantLock;

import com.huangyiming.disjob.event.AbstractEventObject;
import com.huangyiming.disjob.event.ObjectEvent;
import com.huangyiming.disjob.java.EjobConstants;
import com.huangyiming.disjob.java.ProviderClassName;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.listener.EjobInitConfigListener;
import com.huangyiming.disjob.java.listener.EjobInitServerListener;
import com.huangyiming.disjob.java.listener.EjobPublishJobListener;
import com.huangyiming.disjob.java.listener.EjobSchedulerServerCheckListener;
import com.huangyiming.disjob.java.listener.EjobStartFinishListener;
import com.huangyiming.disjob.java.listener.EjobStartUpListener;
import com.huangyiming.disjob.java.listener.EjobStopListener;
import com.huangyiming.disjob.java.service.EjobConfigService;
import com.huangyiming.disjob.java.utils.Log;

public class EjobStartUp extends AbstractEventObject<StartUpConfig> implements ProviderClassName{
	
	public static enum StartUpState{
		UNSTARTUP("未开启"),
		STARTING("正在开启"),
		STARTUP_FINISH("开启完成") ;
		private String desc ;
		StartUpState(String desc){
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	};//未开启，正在开启，开启完成
	
	private StartUpState currentState = StartUpState.UNSTARTUP; //set the default status
	private ReentrantLock lock = new ReentrantLock();
	private long start = 0;
	public EjobStartUp() {
		start = System.currentTimeMillis();
	}
	
	/**
	 * whether startup by spring or context listener ,this method can only be called once
	 * @param startUpConfig
	 */
	public void notifyStartUp(StartUpConfig startUpConfig){
		boolean isCanStartUp = true ;
		lock.lock();
		try{
			if(this.currentState == StartUpState.UNSTARTUP){
				this.currentState = StartUpState.STARTING;
			}else{
				isCanStartUp = false ;
			}
		}finally{
			lock.unlock();
		}
		if(isCanStartUp){
			EjobConfigService.configProperties.setProperty(EjobConstants.StartUpType.START_UP_TYPE, String.valueOf(startUpConfig.getType()));
			ObjectEvent<StartUpConfig> startEvent = new ObjectEvent<StartUpConfig>(startUpConfig,EventType.START_UP);
			this.notifyListeners(startEvent);
		}
	}
	
	/**
	 * 
	 */
	public void notifyStaUpFinish(){
		ObjectEvent<StartUpConfig> finishEvent = new ObjectEvent<StartUpConfig>(null, EventType.START_FINISH);
		this.notifyListeners(finishEvent);
	}
	
	/**
	 * 
	 */
	public void notifyStop() {
		ObjectEvent<StartUpConfig> stopEvent = new ObjectEvent<StartUpConfig>(null, EventType.EJOB_STOP);
		this.notifyListeners(stopEvent);
	}
	
	public String getClassName(){
		
		return this.getClass().getName();
	}

	public StartUpState getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(StartUpState currentState) {
		lock.lock();
		try{
			this.currentState = currentState;
			if(this.currentState == StartUpState.STARTUP_FINISH){
				Log.debug(getClassName() + " ejob start success has take time:"+(System.currentTimeMillis()-start)/1000 + " s");
			}
		}finally{
			lock.unlock();
		}
	}

	/**
	 * if you have some listeners then attach these in here 
	 */
	@Override
	public void attachListener() {
		this.addListener(new EjobInitConfigListener(), EventType.START_UP);
		this.addListener(new EjobSchedulerServerCheckListener(), EventType.START_UP);
		this.addListener(new EjobInitServerListener(), EventType.START_UP);
		this.addListener(new EjobPublishJobListener(), EventType.START_UP);
		this.addListener(new EjobStartUpListener(), EventType.START_UP);
		
		this.addListener(new EjobStopListener(this), EventType.EJOB_STOP);
		this.addListener(new EjobStartFinishListener(this), EventType.START_FINISH);
	}
}
