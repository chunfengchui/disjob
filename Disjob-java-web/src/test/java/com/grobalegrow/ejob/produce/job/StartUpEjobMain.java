package com.grobalegrow.ejob.produce.job;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUpEjobMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"ejob.xml"});
		context.start();
	}

}
     