package com.huangyiming.disjob.java.app.alarm;

import com.huangyiming.disjob.java.EjobBootstrap;
import com.huangyiming.disjob.java.EjobConstants;

public class FireNowMain {

	public static void main(String[] args) {
		String path = "F:/project/EjobJavaApp/EjobJavaApp/src/main/resources/META-INF/ejob.properties";
		new EjobBootstrap().startUpEjob(EjobConstants.StartUpType.JAVA_APPLICATION, path);
	}
}
