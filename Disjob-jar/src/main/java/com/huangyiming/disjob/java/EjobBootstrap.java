package com.huangyiming.disjob.java;

import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.core.dispatcher.EventObjectDispatcher;

/**
 * 
 * @author Disjob
 *
 */
public class EjobBootstrap {

	/**
	 * @param startUpConfig
	 */
	public void startUpEjob(StartUpConfig startUpConfig){
		
		EventObjectDispatcher.dispatcherEjobStartUp(startUpConfig);
	}
	
	/**
	 * 
	 * @param ejobConfigPath
	 * @param log4jProperties
	 */
	public void startUpEjob(short type,String ejobConfigPath){
		
		this.startUpEjob(new StartUpConfig(type,ejobConfigPath));
	}
	
	/**
	 * 如果不是一个spring 项目，则使用我们的ejob 配置文件来配置相关的参数，
	 * @param ejobConfigPath 配置文件的绝对路径
	 */
	public void webServletStartUpEjob(String ejobConfigPath){
		
		EventObjectDispatcher.dispatcherEjobStartUp(new StartUpConfig(EjobConstants.StartUpType.WEB_SERVLET_START_UP,ejobConfigPath));
	}
	
	public static void main(String[] args) {
		String ejob = "E:/__work_space_middleware/EJob1.0Fixed/ejob-jar/src/main/resources/META-INF/ejob.properties";
		
		new EjobBootstrap().startUpEjob(new StartUpConfig(EjobConstants.StartUpType.JAVA_APPLICATION,ejob));
	}
}
