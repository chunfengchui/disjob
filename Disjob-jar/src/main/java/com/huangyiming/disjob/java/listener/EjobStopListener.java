package com.huangyiming.disjob.java.listener;

import com.huangyiming.disjob.event.ObjectEvent;
import com.huangyiming.disjob.event.ObjectListener;
import com.huangyiming.disjob.java.ExecutorBuilder;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.event.EjobStartUp;
import com.huangyiming.disjob.java.service.EjobConfigService;

public class EjobStopListener implements ObjectListener<StartUpConfig>{
	
	private EjobStartUp ejobStartUp ;
	public EjobStopListener(EjobStartUp ejobStartUp) {
		this.ejobStartUp = ejobStartUp ;
	}
	public void onEvent(ObjectEvent<StartUpConfig> event) {
		EjobConfigService.destory();
		ExecutorBuilder.getJobExecutor().stop();
		this.ejobStartUp.clearListener();
	}
}
