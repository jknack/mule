/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.routing;

import org.apache.commons.lang.Validate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;

public class ConditionalMessageProcessor
{
    private final MessageProcessor messageProcessor;
    private final Filter filter;

    public ConditionalMessageProcessor(MessageProcessor messageProcessor)
    {
        Validate.notNull(messageProcessor, "messageProcessor can't be null");
        this.messageProcessor = messageProcessor;
        this.filter = null;
    }

    public ConditionalMessageProcessor(MessageProcessor messageProcessor, Filter filter)
    {
        Validate.notNull(messageProcessor, "messageProcessor can't be null");
        Validate.notNull(filter, "filter can't be null");
        this.messageProcessor = messageProcessor;
        this.filter = filter;
    }

    public MessageProcessor getMessageProcessor()
    {
        return messageProcessor;
    }

    public Filter getFilter()
    {
        return filter;
    }
}