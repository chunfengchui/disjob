package com.huangyiming.disjob.java.event;

import com.huangyiming.disjob.java.ExecutorBuilder;
import com.huangyiming.disjob.java.job.RegisterEJobAction;
import com.huangyiming.disjob.quence.ActionQueue;
import com.huangyiming.disjob.quence.BaseActionQueue;

public class GroupRegisterTracker {

	private ActionQueue groupRegisterQueue = new BaseActionQueue(ExecutorBuilder.getJobExecutor());
	
	public void enqueue(RegisterEJobAction registerEJobAction){
		groupRegisterQueue.enqueue(registerEJobAction);
	}
}
