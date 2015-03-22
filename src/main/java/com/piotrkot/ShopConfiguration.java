package com.piotrkot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Shop main configuration.
 */
public final class ShopConfiguration extends Configuration {
    /**
     * Items.
     */
    @NotNull
    @JsonProperty
    @Getter
    private Map<String, Integer> items;
}
