/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the MuleSource MPL
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.jersey.config;

import org.mule.config.spring.handlers.AbstractMuleNamespaceHandler;
import org.mule.config.spring.parsers.specific.ComponentDefinitionParser;
import org.mule.transport.jersey.JerseyResourcesComponent;

public class JerseyNamespaceHandler extends AbstractMuleNamespaceHandler
{
    public void init()
    {
        ComponentDefinitionParser parser = new ComponentDefinitionParser(JerseyResourcesComponent.class);
        
        registerBeanDefinitionParser("resources", parser);
    }
}