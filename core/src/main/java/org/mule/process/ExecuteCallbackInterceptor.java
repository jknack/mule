/*
 * $Id:AbstractExternalTransactionTestCase.java 8215 2007-09-05 16:56:51Z aperepel $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.process;

class ExecuteCallbackInterceptor<T> implements ProcessingInterceptor<T>
{
    @Override
    public T execute(ProcessingCallback<T> callback) throws Exception
    {
        return callback.process();
    }
}
