package com.huangyiming.disjob.java.core.dispatcher;

import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.event.DynamicJobStartUp;
import com.huangyiming.disjob.java.event.EjobStartUp;
import com.huangyiming.disjob.java.event.SpringRegisterJob;

public final class EventObjectDispatcher {

	private static final EjobStartUp EJOBSTA_EJOB_START_UP = new EjobStartUp();
	
	public static EjobStartUp getEjobStartUp(){
		return EJOBSTA_EJOB_START_UP ;
	}
	
	public static void dispatcherEjobStartUp(StartUpConfig startUpConfig) {

		EJOBSTA_EJOB_START_UP.notifyStartUp(startUpConfig);
	}

	public static void dispatcherEjobStop() {

		EJOBSTA_EJOB_START_UP.notifyStop();
	}

	public static void dispatcherEjobStartUpFinish(){
		
		EJOBSTA_EJOB_START_UP.notifyStaUpFinish();
	}
	
	private static final DynamicJobStartUp DYNAMIC_JOB = new DynamicJobStartUp();
	public static void dispatcherDynamicJob(String fileName){

		DYNAMIC_JOB.notifyDynamicJob(fileName);
	}
	
	private static final SpringRegisterJob SPRING_REGISTER_JOB =  new SpringRegisterJob();
	public static void dispatcherSpringRegisterJob(String className){
		SPRING_REGISTER_JOB.notify(className);
	}
}
