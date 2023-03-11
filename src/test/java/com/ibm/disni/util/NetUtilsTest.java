package com.ibm.disni.util;

import junit.framework.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Mae
 * 2023/3/10 1:06 PM
 */
public class NetUtilsTest {
    private final static String HOSTNAME = "localhost";

    @Test
    public void testGetIntIPFromName() throws UnknownHostException {
        int rc = NetUtils.getIntIPFromName(HOSTNAME);
        Assert.assertTrue(rc >= 0);
    }

    @Test
    public void testGetIntIPFromInetAddress() throws UnknownHostException {
        Assert.assertEquals(NetUtils.getIntIPFromName(HOSTNAME), NetUtils.getIntIPFromInetAddress(InetAddress.getByName(HOSTNAME)));
    }

    @Test
    public void testGetInetAddressFromIntIp() throws UnknownHostException {
        int rc = NetUtils.getIntIPFromInetAddress(HOSTNAME);
        InetAddress inetAddress = NetUtils.getInetAddressFromIntIP(rc);
        Assert.assertEquals(rc, NetUtils.getIntIPFromInetAddress(inetAddress));
    }

    @Test
    public void testShortByteOrder() {
        short v = Short.MAX_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(v), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(NetUtils.hostToNetworkByteOrder(v))));
    }

    @Test
    public void testIntByteOrder() {
        int v = Integer.MAX_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(v), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(NetUtils.hostToNetworkByteOrder(v))));
    }

    @Test
    public void testLongByteOrder() {
        long v = Long.MIN_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(v), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(NetUtils.hostToNetworkByteOrder(v))));
    }

    @Test
    public void testCharByteOrder() {
        char v = Character.MAX_VALUE;
        Assert.assertEquals(NetUtils.hostToNetworkByteOrder(v), NetUtils.hostToNetworkByteOrder(NetUtils.networkToHostByteOrder(NetUtils.hostToNetworkByteOrder(v))));
    }
}