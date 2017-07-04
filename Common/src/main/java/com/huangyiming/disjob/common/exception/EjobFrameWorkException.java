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
 * wrapper client exception.
 * 
 * @author maijunsheng
 * 
 */
public class EjobFrameWorkException extends EjobAbstractUncheckException {
    private static final long serialVersionUID = -1638857395789735293L;

    public EjobFrameWorkException() {
        super(""+EjobErrorMsgConstant.JOB_DEFAULT_ERROR_CODE);
    }

    public EjobFrameWorkException(EjobErrorMsg motanErrorMsg) {
        super(motanErrorMsg);
    }

    public EjobFrameWorkException(String message) {
        super(message, EjobErrorMsgConstant.SERVICE_DEFAULT_ERROR);
    }

    public EjobFrameWorkException(String message, EjobErrorMsg motanErrorMsg) {
        super(message, motanErrorMsg);
    }

    public EjobFrameWorkException(String message, Throwable cause) {
        super(message, cause, EjobErrorMsgConstant.SERVICE_DEFAULT_ERROR);
    }

    public EjobFrameWorkException(String message, Throwable cause, EjobErrorMsg motanErrorMsg) {
        super(message, cause, motanErrorMsg);
    }

    public EjobFrameWorkException(Throwable cause) {
        super(cause, EjobErrorMsgConstant.SERVICE_DEFAULT_ERROR);
    }

    public EjobFrameWorkException(Throwable cause, EjobErrorMsg motanErrorMsg) {
        super(cause, motanErrorMsg);
    }

}
