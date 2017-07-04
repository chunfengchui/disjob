package com.huangyiming.disjob.rpc.codec;

import java.io.Serializable;

public class EjobRestartTaskResponse implements EjobResponse,Serializable {

	public boolean status;

 

	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "EjobRestartTaskResponse [status=" + status + "]";
	}
	
	
	
}
