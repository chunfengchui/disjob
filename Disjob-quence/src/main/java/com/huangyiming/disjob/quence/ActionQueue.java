package com.huangyiming.disjob.quence;

import java.util.Queue;

public interface ActionQueue extends Runnable {

	public ActionQueue getActionQueue();

	public void enqueue(Action action);

	public void clear();

	public Queue<Action> getQueue();
}
