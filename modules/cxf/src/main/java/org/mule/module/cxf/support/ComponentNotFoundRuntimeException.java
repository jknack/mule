/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.cxf.support;

import org.mule.api.MuleRuntimeException;
import org.mule.config.i18n.Message;

public class ComponentNotFoundRuntimeException extends MuleRuntimeException
{

    public ComponentNotFoundRuntimeException(Message message, Throwable cause)
    {
        super(message, cause);
    }

    public ComponentNotFoundRuntimeException(Message message)
    {
        super(message);
    }

}

