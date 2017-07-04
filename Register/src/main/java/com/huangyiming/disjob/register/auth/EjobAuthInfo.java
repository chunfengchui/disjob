package com.huangyiming.disjob.register.auth;

import org.apache.curator.framework.AuthInfo;

public class EjobAuthInfo extends AuthInfo{

	public EjobAuthInfo(String scheme, byte[] auth) {
		super(scheme, auth);
	}
	
	public EjobAuthInfo(byte[] auth) {
		super(AuthConstants.SCHEME, auth);
	}
	
	public EjobAuthInfo(String auth) {
		super(AuthConstants.SCHEME, auth.getBytes());
	}

}
