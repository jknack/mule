/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transformers.xml.xstream;

import org.mule.api.transformer.TransformerException;
import org.mule.module.xml.transformer.XStreamFactory;
import org.mule.module.xml.transformer.XmlToObject;
import org.mule.tck.AbstractMuleTestCase;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Tests configuration and creation of XStream-based transformers
 */
public class XStreamTransformerConfigurationTestCase extends AbstractMuleTestCase
{
    public static volatile boolean MyDriverDidInitialize;

    protected static class MyDOMDriver extends DomDriver
    {
        public MyDOMDriver()
        {
            super();
            XStreamTransformerConfigurationTestCase.MyDriverDidInitialize = true;
        }
    }

    @Override
    protected void doSetUp() throws Exception
    {
        super.doSetUp();
        MyDriverDidInitialize = true;
    }

    @Override
    protected void doTearDown() throws Exception
    {
        MyDriverDidInitialize = false;
        super.doTearDown();
    }

    public void testDefaultDriver() throws Exception
    {
        XmlToObject transformer = new XmlToObject();
        // check for XStream's default
        assertEquals(XStreamFactory.XSTREAM_XPP_DRIVER, transformer.getDriverClass());
    }

    public void testCustomDriver() throws Exception
    {
        XmlToObject transformer = new XmlToObject();
        // set custom driver
        transformer.setDriverClass(MyDOMDriver.class.getName());
        XStream xs = transformer.getXStream();

        assertNotNull(xs);
        assertSame(xs, transformer.getXStream());
        assertTrue(MyDriverDidInitialize);
    }

    public void testBadDriver() throws Exception
    {
        XmlToObject transformer = new XmlToObject();
        // set nonexisting driver class
        transformer.setDriverClass("DudeWhereIsMyDriver");

        try
        {
            assertNotNull(transformer.getXStream());
            fail();
        }
        catch (TransformerException tex)
        {
            // OK
            assertTrue(tex.getCause() instanceof ClassNotFoundException);
        }
    }

    public void testClassLoader()
    {

        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();

        try
        {
            TestClassLoader classLoader =  new TestClassLoader();   
            Thread.currentThread().setContextClassLoader(classLoader);
            XmlToObject transformer = new XmlToObject();
            transformer.initialise();
            assertEquals(classLoader, transformer.getXStream().getClassLoader());
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
        }
    }

    private static class TestClassLoader extends ClassLoader
    {
    }

}