package com.piotrkot.resources;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.piotrkot.core.Item;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Path orders.
 */
@Value
@Slf4j
public class PathOrders {
    /**
     * Orders as a string from path parameters.
     */
    String orders;

    /**
     * Split orders.
     *
     * @return Map of path param name and path param value pairs.
     */
    private Map<String, String> split() {
        return Splitter.on("&").omitEmptyStrings().
            withKeyValueSeparator("=").split(this.orders);
    }

    /**
     * Parse orders.
     *
     * @param orders Orders as path param name and path param value pairs.
     * @return Orders with param values parsed to integers.
     * When param value cannot be parsed, zero is returned.
     */
    private Map<String, Integer> parse(final Map<String, String> orders) {
        return Maps.transformEntries(orders,
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
     * Filter positive orders.
     *
     * @param orders Orders with param values parsed to integers.
     * @return Orders with positive param values.
     */
    private Map<String, Integer> filterPositive(final Map<String, Integer> orders) {
        return Maps.filterValues(orders, new Predicate<Integer>() {
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
        return Iterables.transform(this.filterPositive(this.parse(this.split())).entrySet(),
            new Function<Map.Entry<String, Integer>, Item>() {
                @Nullable
                @Override
                public Item apply(Map.Entry<String, Integer> item) {
                    return new Item(item.getKey(), item.getValue());
                }
            });
    }
}
