package com.huangyiming.disjob.quence;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 说明：这里的队列是严格的无阻塞队列。如果这个队列中某个业务室阻塞式的，那么将会导致一个严重的问题就是后面的action 就无法保证顺利执行。
 * 使用场景：无阻塞是行为队列
 * @author Disjob
 *
 */
public class BaseActionQueue implements ActionQueue {

	private Queue<Action> queue;
	private Executor executor;
	private ReentrantLock lock = new ReentrantLock();

	private volatile boolean isExecute=false;

	public BaseActionQueue(Executor executor) {
		this.executor = executor;
		queue = new LinkedList<Action>();
	}

	public BaseActionQueue(Executor executor, Queue<Action> queue) {
		this.executor = executor;
		this.queue = queue;
	}

	public ActionQueue getActionQueue() {
		return this;
	}

	public Queue<Action> getQueue() {
		return queue;
	}

	public void enqueue(Action action) {
		lock.lock();
		try{
			queue.add(action);
			int queueSize = queue.size();
			if (queueSize > 4 * Runtime.getRuntime().availableProcessors()) {
				Log.warn(action.toString() + " queue size : " + queueSize);
			}
			if(isExecute)
				return;
			executor.execute(this);
			isExecute=true;
		}finally{
			lock.unlock();
		}
	}


	public void clear() {
		lock.lock();
		try{
			queue.clear();
		}finally{
			lock.unlock();
		}
	}

	public void run() {
		Action action;
		for(;;){
			lock.lock();
			try {
				action = queue.poll();
				if(action==null){
					isExecute = false;
					return;
				}
			}finally {
				lock.unlock();
			}
			action.run();
		}
	}
}
