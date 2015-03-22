package com.piotrkot.views;

import com.piotrkot.core.Item;
import io.dropwizard.views.View;
import lombok.Getter;

/**
 * View after buying.
 */
public class BuyView extends View {
    /**
     * Read only items.
     */
    @Getter
    private final Iterable<Item> items;

    public enum Template {
        MUSTACHE("mustache/buy.mustache");
        @Getter
        private final String template;

        private Template(String template) {
            this.template = template;
        }
    }

    public BuyView(final Template template, final Iterable<Item> items) {
        super(template.getTemplate());
        this.items = items;
    }
}