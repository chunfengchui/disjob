package com.huangyiming.disjob.common.exception;

public class RpcServiceException extends EjobAbstractUncheckException {
	private static final long serialVersionUID = -5323792555332165319L;
	
	public RpcServiceException(final String errorMessage, final Object... args) {
	       super(String.format(errorMessage, args));
	}
	    
	public RpcServiceException(final Exception cause) {
	       super(cause);
	}
	
	public RpcServiceException(String message) {
        super(message, EjobErrorMsgConstant.SERVICE_DEFAULT_ERROR);
    }
}
  