package com.huangyiming.disjob.java.listener;

import com.huangyiming.disjob.event.ObjectEvent;
import com.huangyiming.disjob.event.ObjectListener;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.event.EjobStartUp;
import com.huangyiming.disjob.java.event.EventType;

public class EjobStartFinishListener implements ObjectListener<StartUpConfig> {
	
	private EjobStartUp ejobStartUp ;
	
	public EjobStartFinishListener(EjobStartUp ejobStartUp) {
		this.ejobStartUp = ejobStartUp ;
	}
	
	public void onEvent(ObjectEvent<StartUpConfig> event) {
		this.ejobStartUp.setCurrentState(EjobStartUp.StartUpState.STARTUP_FINISH);
		this.ejobStartUp.removeListener(EventType.START_FINISH);
		this.ejobStartUp.removeListener(EventType.START_UP);
	}
}
