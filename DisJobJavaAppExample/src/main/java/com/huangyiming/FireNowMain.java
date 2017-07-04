package com.huangyiming;

import com.huangyiming.disjob.java.EjobBootstrap;
import com.huangyiming.disjob.java.EjobConstants;

public class FireNowMain {

	public static void main(String[] args) {
		String path = "E:/__work_space_middleware/EJob1.0Fixed/EjobJavaApp/src/main/resources/META-INF/ejob.properties";
		new EjobBootstrap().startUpEjob(EjobConstants.StartUpType.JAVA_APPLICATION, path);
	}
}
