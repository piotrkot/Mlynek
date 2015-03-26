/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.core;

import lombok.Value;

/**
 * Shopping item.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Value
public final class Item {
    /**
     * Name of the item.
     * @checkstyle VisibilityModifierCheck (2 lines)
     */
    String name;
    /**
     * Amount of the item.
     * @checkstyle VisibilityModifierCheck (2 lines)
     */
    Integer count;
}
