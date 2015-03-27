/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.views;

import com.piotrkot.core.Item;
import io.dropwizard.views.View;
import lombok.Getter;

/**
 * View after buying.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.UnusedPrivateField"
    , "PMD.SingularField"})
public final class BuyView extends View {
    /**
     * Template location.
     */
    private static final String LOCATION = "mustache/buy.mustache";
    /**
     * Read only items.
     */
    @Getter
    private final Iterable<Item> items;

    /**
     * Buy view.
     *
     * @param bought Bought items.
     */
    public BuyView(final Iterable<Item> bought) {
        super(BuyView.LOCATION);
        this.items = bought;
    }
}
