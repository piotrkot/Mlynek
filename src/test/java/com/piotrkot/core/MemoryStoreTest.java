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
     * Item name for A.
     */
    private static final String A_LABEL = "A";
    /**
     * Item name for B.
     */
    private static final String B_LABEL = "B";

    /**
     * Test items.
     */
    @Test
    public void testItems() {
        final ConcurrentHashMap<String, Integer> map =
            new ConcurrentHashMap<>(1);
        map.put(A_LABEL, 1);
        map.put(B_LABEL, 2);
        Assert.assertTrue(
            "wrong elements",
            Iterables.elementsEqual(
                ImmutableList.of(new Item(A_LABEL, 1), new Item(B_LABEL, 2)),
                new MemoryStore(map).items()
            ));
    }

    /**
     * Test sell possible.
     */
    @Test
    public void testCanSell() {
        final ConcurrentHashMap<String, Integer> map =
            new ConcurrentHashMap<>(1);
        map.put(A_LABEL, 1);
        Assert.assertTrue(
            "cannot sell A", new MemoryStore(map).canSell(new Item(A_LABEL, 1))
        );
        Assert.assertTrue(
            "cannot sell no A",
            new MemoryStore(map).canSell(new Item(A_LABEL, 0))
        );
        Assert.assertFalse(
            "can sell more A than exist",
            new MemoryStore(map).canSell(new Item(A_LABEL, 2))
        );
        Assert.assertFalse(
            "can sell B", new MemoryStore(map).canSell(new Item(B_LABEL, 1))
        );
    }

    /**
     * Test sell.
     */
    @Test
    public void testSell() {
        final ConcurrentHashMap<String, Integer> map =
            new ConcurrentHashMap<>(1);
        map.put(A_LABEL, 1);
        final MemoryStore store = new MemoryStore(map);
        store.sell(ImmutableList.of(new Item(B_LABEL, 1)));
        Assert.assertTrue(
            "removing not present B",
            Iterables.elementsEqual(
                ImmutableList.of(new Item(A_LABEL, 1)), store.items()
            ));
        store.sell(ImmutableList.of(new Item(A_LABEL, 2)));
        Assert.assertTrue(
            "removing more A than exist",
            Iterables.elementsEqual(
                ImmutableList.of(new Item(A_LABEL, 1)), store.items()
            ));
        store.sell(ImmutableList.of(new Item(A_LABEL, 1)));
        Assert.assertTrue(
            "not cleared",
            Iterables.elementsEqual(ImmutableList.of(), store.items())
        );
        store.sell(ImmutableList.of(new Item(A_LABEL, 1)));
        Assert.assertTrue(
            "removing though empty",
            Iterables.elementsEqual(ImmutableList.of(), store.items())
        );
        Assert.assertTrue("not empty", store.empty());
    }
}
