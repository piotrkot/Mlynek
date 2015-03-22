package com.piotrkot.core;

import lombok.Value;

/**
 * Shopping item.
 */
@Value
public final class Item {
    /**
     * Name of the item.
     */
    String name;
    /**
     * Amount of the item.
     */
    Integer count;
}
