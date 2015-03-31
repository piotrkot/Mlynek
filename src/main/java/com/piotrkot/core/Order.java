/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import com.piotrkot.resources.PathOrder;

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
     * Store where order is made.
     */
    private final transient MemoryStore store;
    /**
     * Class constructor.
     *
     * @param ords Path orders.
     * @param memory Memory.
     */
    public Order(final PathOrder ords, final MemoryStore memory) {
        this.items = ords.validItems();
        this.store = memory;
    }

    /**
     * Order's bought items.
     *
     * @return Items representing made order.
     */
    public Iterable<Item> buyItems() {
        return this.store.sell(this.items);
    }
}
