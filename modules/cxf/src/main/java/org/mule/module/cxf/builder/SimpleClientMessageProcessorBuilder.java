/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.cxf.builder;

import org.mule.api.lifecycle.CreateException;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

public class SimpleClientMessageProcessorBuilder extends AbstractClientMessageProcessorBuilder
{
    @Override
    protected Client createClient() throws CreateException, Exception
    {
        ClientProxyFactoryBean cpf = new ClientProxyFactoryBean();
        cpf.setServiceClass(serviceClass);
        if (databinding == null) 
        {
            cpf.setDataBinding(new AegisDatabinding());
        }
        else 
        {
            cpf.setDataBinding(databinding);
        }
        cpf.setAddress(getAddress());
        cpf.setBus(getBus());
        cpf.setProperties(properties);
        
        if (wsdlLocation != null)
        {
            cpf.setWsdlLocation(wsdlLocation);
        }

        return ClientProxy.getClient(cpf.create());
    }
}