/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.views;

import com.piotrkot.core.Item;
import io.dropwizard.views.View;
import lombok.Getter;

/**
 * Shop view.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.UnusedPrivateField"
    , "PMD.SingularField"})
public final class ShopView extends View {
    /**
     * Template location.
     */
    private static final String LOCATION = "mustache/shop.mustache";
    /**
     * Read only items.
     */
    @Getter
    private final Iterable<Item> items;

    /**
     * Show view.
     *
     * @param available Available items.
     */
    public ShopView(final Iterable<Item> available) {
        super(ShopView.LOCATION);
        this.items = available;
    }
}
