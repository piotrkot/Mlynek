/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.piotrkot.core.Item;
import com.piotrkot.mock.McMultivalueMap;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of PathOrder class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class WebOrderTest {
    /**
     * Item name for A.
     */
    private static final String A_LABEL = "A";
    /**
     * Item name for B.
     */
    private static final String B_LABEL = "B";

    /**
     * Test validity for empty.
     */
    @Test
    public void testEmptyValid() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        Assert.assertTrue(
            "not empty",
            Iterables.elementsEqual(
                ImmutableList.of(), new WebOrder(mmap).validItems()
            ));
    }
    /**
     * Test validity for key with no value.
     */
    @Test
    public void testEmptyKeyValid() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.put(A_LABEL, new ArrayList<String>(0));
        Assert.assertTrue(
            "not empty with &",
            Iterables.elementsEqual(
                ImmutableList.of(), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Test validity for one key.
     */
    @Test
    public void testOneKeyValid() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.putSingle(A_LABEL, "1");
        final Item aitem = new Item(A_LABEL, 1);
        Assert.assertTrue(
            "cannot read A",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Test validity for multiple same keys.
     */
    @Test
    public void testMultipleKeyValid() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.put(A_LABEL, ImmutableList.of("9", "8"));
        Assert.assertTrue(
            "can read key duplicates",
            Iterables.elementsEqual(
                ImmutableList.of(), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Test validity for one key with value and one with no value.
     */
    @Test
    public void testOneKeyValidOneKeyEmpty() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.putSingle(A_LABEL, "12");
        mmap.put(B_LABEL, new ArrayList<String>(0));
        final Item aitem = new Item(A_LABEL, 12);
        Assert.assertTrue(
            "cannot read A with &",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Test validity for two keys with values.
     */
    @Test
    public void testTwoKeysValid() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.putSingle(A_LABEL, "2");
        mmap.putSingle(B_LABEL, "3");
        final Item aitem = new Item(A_LABEL, 2);
        final Item bitem = new Item(B_LABEL, 3);
        Assert.assertTrue(
            "cannot read A and B",
            Iterables.elementsEqual(
                ImmutableList.of(aitem, bitem), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Test validity for non digits.
     */
    @Test
    public void testNonDigits() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.putSingle(A_LABEL, "4");
        mmap.putSingle(B_LABEL, "01");
        mmap.putSingle("C", "awq");
        mmap.putSingle("D", "0");
        mmap.putSingle("E", "1.2");
        mmap.putSingle("F", "1,4");
        final Item aitem = new Item(A_LABEL, 4);
        final Item bitem = new Item(B_LABEL, 1);
        Assert.assertTrue(
            "can read non-digits",
            Iterables.elementsEqual(
                ImmutableList.of(aitem, bitem), new WebOrder(mmap).validItems()
            ));
    }
    /**
     * Test validity for negative values.
     */
    @Test
    public void testNegativeValues() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.putSingle(A_LABEL, "5");
        mmap.putSingle(B_LABEL, "-0");
        mmap.putSingle("G", "-2");
        mmap.putSingle("H", "-2.3");
        final Item aitem = new Item(A_LABEL, 5);
        Assert.assertTrue(
            "can read negative numbers",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new WebOrder(mmap).validItems()
            ));
    }
}
