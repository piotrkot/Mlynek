/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import lombok.Synchronized;

/**
 * Memory store. Stateful object.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class MemoryStore {
    /**
     * Items as a map.
     */
    private final transient Map<String, Integer> map;

    /**
     * Class constructor.
     *
     * @param items Items to be stored.
     */
    public MemoryStore(final Map<String, Integer> items) {
        this.map = Maps.newHashMap(items);
    }

    /**
     * All items.
     *
     * @return All items from the store.
     */
    @Synchronized
    public Iterable<Item> items() {
        return Iterables.transform(
            this.map.entrySet(),
            new Function<Map.Entry<String, Integer>, Item>() {
                @Override
                public Item apply(final Map.Entry<String, Integer> item) {
                    return new Item(item.getKey(), item.getValue());
                }
            });
    }

    /**
     * Selling items which decreases the store.
     *
     * @param items Items to be bought.
     * @return Items sold.
     */
    @Synchronized
    public Iterable<Item> sell(final Iterable<Item> items) {
        final List<Item> sold = Lists.newArrayList();
        for (final Item item : items) {
            if (this.canSell(item)) {
                sold.add(item);
                final Integer value = this.map.get(item.getName())
                    - item.getCount();
                if (value == 0) {
                    this.map.remove(item.getName());
                } else {
                    this.map.put(
                        item.getName(), this.map.get(item.getName())
                            - item.getCount()
                    );
                }
            }
        }
        return sold;
    }

    /**
     * Test if item can be sold.
     *
     * @param item Item in question.
     * @return True If item can be sold. False, otherwise.
     */
    public boolean canSell(final Item item) {
        return this.map.containsKey(item.getName())
            && this.map.get(item.getName()) >= item.getCount();
    }

    /**
     * Test memory store.
     *
     * @return True, if there is no items. False, otherwise.
     */
    @Synchronized
    public boolean empty() {
        return this.map.isEmpty();
    }
}
