/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Shop main configuration.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings("PMD.UnusedPrivateField")
public final class ShopConfiguration extends Configuration {
    /**
     * Items.
     */
    @NotNull
    @JsonProperty
    @Getter
    private Map<String, Integer> items;
}
