package com.huangyiming.disjob.java.listener;

import com.huangyiming.disjob.event.ObjectEvent;
import com.huangyiming.disjob.event.ObjectListener;
import com.huangyiming.disjob.java.EjobConstants;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.service.StartUpService;

public class EjobPublishJobListener implements ObjectListener<StartUpConfig>{

	public void onEvent(ObjectEvent<StartUpConfig> event) {
		if(StartUpService.isInitSuccess == false){
			return ;
		}
		
		if(event.getValue().getType() != EjobConstants.StartUpType.SPRING_START_UP){
			StartUpService.initJobScanner();//不是spring 的需要扫描 job 所在的包发布job
		}
	}

}
