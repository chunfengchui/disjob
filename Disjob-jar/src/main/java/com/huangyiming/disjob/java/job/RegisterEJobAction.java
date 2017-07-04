package com.huangyiming.disjob.java.job;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.huangyiming.disjob.java.core.dispatcher.EventObjectDispatcher;
import com.huangyiming.disjob.java.event.EjobStartUp;
import com.huangyiming.disjob.java.service.StartUpService;
import com.huangyiming.disjob.java.utils.Log;
import com.huangyiming.disjob.quence.Action;
import com.huangyiming.disjob.quence.ActionQueue;
import com.huangyiming.disjob.quence.TaskExecuteException;

public class RegisterEJobAction extends Action{
	private RegisterEJob registerEJob ;
	private final static AtomicInteger jobCount = new AtomicInteger();
	public RegisterEJobAction(RegisterEJob registerEJob,ActionQueue actionQueue) {
		super(actionQueue);
		this.registerEJob = registerEJob ;
	}
	@Override
	public void execute() throws TaskExecuteException {
		
		EjobStartUp ejobStartUp = EventObjectDispatcher.getEjobStartUp();
		while(ejobStartUp.getCurrentState()!=EjobStartUp.StartUpState.STARTUP_FINISH){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(StartUpService.isInitSuccess == false){
			return ;
		}
		
		boolean flag = this.registerEJob.createJob();
		if(!flag){
			System.err.println(this.registerEJob.getJobInfo().toString()+" 注册失败!");
			Log.error(this.registerEJob.getJobInfo().toString()+" 注册失败!");
		}else{
			System.out.println(this.registerEJob.getJobInfo().toString()+" 注册成功,第："+jobCount.incrementAndGet()+" 个job .");
			Log.debug(this.registerEJob.getJobInfo().toString()+" 注册成功");
		}
	}

}
