
package com.huangyiming.disjob.graph;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.huangyiming.disjob.event.AbstractEventObject;
import com.huangyiming.disjob.event.ObjectEvent;
import com.huangyiming.disjob.event.ObjectListener;
import com.huangyiming.disjob.java.job.DependEJob;

public class Scheduler extends AbstractEventObject<Node<DependEJob>> {
	
	@SuppressWarnings("rawtypes")
	private Graph graph ;
	private Map<Node<DependEJob>, JobCondition> jobConditionsMap = null ;
	@SuppressWarnings("rawtypes")
	Scheduler(Graph graph){
		this.graph = graph ;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void attachListener() {
		
		jobConditionsMap = new ConcurrentHashMap<Node<DependEJob>, JobCondition>(graph.getVertexSet().size());
		Iterator<?> nodes = graph.getVertexSet().iterator();
		
		while(nodes.hasNext()){
			Node<DependEJob> node = (Node<DependEJob>) nodes.next();
			Set<Node<DependEJob>> reverseNode = (Set<Node<DependEJob>>) graph.getReverseAdjaNode().get(node);
			
			jobConditionsMap.put(node,new JobCondition(this,node,reverseNode));
		}
		
		this.addListener(new ObjectListener<Node<DependEJob>>() {
			
			public void onEvent(ObjectEvent<Node<DependEJob>> event) {
				Node<DependEJob> ejob = event.getValue();
				//得到当前这个节点的前驱节点[可能有多个]
				Set<Node<DependEJob>> dependsNodes = (Set<Node<DependEJob>>) graph.getAdjaNode().get(ejob);
				if(dependsNodes!=null){
					//如果不为null,则向他的后继节点广播一个消息。后继节点都知道自己将收到多少个消息后便可以开始执行
					Iterator<?> iter = dependsNodes.iterator();
					for(;iter.hasNext();){
						Node<DependEJob> temp = (Node<DependEJob>) iter.next();
						JobCondition jobCondition = jobConditionsMap.get(temp) ;
						jobCondition.increMessageCount(ejob);//收到一个消息。进行加一操作。
						jobCondition.handler();
					}
				}else{
					jobConditionsMap.get(ejob).handler();//如果是最后一个节点每次执行完就直接看消息有没有收足够，收足够了，就执行，没有收足够，就直接丢弃
				}
			}
		}, 4);
	}
	
	public void notify(Node<DependEJob> node){
		ObjectEvent<Node<DependEJob>> objectEvent = new ObjectEvent<Node<DependEJob>>(node, 4);
		this.notifyListeners(objectEvent);
	}
}
