/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

/**
 * Order made.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Order {
    /**
     * Items to be realized with this order.
     */
    private final transient Iterable<Item> items;

    /**
     * Memory Store where order is made.
     */
    private final transient MemoryStore memory;
    /**
     * Class constructor.
     *
     * @param products Products to buy.
     * @param store Store where products are bought from.
     */
    public Order(final Iterable<Item> products, final MemoryStore store) {
        this.items = products;
        this.memory = store;
    }

    /**
     * Order's bought items.
     *
     * @return Items representing made order.
     */
    public Iterable<Item> buyItems() {
        return this.memory.sell(this.items);
    }
}
