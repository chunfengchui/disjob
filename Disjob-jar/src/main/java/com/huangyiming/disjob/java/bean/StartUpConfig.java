package com.huangyiming.disjob.java.bean;

import java.io.Serializable;

import com.huangyiming.disjob.java.EjobConstants;
import com.huangyiming.disjob.java.service.EjobConfigService;

public class StartUpConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ejobConfigPath ;
	
	private String log4jProperties;
	
	private short type ;//1 or 2 or 3 
	
	public StartUpConfig(short type) {
		this.type = type ;
		this.log4jProperties = StartUpConfig.class.getClassLoader().getResource("META-INF/log4j.properties").getPath();
		EjobConfigService.configProperties.setProperty(EjobConstants.StartUpType.START_UP_TYPE, String.valueOf(type));
	}
	/**
	 * 如果不用spring,就是用我们默认提供的配置文件
	 * @param type
	 * @param ejobConfigPath
	 */
	public StartUpConfig(short type , String ejobConfigPath) {
		this(type);
		this.ejobConfigPath = ejobConfigPath;
	}

	public String getEjobConfigPath() {
		return ejobConfigPath;
	}

	public void setEjobConfigPath(String ejobConfigPath) {
		this.ejobConfigPath = ejobConfigPath;
	}

	public String getLog4jProperties() {
		return log4jProperties;
	}

	public void setLog4jProperties(String log4jProperties) {
		this.log4jProperties = log4jProperties;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	
}
