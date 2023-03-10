package com.ibm.disni.util;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.slf4j.Logger;

import java.util.Random;

/**
 * Mae
 * 2023/3/10 9:18 AM
 */
public class EndianUtilsTest extends TestCase {
    private static Logger LOG = DiSNILogger.getLogger();

    public void testSwapShort() {
        LOG.info("#testSwap1");
        Random rr = new Random();
        short EXPECTED_SHORT = Short.valueOf(String.valueOf(Math.abs(rr.nextInt() % (Short.MAX_VALUE))));
        Assert.assertEquals(EXPECTED_SHORT, EndianUtils.swap(EndianUtils.swap(EXPECTED_SHORT)));

        EXPECTED_SHORT *= -1;
        Assert.assertEquals(EXPECTED_SHORT, EndianUtils.swap(EndianUtils.swap(EXPECTED_SHORT)));
    }

    public void testSwapChar() {
        LOG.info("#testSwap2");

    }

    public void testSwapInt() {
        LOG.info("#testSwap3");

    }

    public void testSwapLong() {
        LOG.info("#testSwap4");

    }
}