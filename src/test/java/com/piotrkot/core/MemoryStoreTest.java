/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of MemoryStore class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class MemoryStoreTest {
    /**
     * Can list items.
     */
    @Test
    public void listingItems() {
        final ConcurrentHashMap<String, Integer> map =
            new ConcurrentHashMap<>(1);
        final String itema = "A";
        final String itemb = "B";
        map.put(itema, 1);
        map.put(itemb, 2);
        Assert.assertTrue(
            "wrong elements",
            Iterables.elementsEqual(
                ImmutableList.of(new Item(itema, 1), new Item(itemb, 2)),
                new MemoryStore(map).items()
            ));
    }

    /**
     * Can check selling possibility.
     */
    @Test
    public void checkingSellingPossibility() {
        final ConcurrentHashMap<String, Integer> map =
            new ConcurrentHashMap<>(1);
        final String label = "A_item";
        map.put(label, 1);
        Assert.assertTrue(
            "cannot sell item", new MemoryStore(map).canSell(new Item(label, 1))
        );
        Assert.assertTrue(
            "cannot sell no item",
            new MemoryStore(map).canSell(new Item(label, 0))
        );
        Assert.assertFalse(
            "can sell more A than exist",
            new MemoryStore(map).canSell(new Item(label, 2))
        );
        Assert.assertFalse(
            "can sell not existing",
            new MemoryStore(map).canSell(new Item("not exist", 1))
        );
    }

    /**
     * Can sell items.
     */
    @Test
    public void sellingItems() {
        final ConcurrentHashMap<String, Integer> map =
            new ConcurrentHashMap<>(1);
        final String itema = "AITEM";
        final String itemb = "BITEM";
        map.put(itema, 1);
        final MemoryStore store = new MemoryStore(map);
        store.sell(ImmutableList.of(new Item(itemb, 1)));
        Assert.assertTrue(
            "removing not present B",
            Iterables.elementsEqual(
                ImmutableList.of(new Item(itema, 1)), store.items()
            ));
        store.sell(ImmutableList.of(new Item(itema, 2)));
        Assert.assertTrue(
            "removing more A than exist",
            Iterables.elementsEqual(
                ImmutableList.of(new Item(itema, 1)), store.items()
            ));
        store.sell(ImmutableList.of(new Item(itema, 1)));
        Assert.assertTrue(
            "not cleared",
            Iterables.elementsEqual(ImmutableList.of(), store.items())
        );
        store.sell(ImmutableList.of(new Item(itema, 1)));
        Assert.assertTrue(
            "removing though empty",
            Iterables.elementsEqual(ImmutableList.of(), store.items())
        );
        Assert.assertTrue("not empty", store.empty());
    }
}
