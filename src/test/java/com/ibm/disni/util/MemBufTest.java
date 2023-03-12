package com.ibm.disni.util;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Mae
 * 2023/3/10 4:30 PM
 */
public class MemBufTest {
    private static final int BUF_SIZE = 1024;

    @Test
    public void testGetAddress() {
        // allocate direct byte buffer space
        ByteBuffer bBuf = ByteBuffer.allocateDirect(BUF_SIZE);

        // set direct byte buffer's order
        bBuf.order(ByteOrder.nativeOrder());

        // get allocated byte buffer address
        long bufAddr = MemoryUtils.getAddress(bBuf);
        Assert.assertTrue(bufAddr > 0L);
    }
}