/*
 *  Copyright 2009-2016 Weibo, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huangyiming.disjob.common.exception;
 

/**
 * @author Disjob
 * @version 创建时间：2016-5-19
 * 
 */
public abstract class EjobAbstractCheckException extends Exception {
    private static final long serialVersionUID = -7742311167276890505L;

    protected EjobErrorMsg ejobErrorMsg;
   
    protected String errorMsg = null;

    public EjobAbstractCheckException() {
        super();
    }

    public EjobAbstractCheckException(EjobErrorMsg ejobErrorMsg) {
        super();
        this.ejobErrorMsg = ejobErrorMsg;
    }

    public EjobAbstractCheckException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public EjobAbstractCheckException(String message, EjobErrorMsg ejobErrorMsg) {
        super(message);
        this.ejobErrorMsg = ejobErrorMsg;
        this.errorMsg = message;
    }

    public EjobAbstractCheckException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

    public EjobAbstractCheckException(String message, Throwable cause, EjobErrorMsg ejobErrorMsg) {
        super(message, cause);
        this.ejobErrorMsg = ejobErrorMsg;
        this.errorMsg = message;
    }

    public EjobAbstractCheckException(Throwable cause) {
        super(cause);
    }

    public EjobAbstractCheckException(Throwable cause, EjobErrorMsg ejobErrorMsg) {
        super(cause);
        this.ejobErrorMsg = ejobErrorMsg;
    }

    @Override
    public String getMessage() {
        if (ejobErrorMsg == null) {
            return super.getMessage();
        }
        String message;
        if (errorMsg != null && !"".equals(errorMsg)) {
            message = errorMsg;
        } else {
            message = ejobErrorMsg.getMessage();
        }
        // TODO 统一上下文 requestid
        return "error_message: " + message + ", status: " + ejobErrorMsg.getStatus() + ", error_code: " + ejobErrorMsg.getErrorCode()
                + ",r=";
    }

    public int getStatus() {
        return ejobErrorMsg != null ? ejobErrorMsg.getStatus() : 0;
    }

    public int getErrorCode() {
        return ejobErrorMsg != null ? ejobErrorMsg.getErrorCode() : 0;
    }

    public EjobErrorMsg getEjobErrorMsg() {
        return ejobErrorMsg;
    }
}
