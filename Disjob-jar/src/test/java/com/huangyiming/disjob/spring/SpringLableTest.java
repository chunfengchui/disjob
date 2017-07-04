package com.huangyiming.disjob.spring;
/*package com.huangyiming.disjob.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.huangyiming.disjob.java.job.EJob;
import com.huangyiming.disjob.java.utils.SpringWorkFactory;
import com.huangyiming.disjob.quence.TaskExecuteException;

public class SpringLableTest {

	public static void main(String[] args) {
		String conf = "classpath:META-INF/ejob-spring.xml";
		FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext(conf);
		ExecutorInfo executorInfo = ac.getBean(ExecutorInfo.class);
		System.out.println(executorInfo.toString());
		EJob ejob = (EJob) ac.getBean("com.huangyiming.disjob.java.job.SpringLabelJob");
		System.out.println(SpringWorkFactory.getApplicationContext());
		try {
			ejob.execute();
		} catch (TaskExecuteException e) {
			e.printStackTrace();
		}
	}
}
=======
package com.huangyiming.disjob.spring;
/*package com.huangyiming.disjob.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.huangyiming.disjob.java.job.EJob;
import com.huangyiming.disjob.java.utils.SpringWorkFactory;
import com.huangyiming.disjob.quence.TaskExecuteException;

public class SpringLableTest {

	public static void main(String[] args) {
		String conf = "classpath:META-INF/ejob-spring.xml";
		FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext(conf);
		ExecutorInfo executorInfo = ac.getBean(ExecutorInfo.class);
		System.out.println(executorInfo.toString());
		EJob ejob = (EJob) ac.getBean("com.huangyiming.disjob.java.job.SpringLabelJob");
		System.out.println(SpringWorkFactory.getApplicationContext());
		try {
			ejob.execute();
		} catch (TaskExecuteException e) {
			e.printStackTrace();
		}
	}
}
>>>>>>> 31756b5772b8cccc509feda8167e5dd74f23142f:Ejob-jar/src/test/java/com/huangyiming/disjob/spring/SpringLableTest.java
*/