/* Copyright (c) 2001-2011, The HSQL Development Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hsqldb.jdbc;

import org.hsqldb.jdbc.testbase.BaseDriverTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.hsqldb.testbase.ForSubject;

/**
 *
 * @author Campbell Boucher-Burnet (boucherb@users dot sourceforge.net)
 */
@ForSubject(JDBCDriver.class)
public class JDBCDriverTest extends BaseDriverTestCase {

    public JDBCDriverTest(String testName) {
        super(testName);
    }

    @Override
    protected int getExpectedDriverPropertyInfoCount() {
        return getIntProperty("driver.property.info.count", 6);
    }

    @Override
    protected int getExpectedMajorVersion() {
        return getIntProperty("driver.major.version", 2);
    }

    @Override
    protected int getExpectedMinorVersion() {
        return getIntProperty("driver.minor.version", 2);
    }

    @Override
    protected boolean getExpectedJdbcCompliant() {
        return getBooleanProperty("driver.jdbc.compliant", true);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(JDBCDriverTest.class);

        return suite;
    }

    public static void main(java.lang.String[] argList) {

        junit.textui.TestRunner.run(suite());
    }
}
