package com.piotrkot.resources;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.piotrkot.core.Item;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Path order.
 */
public final class PathOrder {
    /**
     * Valid items.
     */
    private Iterable<Item> items;

    public PathOrder(final String orders) {
        this.items = Iterables.transform(this.filterPositive(
                this.parse(this.split(orders))).entrySet(),
            new Function<Map.Entry<String, Integer>, Item>() {
                @Nullable
                @Override
                public Item apply(Map.Entry<String, Integer> item) {
                    return new Item(item.getKey(), item.getValue());
                }
            });
    }

    /**
     * Split order.
     *
     * @param order Path params for order.
     * @return Map of path param item name and path param item count pairs.
     */
    private Map<String, String> split(final String order) {
        return Splitter.on("&").omitEmptyStrings().
            withKeyValueSeparator("=").split(order);
    }

    /**
     * Parse order.
     *
     * @param order Order as path param item name and path param item count pairs.
     * @return Order with param values parsed to integers.
     * When param value cannot be parsed, zero is returned.
     */
    private Map<String, Integer> parse(final Map<String, String> order) {
        return Maps.transformEntries(order,
            new Maps.EntryTransformer<String, String, Integer>() {
                @Override
                public Integer transformEntry(String key, String value) {
                    try {
                        return Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }
            });
    }

    /**
     * Filter positive order amounts.
     *
     * @param order Order as path param item name and path param item count pairs.
     * @return Order with positive items' amounts.
     */
    private Map<String, Integer> filterPositive(final Map<String, Integer> order) {
        return Maps.filterValues(order, new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer integer) {
                return integer != null && integer > 0;
            }
        });
    }

    /**
     * Valid items
     *
     * @return Read-only valid items.
     */
    public Iterable<Item> validItems() {
        return this.items;
    }
}
