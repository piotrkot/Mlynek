package com.piotrkot.views;

import com.piotrkot.core.Item;
import io.dropwizard.views.View;
import lombok.Getter;

/**
 * View after buying.
 */
public final class BuyView extends View {
    /**
     * Read only items.
     */
    @Getter
    private final Iterable<Item> items;

    public enum Template {
        MUSTACHE("mustache/buy.mustache");
        @Getter
        private final String template;

        Template(final String templ) {
            this.template = templ;
        }
    }

    public BuyView(final Template template, final Iterable<Item> bought) {
        super(template.getTemplate());
        this.items = bought;
    }
}
