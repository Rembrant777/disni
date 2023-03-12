package com.ibm.disni.util;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Mae
 * 2023/3/11 9:21 PM
 */
public class NativeAffinityTest {
    @Test
    @Ignore
    public void testSetAffinity() {
        long affinity = 1L << 1;
        NativeAffinity.setAffinity(affinity);
        Assert.assertNotNull(NativeAffinity.getAffinity());
    }
}