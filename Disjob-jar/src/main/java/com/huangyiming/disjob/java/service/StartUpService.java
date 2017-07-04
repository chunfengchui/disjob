package com.huangyiming.disjob.java.service;

import java.util.concurrent.CountDownLatch;
import org.apache.log4j.PropertyConfigurator;
import com.huangyiming.disjob.java.CuratorClientBuilder;
import com.huangyiming.disjob.java.ExecutorBuilder;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.core.rpc.JobExecutorServer;
import com.huangyiming.disjob.java.dynamic.WatchServiceReactor;
import com.huangyiming.disjob.java.job.JobInitScanner;
import com.huangyiming.disjob.java.utils.Log;
import com.huangyiming.disjob.java.utils.StringUtils;

public class StartUpService {
	
	public volatile static boolean isInitSuccess = true ;
	
	public static void startup(StartUpConfig startUpConfig){
		initLog4j(startUpConfig.getLog4jProperties());
		initEjobConfig(startUpConfig.getEjobConfigPath());
		initCurator();
		initJobScanner();
		//initDynamicJobDirWatcher();
	}
	
	public static void initLog4j(String log4jProperties) {
		if(StringUtils.isEmpty(log4jProperties)){
			return ;
		}
		PropertyConfigurator.configure(log4jProperties);
	}
	
	public static void initEjobConfig(String ejobConfigPath) {
		if(StringUtils.isEmpty(ejobConfigPath)){
			Log.error(getClassName()+"[ spring start up ] the ejob config path is empty,please config the 'ejobConfigPath' paramter");
			try {
				throw new IllegalArgumentException("[ spring start up ] the ejob config path is empty,please config the 'ejobConfigPath' paramter");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			return ;
		}
		EjobConfigService.init(ejobConfigPath);
	}
	
	public static void initCurator(){
		String zkhost = EjobConfigService.getZkHost();
		if(StringUtils.isEmpty(zkhost)){
			throw new RuntimeException(getClassName() + "; start ejob fail because the config of zkhost is null.");
		}
		String zkrootnode = EjobConfigService.getZKRootNode();
		CuratorClientBuilder.initCurator(zkhost, zkrootnode);
	}
	
	public static void initJobScanner(){
		
		ExecutorBuilder.getJobExecutor().execute(new JobInitScanner());
	}
	
	public static CountDownLatch initExecutorServer(){
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Thread thread = new Thread(new JobExecutorServer(countDownLatch),"thread-netty-server");
		thread.setDaemon(true);
		thread.start();
		return countDownLatch;
	}
	
	public static void initDynamicJobDirWatcher(){
		Thread thread = new Thread(new WatchServiceReactor(EjobConfigService.getDynamicDir()),"thread-dynamic-job");
		thread.setDaemon(true);
		thread.start();
	}
	
	public static String getClassName() {
		return StartUpService.class.getName();
	}
	
	public static boolean check(String ejobConfigPath, String log4jProperties){
		if(StringUtils.isEmpty(ejobConfigPath) & StringUtils.isEmpty(log4jProperties)){
			Log.error(getClassName()+ " ejob config parameters is empty.");
			return false;
		}
		
		if(ejobConfigPath.equals(log4jProperties)){
			Log.error(getClassName()+" ejob config is equal to log4j config.");
			return false;
		}
		
		return true ;
	}
}
