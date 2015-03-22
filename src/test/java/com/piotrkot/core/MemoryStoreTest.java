package com.piotrkot.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test of MemoryStore class.
 */
public class MemoryStoreTest {
    private Map<String, Integer> map;

    @Before
    public void setup() {
        map = new HashMap<>();
        map.put("A", 2);
        map.put("B", 4);
    }

    @Test
    public void testItems() throws Exception {
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 2), new Item("B", 4)),
            new MemoryStore(map).items()));
    }

    @Test
    public void testCanSell() throws Exception {
        assertTrue(new MemoryStore(map).canSell(new Item("A", 1)));
        assertTrue(new MemoryStore(map).canSell(new Item("A", 0)));
        assertTrue(new MemoryStore(map).canSell(new Item("A", 2)));
        assertFalse(new MemoryStore(map).canSell(new Item("A", 3)));
    }

    @Test
    public void testSell() throws Exception {
        final MemoryStore store = new MemoryStore(map);
        store.sell(ImmutableList.of(new Item("A", 1)));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 1), new Item("B", 4)),
            store.items()));

        store.sell(ImmutableList.of(new Item("A", 1)));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("B", 4)), store.items()));

        store.sell(ImmutableList.of(new Item("A", 1)));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("B", 4)), store.items()));

        store.sell(ImmutableList.of(new Item("B", 5)));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("B", 4)), store.items()));

        store.sell(ImmutableList.of(new Item("B", 4)));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(), store.items()));
        assertTrue(store.empty());
    }
}