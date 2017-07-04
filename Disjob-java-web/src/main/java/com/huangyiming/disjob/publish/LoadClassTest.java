package com.huangyiming.disjob.publish;
//package com.globalgrow.ejob.publish;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.util.List;
//
//import com.huangyiming.disjob.java.bean.JobInfo;
//import com.huangyiming.disjob.java.job.EJob;
//import com.huangyiming.disjob.java.job.JobInitScanner;
//import com.huangyiming.disjob.java.utils.ClasspathPackageScanner;
//
//public class LoadClassTest {
//  
//	public static void main(String[] args) {
//		try {  
//            //第一种  配置成文件格式  
////            testOne();  
//            //第二种  
////			loadDynamic(); 
//			getAllClassFromJar();
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }
//	
//	private static void getAllClassFromJar() throws IOException, Exception{
////		String src = "com.globalgrow.ejob" ;
//		JobInitScanner jobInitScanner = new JobInitScanner();
//		String src = "F:/__my_src/read.jar" ;
//		URL url1 = new URL("file:"+src);
//		URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, Thread.currentThread().getContextClassLoader());
//		List<String> classNames = new ClasspathPackageScanner(src).getClassNameList();
//		for(String cn:classNames){
//			System.out.println(cn);
//			Class<? extends EJob> clazz = (Class<? extends EJob>) myClassLoader1.loadClass(cn);
//			System.out.println(jobInitScanner.getJobInfo(clazz));
//		}
//	}
//	
//	private static void loadDynamic() throws Exception{
//		JobInitScanner jobInitScanner = new JobInitScanner();
//		String dynamicJob_1 = "com.globalgrow.ejob.dynamic.DynamicJobOne";
//		String dynamicJob_2 = "com.globalgrow.ejob.dynamic.DynamicJobTwo";
//		String src = "file:F:/__my_src/read.jar" ;
//		URL url1 = new URL("file:F:/__my_src/read.jar");  
//		URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, Thread.currentThread().getContextClassLoader());
//		Class<? extends EJob> jobOne = (Class<? extends EJob>) myClassLoader1.loadClass(dynamicJob_1);
//	
//		JobInfo jobInfo = jobInitScanner.getJobInfo(jobOne);
//		System.err.println(jobInfo);
//	}
//
//	private static void testTwo() throws MalformedURLException,
//			ClassNotFoundException, InstantiationException,
//			IllegalAccessException {
//		URL url1 = new URL("file:F:/__my_src/read.jar");  
//		URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, Thread.currentThread().getContextClassLoader());  
//		Class<? extends AbstractAction> myClass1 = (Class<? extends AbstractAction>) myClassLoader1.loadClass("com.globalgrow.ejob.publish.TestAction");  
//		AbstractAction action1 = (AbstractAction) myClass1.newInstance();  
//		String str1 = action1.action();  
//		System.out.println(str1);
//	}
//
//	private static void testOne() throws FileNotFoundException, IOException,
//			MalformedURLException, ClassNotFoundException,
//			InstantiationException, IllegalAccessException {
//		File file = new File("D:\\jarload\\test.txt");  
//		BufferedReader in = new BufferedReader(new FileReader(file));  
//		String s = new String();  
//		while ((s = in.readLine()) != null) {  
//  
//		    URL url = new URL(s);  
//		    s = null;  
//		      
//		    URLClassLoader myClassLoader = new URLClassLoader(new URL[] { url }, Thread.currentThread().getContextClassLoader());  
//		    Class<? extends AbstractAction> myClass = (Class<? extends AbstractAction>) myClassLoader.loadClass("com.java.jarloader.TestAction");  
//		    AbstractAction action = (AbstractAction) myClass.newInstance();  
//		    String str = action.action();  
//		    System.out.println(str);  
//		}
//	}  
//}
