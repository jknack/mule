/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.el.context;

import org.mule.config.MuleManifest;
import org.mule.el.AbstractELTestCase;

import java.net.UnknownHostException;

import junit.framework.Assert;

import org.junit.Test;

public class MuleInstanceContextTestCase extends AbstractELTestCase
{
    public MuleInstanceContextTestCase(Variant variant)
    {
        super(variant);
    }

    @Test
    public void version() throws UnknownHostException
    {
        Assert.assertEquals(MuleManifest.getProductVersion(), evaluate("mule.version"));
    }

    public void assignValueToMuleVersion()
    {
        assertImmutableVariable("mule.version='1'");
    }

    @Test
    public void home() throws UnknownHostException
    {
        Assert.assertEquals(muleContext.getConfiguration().getMuleHomeDirectory(), evaluate("mule.home"));
    }

    public void assignValueToHomeDir()
    {
        assertImmutableVariable("mule.home='1'");
    }

    @Test
    public void clusterId() throws UnknownHostException
    {
        Assert.assertEquals(muleContext.getClusterId(), evaluate("mule.clusterId"));
    }

    public void assignValueToClusterId()
    {
        assertImmutableVariable("mule.clusterId='1'");
    }

    @Test
    public void nodeId() throws UnknownHostException
    {
        Assert.assertEquals(muleContext.getClusterNodeId(), evaluate("mule.nodeId"));
    }

    public void assignValueToNodeId()
    {
        assertImmutableVariable("mule.nodeId='1'");
    }

}
