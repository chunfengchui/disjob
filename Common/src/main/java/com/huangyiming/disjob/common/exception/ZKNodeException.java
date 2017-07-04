package com.huangyiming.disjob.common.exception;

public class ZKNodeException extends EjobAbstractUncheckException{
    
	private static final long serialVersionUID = 1393957353478034407L;
    
    public ZKNodeException(final Exception cause) {
        super(cause);
    }
    
    public ZKNodeException(String message, final Exception cause) {
        super(message, cause);
    }
    
      
}
