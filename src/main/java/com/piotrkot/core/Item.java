/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Shopping item.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.UnusedPrivateField",
    "PMD.SingularField"})
@EqualsAndHashCode
public final class Item {
    /**
     * Name of the item.
     */
    @Getter
    private final String name;
    /**
     * Amount of the item.
     */
    @Getter
    private final int count;

    /**
     * Class constructor.
     *
     * @param label Item label.
     * @param amount Item amount.
     */
    public Item(final String label, final int amount) {
        this.name = label;
        this.count = amount;
    }
}
