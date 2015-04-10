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
     * Can list no items.
     */
    @Test
    public void listingEmptyItems() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        Assert.assertTrue(
            "not empty",
            Iterables.elementsEqual(
                ImmutableList.of(), new WebOrder(mmap).validItems()
            ));
    }
    /**
     * Can handle invalid item.
     */
    @Test
    public void handleInvalidItem() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.put("empty", new ArrayList<String>(0));
        Assert.assertTrue(
            "not empty with &",
            Iterables.elementsEqual(
                ImmutableList.of(), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Can handle single item.
     */
    @Test
    public void handleSingleItem() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        final String single = "single";
        mmap.putSingle(single, "1");
        final Item aitem = new Item(single, 1);
        Assert.assertTrue(
            "cannot read A",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Can handle multiple values for item.
     */
    @Test
    public void handlingMultipleValues() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        mmap.put("multiple", ImmutableList.of("9", "8"));
        Assert.assertTrue(
            "can read key duplicates",
            Iterables.elementsEqual(
                ImmutableList.of(), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Can handle one invalid and one valid item.
     */
    @Test
    public void handleOneValidOneInvalidItem() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        final String aval = "A";
        mmap.putSingle(aval, "12");
        mmap.put("other", new ArrayList<String>(0));
        final Item aitem = new Item(aval, 12);
        Assert.assertTrue(
            "cannot read A with &",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Can handle two valid items.
     */
    @Test
    public void handleTwoValidItems() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        final String bval = "B";
        final String cval = "C";
        mmap.putSingle(bval, "2");
        mmap.putSingle(cval, "3");
        final Item aitem = new Item(bval, 2);
        final Item bitem = new Item(cval, 3);
        Assert.assertTrue(
            "cannot read two keys",
            Iterables.elementsEqual(
                ImmutableList.of(aitem, bitem), new WebOrder(mmap).validItems()
            ));
    }

    /**
     * Can handle non digits.
     */
    @Test
    public void handleNonDigits() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        final String akey = "AKEY";
        final String bkey = "BKEY";
        mmap.putSingle(akey, "4");
        mmap.putSingle(bkey, "01");
        mmap.putSingle("CKEY", "awq");
        mmap.putSingle("DKEY", "0");
        mmap.putSingle("EKEY", "1.2");
        mmap.putSingle("FKEY", "1,4");
        final Item aitem = new Item(akey, 4);
        final Item bitem = new Item(bkey, 1);
        Assert.assertTrue(
            "can read non-digits",
            Iterables.elementsEqual(
                ImmutableList.of(aitem, bitem), new WebOrder(mmap).validItems()
            ));
    }
    /**
     * Can handle negative values.
     */
    @Test
    public void handleNegativeValues() {
        final McMultivalueMap<String, String> mmap = new McMultivalueMap<>();
        final String keypos = "KPOS";
        final String keyzero = "KZERO";
        mmap.putSingle(keypos, "5");
        mmap.putSingle(keyzero, "-0");
        mmap.putSingle("G", "-2");
        mmap.putSingle("H", "-2.3");
        final Item aitem = new Item(keypos, 5);
        Assert.assertTrue(
            "can read negative numbers",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new WebOrder(mmap).validItems()
            ));
    }
}
