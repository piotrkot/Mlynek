package com.piotrkot.views;

import com.piotrkot.core.Item;
import io.dropwizard.views.View;
import lombok.Getter;

/**
 * Shop view.
 */
public final class ShopView extends View {
    /**
     * Read only items.
     */
    @Getter
    private final Iterable<Item> items;

    public enum Template {
        MUSTACHE("mustache/shop.mustache");
        @Getter
        private final String template;

        Template(final String templ) {
            this.template = templ;
        }
    }

    public ShopView(final Template template, final Iterable<Item> available) {
        super(template.getTemplate());
        this.items = available;
    }
}
