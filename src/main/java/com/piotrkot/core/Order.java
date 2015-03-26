/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
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
    private final Iterable<Item> items;

    /**
     * Class constructor.
     *
     * @param ords Path orders.
     * @param memory Memory.
     */
    public Order(final PathOrder ords, final MemoryStore memory) {
        this.items = ImmutableList.copyOf(
            Iterables.filter(
                ords.validItems(),
                new Predicate<Item>() {
                    @Override
                    public boolean apply(final Item item) {
                        return memory.canSell(item);
                    }
                }));
        memory.sell(ords.validItems());
    }

    /**
     * Order's items.
     *
     * @return Items representing made order.
     */
    public Iterable<Item> boughtItems() {
        return this.items;
    }
}
