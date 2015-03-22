package com.piotrkot.core;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.piotrkot.resources.PathOrders;

/**
 * Orders made.
 */
public class Orders {
    /**
     * Items to be realized with these orders.
     */
    private final Iterable<Item> items;

    public Orders(final PathOrders ords, final MemoryStore memory) {
        this.items = ImmutableList.copyOf(
            Iterables.filter(ords.validItems(), new Predicate<Item>() {
                @Override
                public boolean apply(Item item) {
                    return memory.canSell(item);
                }
            }));
        memory.sell(ords.validItems());
    }

    /**
     * Orders' items.
     *
     * @return Items representing made orders.
     */
    public Iterable<Item> items() {
        return this.items;
    }
}
