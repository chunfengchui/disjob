package com.huangyiming.disjob.common.exception;

import java.io.Serializable;

/**
 * @author Disjob
 * @version 创建时间：2016-5-19
 * 
 */ 
public class EjobErrorMsg implements Serializable {
    private static final long serialVersionUID = 4909459500370103048L;

    private int status;
    private int errorcode;
    private String message;

    public EjobErrorMsg(int errorcode, String message) {
        this.errorcode = errorcode;
        this.message = message;
    }
    
    public EjobErrorMsg(int status, int errorcode, String message) {
        this.status = status;
        this.errorcode = errorcode;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public int getErrorCode() {
        return errorcode;
    }

    public String getMessage() {
        return message;
    }

}
