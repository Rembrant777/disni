package com.ibm.disni.util;

import com.ibm.disni.verbs.impl.SockAddrIn;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Mae
 * 2023/3/11 9:26 PM
 */
public class MemoryAllocationTest {
    // copied from MemoryAllocation#MIN_BLOCK_SIZE
    private static final int MIN_BLOCK_SIZE = 64; // 64B

    @Test
    public void testAllocateSockBuf() {
        MemoryAllocation memAlloc = MemoryAllocation.getInstance();
        MemBuf sockBuf = memAlloc.allocate(SockAddrIn.CSIZE);
        long sockBufAddr = sockBuf.address();
        int sockBufSize = sockBuf.size();
        Assert.assertTrue(sockBufAddr > 0L);
        Assert.assertTrue(sockBufSize > 0);
        long directAddr = MemoryUtils.getAddress(sockBuf.getBuffer());
        Assert.assertEquals(directAddr, sockBufAddr);
        memAlloc.free(sockBuf);
        memAlloc.printTable();
        Assert.assertNotNull(memAlloc.getTable());
        Assert.assertTrue(memAlloc.getTable().size() > 0);
    }

    @Test
    public void testTestAllocateLargerByteBuffer() {
        MemoryAllocation memAlloc = MemoryAllocation.getInstance();
        int applyLen = MIN_BLOCK_SIZE - 1;
        MemBuf applyBuf = memAlloc.allocate(applyLen);
        Assert.assertTrue(applyBuf.address() > 0L);
        Assert.assertTrue(applyBuf.size() > 0);

        // if apply size < MemoryAllocation#MIN_BLOCK_SIZE then apply size will be reset to MIN_BLOCK_SIZE
        Assert.assertTrue(applyBuf.getBuffer().capacity() == MIN_BLOCK_SIZE);

        // then free applyBuf this piece of space will be moved to MemoryAllocation#table
        memAlloc.free(applyBuf);
        Assert.assertTrue(memAlloc.getTable().size() == 1);

        // if apply size > MemoryAllocation#MIN_BLOCK_SIZE then apply size will be reset to 2^(n+1)
        // if apply size in the range of (2^n, 2^(n+1)]
        applyLen = MIN_BLOCK_SIZE + 1;
        applyBuf = memAlloc.allocate(applyLen);
        Assert.assertTrue(applyBuf.address() > 0L);
        Assert.assertTrue(applyBuf.size() > 0);
        Assert.assertEquals(applyBuf.getBuffer().capacity(), roundUpSize(applyLen));

        // free the space
        memAlloc.free(applyBuf);
        Assert.assertTrue(memAlloc.getTable().size() == 2);

        // then re-apply a piece of space with the same length as the first piece of memory
        // this piece of memory should be re-use the original memory from table
        int reApplyLen = MIN_BLOCK_SIZE - 1;
        MemBuf reApplyBuf = memAlloc.allocate(reApplyLen);

        // after apply successfully, the table will remove the record from the table
        // so the table's length return to 1
        Assert.assertTrue(memAlloc.getTable().size() == 1);

        // then re-apply a piece of space with the same length as the second piece of memory
        reApplyLen = MIN_BLOCK_SIZE + 1;
        reApplyBuf = memAlloc.allocate(reApplyLen);
        Assert.assertNotNull(reApplyBuf.getBuffer());
        Assert.assertTrue(reApplyBuf.getBuffer().capacity() == roundUpSize(reApplyLen));

        // cuz we apply a same length memory byte buffer form MemoryAllocation instance contains in its inner table
        // so MemoryAllocation will remove the byte buffer from the table(cache), so after apply ok the table length should be 0
        Assert.assertTrue(memAlloc.getTable().size() == 0);
    }


    // copied from the MemoryAllocation#roundUpSize
    private int roundUpSize(int size){
        // Round up length to the nearest power of two, or the minimum block size
        if (size < MIN_BLOCK_SIZE) {
            size = MIN_BLOCK_SIZE;
        } else {
            size--;
            size |= size >> 1;
            size |= size >> 2;
            size |= size >> 4;
            size |= size >> 8;
            size |= size >> 16;
            size++;
        }
        return size;
    }
}