package com.huangyiming.disjob.monitor.rms.util;

import com.huangyiming.disjob.monitor.util.MonitorSpringWorkFactory;


public class RMSPropertityConfigUtil {
	
	public static boolean ifUseRMSMonitor(){
		return Boolean.valueOf(MonitorSpringWorkFactory.getRMSConfig().getRmsMonitor());
	}
	public static String getMonitorUrl() {
		return MonitorSpringWorkFactory.getRMSConfig().getMonitorurl();
	}
	public static String getEjobProjectCode() {
		return MonitorSpringWorkFactory.getRMSConfig().getEjobProjectCode();
	}
	public static int getSelfTestIntervals() {
		return Integer.parseInt(MonitorSpringWorkFactory.getRMSConfig().getIntervals());
	}
	public static boolean availableSelfTest(){
		return Boolean.parseBoolean(MonitorSpringWorkFactory.getRMSConfig().getSelfTest());
	}
	
}
