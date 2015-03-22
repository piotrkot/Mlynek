package com.piotrkot.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.piotrkot.core.Item;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test of PathOrder class.
 */
public class PathOrderTest {
    
    @Test
    public void testExtractValid() throws Exception {
        assertTrue(Iterables.elementsEqual(ImmutableList.of(), new PathOrder("").validItems()));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(), new PathOrder("&").validItems()));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 1)),
            new PathOrder("A=1").validItems()));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 1)),
            new PathOrder("A=1&").validItems()));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 1), new Item("B", 2)),
            new PathOrder("A=1&B=2").validItems()));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 1), new Item("E", 1)),
            new PathOrder("A=1&B=0&C=&D=qwe&E=01&F=1.2&G=1,4").validItems()));
        assertTrue(Iterables.elementsEqual(ImmutableList.of(new Item("A", 1)),
            new PathOrder("A=1&B=-0&C=-2&D=-1.3").validItems()));
    }
}