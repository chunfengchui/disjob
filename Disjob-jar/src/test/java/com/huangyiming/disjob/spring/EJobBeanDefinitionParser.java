package com.huangyiming.disjob.spring;
/*package com.huangyiming.disjob.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.huangyiming.disjob.java.ExecutorBuilder;
import com.huangyiming.disjob.java.bean.JobInfo;
import com.huangyiming.disjob.java.job.RegisterEJob;
import com.huangyiming.disjob.java.job.RegisterEJobAction;
import com.huangyiming.disjob.java.utils.Log;

public class EJobBeanDefinitionParser implements BeanDefinitionParser {

	private Class<?> beanClass;

	private boolean required;

	public EJobBeanDefinitionParser(Class<?> beanClass, boolean required) {
		this.beanClass = beanClass;
		this.required = required;
	}
	private final static String EXECUTOR_INFO = "ejob:executor" ;
	private final static String EJOB_INFOR = "ejob:publish";
	
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		String id = null;
		String nodeName = element.getNodeName();
		if(EXECUTOR_INFO.equals(nodeName.trim())){
			id = element.getAttribute("name");
		}else if(EJOB_INFOR.equals(nodeName)){
			id = element.getAttribute("className");
		}else{
			
			throw new IllegalStateException("不支持的标签异常。");
		}
		
		if (id == null && required) {

			throw new IllegalStateException("MyBeanDefinitionParser.parse,未知的业务逻辑处理：class:" + element.getAttribute("class"));
		}
		if (id != null && id.length() > 0) {
			if (parserContext.getRegistry().containsBeanDefinition(id)) {
				throw new IllegalStateException("Duplicate spring bean id " + id);
			}
			parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		}
		NamedNodeMap nnm = element.getAttributes();
		if(EXECUTOR_INFO.equals(nodeName)){
			
			this.parserExecutor(nnm, beanDefinition);
		}else if(EJOB_INFOR.equals(nodeName)){
			boolean hasChildNodes = element.hasChildNodes();
			if(!hasChildNodes){//<ejob:publish className="***.***.***.**Job" groupName="global_1" jobName="printClassInfo" />
				JobInfo jobInfo = new JobInfo();
				for (int i = 0; i < nnm.getLength(); i++) {
					Node node = nnm.item(i);
					String key = node.getLocalName();
					String value = node.getNodeValue();
					if("className".equals(key)){
						jobInfo.setClassName(value);
					}else if("groupName".equals(key)){
						jobInfo.setGroupName(value);
					}else if("jobName".equals(key)){
						jobInfo.setJobName(value);
					}
				}
				System.out.println(jobInfo.toString());
				ExecutorBuilder.getJobExecutor().enDefaultQueue(new RegisterEJobAction(new RegisterEJob(jobInfo)));
			}else{
				NodeList nodeLists = element.getChildNodes();
				for(int i=0 ; i < nodeLists.getLength();i++){
					Node node = nodeLists.item(i);
					//1、start parse: <ejob:group value="springConfig"> 
					String ejobGroupNode = node.getNodeName() ;
					if(ejobGroupNode.equals("ejob:group")){
						NamedNodeMap groupValue = node.getAttributes() ;
						if(groupValue.getLength() != 1 ){
							throw new IllegalArgumentException(ejobGroupNode +" must to named.");
						}
						Node groupNode = groupValue.item(0);//只有一个数据节点
						String key = groupNode.getLocalName();
						String group = groupNode.getNodeValue() ;
						if(!"value".equals(key)){
							throw new IllegalArgumentException("the attribute of "+ ejobGroupNode +" must be value.");
						}
						
						//2、parse the node of ejob:group all of the children
						NodeList jobNodeLists = node.getChildNodes();
						if(jobNodeLists.getLength() < 1){
							Log.warn(group + " 下面没有配置相关的job");
							continue;
						}
						*//**
						 * 3、开始解析  ejob:job 节点
						 * <ejob:group value="springConfig">
								<ejob:job className="com.huangyiming.disjob.java.job.SpringLabelJobSec" jobName="labelJobSec" />
								<ejob:job className="com.huangyiming.disjob.java.job.SpringLabelJobThree" jobName="labelJobThree" />
						   </ejob:group>
						 *//*
						for(int x=0;x<jobNodeLists.getLength();x++){
							Node jobNode = jobNodeLists.item(x);
							String jobNodeName = jobNode.getNodeName();
							if(!"ejob:job".equals(jobNodeName)){
								throw new IllegalArgumentException("<ejob:group> 下面的子节点必须是以 <ejob:job>  开头。");
							}
							//4、<ejob:job> 解析 他的节点属性
							NamedNodeMap jobAttributes = jobNode.getAttributes();
							String className = null ;
							String jobName = null ;
							for(int y=0;y < jobAttributes.getLength();y++){
								Node jobAttributeNode = jobAttributes.item(y);
								String jobkey = jobAttributeNode.getLocalName();
								String jobValue = jobAttributeNode.getNodeValue();
								if("className".equals(jobkey)){
									className = jobValue;
									continue;
								}
								
								if("jobName".equals(jobkey)){
									jobName = jobValue ;
								}
							}
							
							if(className == null || jobName == null){
								throw new IllegalArgumentException(group + " 下面的第 "+(x+1)+" 个 <ejob:job>  没有配置 className or jobName。");
							}
							
							JobInfo jobInfo = new JobInfo(className, group, jobName);
							System.out.println(jobInfo.toString());
						}
						
					}else{
						System.err.println(ejobGroupNode);
					}
				}
			}
		}
		
		return beanDefinition;
	}
	
	private void parserExecutor(NamedNodeMap nodeMap,RootBeanDefinition beanDefinition){
		for (int i = 0; i < nodeMap.getLength(); i++) {
			Node node = nodeMap.item(i);
			String key = node.getLocalName();
			String value = node.getNodeValue();
			beanDefinition.getPropertyValues().add(key, value);
		}
	}
}
=======
package com.huangyiming.disjob.spring;
/*package com.huangyiming.disjob.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.huangyiming.disjob.java.ExecutorBuilder;
import com.huangyiming.disjob.java.bean.JobInfo;
import com.huangyiming.disjob.java.job.RegisterEJob;
import com.huangyiming.disjob.java.job.RegisterEJobAction;
import com.huangyiming.disjob.java.utils.Log;

public class EJobBeanDefinitionParser implements BeanDefinitionParser {

	private Class<?> beanClass;

	private boolean required;

	public EJobBeanDefinitionParser(Class<?> beanClass, boolean required) {
		this.beanClass = beanClass;
		this.required = required;
	}
	private final static String EXECUTOR_INFO = "ejob:executor" ;
	private final static String EJOB_INFOR = "ejob:publish";
	
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		String id = null;
		String nodeName = element.getNodeName();
		if(EXECUTOR_INFO.equals(nodeName.trim())){
			id = element.getAttribute("name");
		}else if(EJOB_INFOR.equals(nodeName)){
			id = element.getAttribute("className");
		}else{
			
			throw new IllegalStateException("不支持的标签异常。");
		}
		
		if (id == null && required) {

			throw new IllegalStateException("MyBeanDefinitionParser.parse,未知的业务逻辑处理：class:" + element.getAttribute("class"));
		}
		if (id != null && id.length() > 0) {
			if (parserContext.getRegistry().containsBeanDefinition(id)) {
				throw new IllegalStateException("Duplicate spring bean id " + id);
			}
			parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		}
		NamedNodeMap nnm = element.getAttributes();
		if(EXECUTOR_INFO.equals(nodeName)){
			
			this.parserExecutor(nnm, beanDefinition);
		}else if(EJOB_INFOR.equals(nodeName)){
			boolean hasChildNodes = element.hasChildNodes();
			if(!hasChildNodes){//<ejob:publish className="***.***.***.**Job" groupName="global_1" jobName="printClassInfo" />
				JobInfo jobInfo = new JobInfo();
				for (int i = 0; i < nnm.getLength(); i++) {
					Node node = nnm.item(i);
					String key = node.getLocalName();
					String value = node.getNodeValue();
					if("className".equals(key)){
						jobInfo.setClassName(value);
					}else if("groupName".equals(key)){
						jobInfo.setGroupName(value);
					}else if("jobName".equals(key)){
						jobInfo.setJobName(value);
					}
				}
				System.out.println(jobInfo.toString());
				ExecutorBuilder.getJobExecutor().enDefaultQueue(new RegisterEJobAction(new RegisterEJob(jobInfo)));
			}else{
				NodeList nodeLists = element.getChildNodes();
				for(int i=0 ; i < nodeLists.getLength();i++){
					Node node = nodeLists.item(i);
					//1、start parse: <ejob:group value="springConfig"> 
					String ejobGroupNode = node.getNodeName() ;
					if(ejobGroupNode.equals("ejob:group")){
						NamedNodeMap groupValue = node.getAttributes() ;
						if(groupValue.getLength() != 1 ){
							throw new IllegalArgumentException(ejobGroupNode +" must to named.");
						}
						Node groupNode = groupValue.item(0);//只有一个数据节点
						String key = groupNode.getLocalName();
						String group = groupNode.getNodeValue() ;
						if(!"value".equals(key)){
							throw new IllegalArgumentException("the attribute of "+ ejobGroupNode +" must be value.");
						}
						
						//2、parse the node of ejob:group all of the children
						NodeList jobNodeLists = node.getChildNodes();
						if(jobNodeLists.getLength() < 1){
							Log.warn(group + " 下面没有配置相关的job");
							continue;
						}
						*//**
						 * 3、开始解析  ejob:job 节点
						 * <ejob:group value="springConfig">
								<ejob:job className="com.huangyiming.disjob.java.job.SpringLabelJobSec" jobName="labelJobSec" />
								<ejob:job className="com.huangyiming.disjob.java.job.SpringLabelJobThree" jobName="labelJobThree" />
						   </ejob:group>
						 *//*
						for(int x=0;x<jobNodeLists.getLength();x++){
							Node jobNode = jobNodeLists.item(x);
							String jobNodeName = jobNode.getNodeName();
							if(!"ejob:job".equals(jobNodeName)){
								throw new IllegalArgumentException("<ejob:group> 下面的子节点必须是以 <ejob:job>  开头。");
							}
							//4、<ejob:job> 解析 他的节点属性
							NamedNodeMap jobAttributes = jobNode.getAttributes();
							String className = null ;
							String jobName = null ;
							for(int y=0;y < jobAttributes.getLength();y++){
								Node jobAttributeNode = jobAttributes.item(y);
								String jobkey = jobAttributeNode.getLocalName();
								String jobValue = jobAttributeNode.getNodeValue();
								if("className".equals(jobkey)){
									className = jobValue;
									continue;
								}
								
								if("jobName".equals(jobkey)){
									jobName = jobValue ;
								}
							}
							
							if(className == null || jobName == null){
								throw new IllegalArgumentException(group + " 下面的第 "+(x+1)+" 个 <ejob:job>  没有配置 className or jobName。");
							}
							
							JobInfo jobInfo = new JobInfo(className, group, jobName);
							System.out.println(jobInfo.toString());
						}
						
					}else{
						System.err.println(ejobGroupNode);
					}
				}
			}
		}
		
		return beanDefinition;
	}
	
	private void parserExecutor(NamedNodeMap nodeMap,RootBeanDefinition beanDefinition){
		for (int i = 0; i < nodeMap.getLength(); i++) {
			Node node = nodeMap.item(i);
			String key = node.getLocalName();
			String value = node.getNodeValue();
			beanDefinition.getPropertyValues().add(key, value);
		}
	}
}
>>>>>>> 31756b5772b8cccc509feda8167e5dd74f23142f:Ejob-jar/src/test/java/com/huangyiming/disjob/spring/EJobBeanDefinitionParser.java
*/