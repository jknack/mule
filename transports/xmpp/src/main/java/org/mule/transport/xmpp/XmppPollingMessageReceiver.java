/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.xmpp;

import org.mule.api.DefaultMuleException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.api.lifecycle.CreateException;
import org.mule.api.transport.Connector;
import org.mule.process.ProcessingCallback;
import org.mule.process.ProcessingTemplate;
import org.mule.transport.AbstractPollingMessageReceiver;

import org.jivesoftware.smack.packet.Message;

public class XmppPollingMessageReceiver extends AbstractPollingMessageReceiver
{
    private final XmppConnector connector;
    private XmppConversation conversation;

    public XmppPollingMessageReceiver(Connector conn, FlowConstruct flowConstruct, InboundEndpoint endpoint)
        throws CreateException
    {
        super(conn, flowConstruct, endpoint);
        connector = (XmppConnector) conn;
        conversation = connector.getConversationFactory().create(endpoint);
    }

    @Override
    protected void doConnect() throws Exception
    {
        conversation.connect();
    }

    @Override
    protected void doDisconnect() throws Exception
    {
        conversation.disconnect();
    }

    @Override
    protected void doDispose()
    {
        conversation = null;
    }

    @Override
    public void poll() throws Exception
    {
        // Wait 10% less than the polling frequency. This approach makes sure that we finish 
        // in time before the next poll call comes in
        try
        {
            long frequency = getFrequency();
            long tenPercent = (long)(frequency * 0.1);
            long pollTimeout = frequency - tenPercent;

            Message xmppMessage = conversation.receive(pollTimeout);
            if (xmppMessage == null)
            {
                return;
            }

            processMessage(xmppMessage);
        }
        catch (Exception e)
        {
            connector.getMuleContext().getExceptionListener().handleException(e);
            throw e;
        }

    }

    @Override
    protected boolean pollOnPrimaryInstanceOnly()
    {
        return true;
    }

    protected void processMessage(final Message xmppMessage) throws MuleException
    {
        ProcessingTemplate<MuleEvent> processingTemplate = createProcessingTemplate();
        try
        {
            processingTemplate.execute(new ProcessingCallback<MuleEvent>()
            {
                @Override
                public MuleEvent process() throws Exception
                {
                    MuleMessage muleMessage = createMuleMessage(xmppMessage);
                    routeMessage(muleMessage);
                    return null;
                }
            });
        }
        catch (MuleException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new DefaultMuleException(e);
        }

    }
}    
