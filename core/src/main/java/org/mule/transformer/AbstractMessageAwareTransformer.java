/*
 * $Id: AbstractMessageAwareTransformer.java 18823 2010-08-05 11:48:28Z dirk.olmes $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;

/**
 * <code>AbstractMessageAwareTransformer</code> is the superclass for pre-MULE 3.0 message transformers.  Newly
 * created message transformers should derive from AbstractMessageTransformer.
 *
 * @see AbstractMessageTransformer
 */
@Deprecated
public abstract class AbstractMessageAwareTransformer extends AbstractMessageTransformer
{
    /**
     * Transform the message.
     * @param message
     * @param outputEncoding
     * @return
     * @throws TransformerException
     */
    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException
    {
        return transform(message, outputEncoding);
    }

    /**
     * Subclasses implement this method.
     * @param message
     * @param outputEncoding
     * @return
     * @throws TransformerException
     */
    public abstract Object transform(MuleMessage message, String outputEncoding) throws TransformerException;

}