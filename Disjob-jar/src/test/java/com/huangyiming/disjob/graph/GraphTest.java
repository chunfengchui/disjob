package com.huangyiming.disjob.graph;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.huangyiming.disjob.java.ExecutorBuilder;
import com.huangyiming.disjob.java.job.DependEJob;

public class GraphTest {

	private static Node<DependEJob> startNode = null ;
	public static void main(String[] args) throws Exception {
		Graph<DependEJob> graph = new Graph<DependEJob>();

		Node<DependEJob> JOB_A = new Node<DependEJob>(new JobA("A"));
		Node<DependEJob> JOB_B = new Node<DependEJob>(new JobB("B"));
		Node<DependEJob> JOB_C = new Node<DependEJob>(new JobC("C"));
		Node<DependEJob> JOB_D = new Node<DependEJob>(new JobD("D"));
		Node<DependEJob> JOB_E = new Node<DependEJob>(new JobD("E"));
		Node<DependEJob> JOB_F = new Node<DependEJob>(new JobD("F"));

		graph.addNode(JOB_A, JOB_B);
		graph.addNode(JOB_B, JOB_E);
		graph.addNode(JOB_B, JOB_C);
		graph.addNode(JOB_E, JOB_D);
		graph.addNode(JOB_E, JOB_F);
		graph.addNode(JOB_C, JOB_F);
		graph.addNode(JOB_D, JOB_F);
		

		startNode = JOB_A;
		
		Iterator<Node<DependEJob>> iter = graph.getVertexSet().iterator();
		while (iter.hasNext()) {
			Node<DependEJob> node = iter.next();
			Set<Node<DependEJob>> sets = graph.getAdjaNode().get(node);
			if (sets != null) {
				System.out.print(node.getVal().getKey() + "->");
				for (Node<DependEJob> n : sets) {
					System.out.print(n.getVal().getKey() + ",");
				}
				System.out.println();
			}
		}
		
		
		System.out.println("===getReverseAdjaNode==");

		for(Map.Entry<Node<DependEJob>, Set<Node<DependEJob>>> entry : graph.getReverseAdjaNode().entrySet()){
			System.out.print(entry.getKey().getVal().getKey() + "->");
			Set<Node<DependEJob>> sets = entry.getValue();
			for (Node<DependEJob> n : sets) {
				System.out.print(n.getVal().getKey() + ",");
			}
			System.out.println();
		}
		
		
		Scheduler scheduler = new Scheduler(graph);
		//3s 钟后触发A节点
		while(true){
			Thread.sleep(10000);
			ExecutorBuilder.getJobExecutor().execute(new JobAction(startNode, scheduler));
		}
	}
}
