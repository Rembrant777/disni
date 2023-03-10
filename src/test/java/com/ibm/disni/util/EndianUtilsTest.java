package com.ibm.disni.util;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Random;

/**
 * Mae
 * 2023/3/10 9:18 AM
 */
public class EndianUtilsTest extends TestCase {
    public void testSwapShort() {
        Random rr = new Random();
        short EXPECTED_SHORT = Short.valueOf(String.valueOf(Math.abs(rr.nextInt() % (Short.MAX_VALUE))));
        Assert.assertEquals(EXPECTED_SHORT, EndianUtils.swap(EndianUtils.swap(EXPECTED_SHORT)));

        EXPECTED_SHORT *= -1;
        Assert.assertEquals(EXPECTED_SHORT, EndianUtils.swap(EndianUtils.swap(EXPECTED_SHORT)));
    }

    public void testSwapChar() {
        char EXPECTED_CHAR = 'c';
        Assert.assertEquals(EXPECTED_CHAR, EndianUtils.swap(EndianUtils.swap(EXPECTED_CHAR)));
    }

    public void testSwapInt() {
        Random rr = new Random();
        int EXPECTED_INT = Math.abs(rr.nextInt());
        Assert.assertEquals(EXPECTED_INT, EndianUtils.swap(EndianUtils.swap(EXPECTED_INT)));

        EXPECTED_INT *= -1;
        Assert.assertEquals(EXPECTED_INT, EndianUtils.swap(EndianUtils.swap(EXPECTED_INT)));
    }

    public void testSwapLong() {
        Random rr = new Random();
        long EXPECTED_LONG = Math.abs(rr.nextLong());
        Assert.assertEquals(EXPECTED_LONG, EndianUtils.swap(EndianUtils.swap(EXPECTED_LONG)));

        EXPECTED_LONG *= -1L;
        Assert.assertEquals(EXPECTED_LONG, EndianUtils.swap(EndianUtils.swap(EXPECTED_LONG)));
    }
}