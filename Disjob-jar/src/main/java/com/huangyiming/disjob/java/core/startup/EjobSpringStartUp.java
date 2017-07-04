package com.huangyiming.disjob.java.core.startup;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.huangyiming.disjob.java.EjobConstants;
import com.huangyiming.disjob.java.ProviderClassName;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.core.dispatcher.EventObjectDispatcher;
import com.huangyiming.disjob.java.service.EjobConfigService;
import com.huangyiming.disjob.java.spring.SpringWorkFactory;

public class EjobSpringStartUp implements ProviderClassName,ApplicationContextAware {
	private String zkhost;
	private String serverport;
	private String zkrootnode ;
	private String clustername;
	
	private String corePoolSize;
	private String maxPoolSize;
	private String keepAliveTime ;
	
	public String getClassName(){
		
		return this.getClass().getName();
	}
	
	public void destroy() {
		EventObjectDispatcher.dispatcherEjobStop();
	}

	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		SpringWorkFactory.getInstance().setApplicationContext(applicationContext);
		EventObjectDispatcher.dispatcherEjobStartUp(new StartUpConfig(EjobConstants.StartUpType.SPRING_START_UP));
	}

	public String getZkhost() {
		return zkhost;
	}

	public void setZkhost(String zkhost) {
		this.zkhost = zkhost;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.ZK_HOST, zkhost);
	}

	public String getServerport() {
		return serverport;
	}

	public void setServerport(String serverport) {
		this.serverport = serverport;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.SERVER_PORT, serverport);
	}

	public String getZkrootnode() {
		return zkrootnode;
	}

	public void setZkrootnode(String zkrootnode) {
		this.zkrootnode = zkrootnode;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.ZK_ROOT_NODE, zkrootnode);
	}

	public String getClustername() {
		return clustername;
	}

	public void setClustername(String clustername) {
		this.clustername = clustername;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.EJOB_CLUSTER_NAME, clustername);
	}

	public String getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(String corePoolSize) {
		this.corePoolSize = corePoolSize;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.THREAD_COREPOOL_SIZE, corePoolSize);
	}

	public String getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(String maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.THREAD_MAXPOOL_SIZE, maxPoolSize);
	}

	public String getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(String keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
		EjobConfigService.configProperties.setProperty(EjobConstants.Config.THREAD_KEEPALIVE_TIME, keepAliveTime);
	}
	
}
