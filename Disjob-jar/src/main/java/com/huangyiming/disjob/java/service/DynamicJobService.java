package com.huangyiming.disjob.java.service;

import java.util.concurrent.ConcurrentHashMap;

import com.huangyiming.disjob.java.job.EJob;

public final class DynamicJobService {

	public static ConcurrentHashMap<String, Class<? extends EJob>> DYNAMIC_JOBS = new ConcurrentHashMap<String, Class<? extends EJob>>();
	
	public static ConcurrentHashMap<String, EJob> EJOB_OBJECT_MAP = new ConcurrentHashMap<String, EJob>();
	
	private DynamicJobService() {
	}
}
