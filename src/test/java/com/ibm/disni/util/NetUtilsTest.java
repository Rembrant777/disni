package com.ibm.disni.util;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Mae
 * 2023/3/10 1:06 PM
 */
public class NetUtilsTest extends TestCase {
    private final static String HOSTNAME = "localhost";
    public void testGetIntIPFromName() throws UnknownHostException {
        int rc = NetUtils.getIntIPFromName(HOSTNAME);
        Assert.assertTrue(rc >= 0);
    }
    public void testGetIntIPFromInetAddress() throws UnknownHostException {
        Assert.assertEquals(NetUtils.getIntIPFromName(HOSTNAME), NetUtils.getIntIPFromInetAddress(InetAddress.getByName(HOSTNAME)));
    }

    public void testGetInetAddressFromIntIp() throws UnknownHostException {
        int rc = NetUtils.getIntIPFromInetAddress(HOSTNAME);
        InetAddress inetAddress = NetUtils.getInetAddressFromIntIP(rc);
        Assert.assertEquals(rc, NetUtils.getIntIPFromInetAddress(inetAddress));
    }

    public void testShortByteOrder() {
        short s = Short.MIN_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(s), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(s)));
    }

    public void testIntByteOrder() {
        int i = Integer.MAX_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(i), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(i)));
    }

    public void testLongByteOrder() {
        long l = Long.MIN_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(l), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(l)));
    }

    public void testCharByteOrder() {
        char c = Character.MAX_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(c), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(c)));
    }
}