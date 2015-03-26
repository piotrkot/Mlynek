/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
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
     * Map.
     */
    private final Map<String, Integer> map = new HashMap<>(2);
    /**
     * No A items.
     */
    private final Item anoitem = new Item("A", 0);
    /**
     * One A item.
     */
    private final Item aitem = new Item("A", 1);
    /**
     * Two A items.
     */
    private final Item aitems = new Item("A", 2);
    /**
     * One B item.
     */
    private final Item bitem = new Item("B", 1);
    /**
     * One C item.
     */
    private final Item citem = new Item("C", 1);

    /**
     * Each test setup.
     */
    @Before
    public void setup() {
        this.map.clear();
        this.map.put("A", 1);
        this.map.put("B", 1);
    }

    /**
     * Test items.
     */
    @Test
    public void testItems() {
        Assert.assertTrue(
            Iterables.elementsEqual(
                ImmutableList.of(this.aitem, this.bitem),
                new MemoryStore(this.map).items()
            ));
    }

    /**
     * Test sell possible.
     */
    @Test
    public void testCanSell() {
        Assert.assertTrue(new MemoryStore(this.map).canSell(this.aitem));
        Assert.assertTrue(new MemoryStore(this.map).canSell(this.anoitem));
        Assert.assertFalse(new MemoryStore(this.map).canSell(this.aitems));
        Assert.assertFalse(new MemoryStore(this.map).canSell(this.citem));
    }

    /**
     * Test sell.
     */
    @Test
    public void testSell() {
        final MemoryStore store = new MemoryStore(this.map);
        store.sell(ImmutableList.of(this.aitem));
        Assert.assertTrue(
            Iterables.elementsEqual(
                ImmutableList.of(this.bitem), store.items()
            ));
        store.sell(ImmutableList.of(this.aitem));
        Assert.assertTrue(
            Iterables.elementsEqual(
                ImmutableList.of(this.bitem), store.items()
            ));
        store.sell(ImmutableList.of(this.citem));
        Assert.assertTrue(
            Iterables.elementsEqual(
                ImmutableList.of(this.bitem), store.items()
            ));
        store.sell(ImmutableList.of(this.bitem));
        Assert.assertTrue(
            Iterables.elementsEqual(ImmutableList.of(), store.items())
        );
        Assert.assertTrue(store.empty());
    }
}
