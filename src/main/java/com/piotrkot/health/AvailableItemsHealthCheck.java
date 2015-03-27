/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.health;

import com.codahale.metrics.health.HealthCheck;
import com.piotrkot.core.MemoryStore;
import lombok.EqualsAndHashCode;

/**
 * Available Items Health Check.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false)
public final class AvailableItemsHealthCheck extends HealthCheck {
    /**
     * Memory store.
     */
    private final transient MemoryStore store;

    /**
     * Class constructor.
     *
     * @param memory Store.
     */
    public AvailableItemsHealthCheck(final MemoryStore memory) {
        super();
        this.store = memory;
    }

    @Override
    public Result check() throws Exception {
        Result result = Result.healthy();
        if (this.store.empty()) {
            result = Result.unhealthy("No available items");
        }
        return result;
    }
}
