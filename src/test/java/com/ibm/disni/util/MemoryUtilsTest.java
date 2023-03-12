package com.ibm.disni.util;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Mae
 * 2023/3/10 4:34 PM
 */
public class MemoryUtilsTest {
    @Test
    public void test() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        long directAddr = MemoryUtils.getAddress(byteBuffer);
        Assert.assertTrue(directAddr > 0L);
    }
}