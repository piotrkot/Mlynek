/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.piotrkot.core.Item;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of PathOrder class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class PathOrderTest {

    /**
     * Test validity.
     */
    @Test
    public void testExtractValid() {
        final Item aitem = new Item("A", 1);
        final Item bitem = new Item("B", 1);
        Assert.assertTrue(
            "not empty",
            Iterables.elementsEqual(
                ImmutableList.of(), new PathOrder("").validItems()
            ));
        Assert.assertTrue(
            "not empty with &",
            Iterables.elementsEqual(
                ImmutableList.of(), new PathOrder("&").validItems()
            ));
        Assert.assertTrue(
            "cannot read A",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new PathOrder("A=1").validItems()
            ));
        Assert.assertTrue(
            "cannot read A with &",
            Iterables.elementsEqual(
                ImmutableList.of(aitem), new PathOrder("A=1&").validItems()
            ));
        Assert.assertTrue(
            "cannot read A and B",
            Iterables.elementsEqual(
                ImmutableList.of(aitem, bitem),
                new PathOrder("A=1&B=1").validItems()
            ));
        Assert.assertTrue(
            "can read non-digits",
            Iterables.elementsEqual(
                ImmutableList.of(aitem, bitem),
                new PathOrder("A=1&B=01&C=&D=qwe&E=0&F=1.2&G=1,4").validItems()
            ));
        Assert.assertTrue(
            "can read negative numbers",
            Iterables.elementsEqual(
                ImmutableList.of(aitem),
                new PathOrder("A=1&B=-0&C=-2&D=-1.3").validItems()
            ));
    }
}
