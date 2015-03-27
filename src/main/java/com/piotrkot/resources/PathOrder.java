/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.resources;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.piotrkot.core.Item;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * Order as path parameters.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class PathOrder {
    /**
     * Valid items.
     */
    private final transient Iterable<Item> items;

    /**
     * Class constructor.
     *
     * @param orders Path orders.
     */
    public PathOrder(final CharSequence orders) {
        this.items = Iterables.transform(
            PathOrder.filterPositive(PathOrder.parse(PathOrder.split(orders)))
                .entrySet(),
            new Function<Map.Entry<String, Integer>, Item>() {
                @Override
                public Item apply(final Map.Entry<String, Integer> item) {
                    return new Item(item.getKey(), item.getValue());
                }
            });
    }

    /**
     * Valid items.
     *
     * @return Iterable valid items.
     */
    public Iterable<Item> validItems() {
        return this.items;
    }

    /**
     * Split order.
     *
     * @param order Path params for order.
     * @return Map of path param item name and path param item count pairs.
     */
    private static Map<String, String> split(final CharSequence order) {
        return Splitter.on("&").omitEmptyStrings()
            .withKeyValueSeparator("=").split(order);
    }

    /**
     * Parse order.
     *
     * @param order Order as path param item name and path param item
     *  count pairs.
     * @return Order with param values parsed to integers.
     *  When param value cannot be parsed, zero is returned.
     */
    private static Map<String, Integer> parse(final Map<String, String> order) {
        return Maps.transformEntries(
            order,
            new Maps.EntryTransformer<String, String, Integer>() {
                @Override
                public Integer transformEntry(final String key,
                                              final String value) {
                    int parsed;
                    try {
                        parsed = Integer.parseInt(value);
                    } catch (final NumberFormatException ignore) {
                        parsed = 0;
                    }
                    return parsed;
                }
            });
    }

    /**
     * Filter positive order amounts.
     *
     * @param order Order as path param item name and path param item
     *  count pairs.
     * @return Order with positive items' amounts.
     */
    private static Map<String, Integer> filterPositive(
        final Map<String, Integer> order) {
        return Maps.filterValues(
            order, new Predicate<Integer>() {
                @Override
                public boolean apply(@Nullable final Integer integer) {
                    return integer != null && integer > 0;
                }
            });
    }
}
