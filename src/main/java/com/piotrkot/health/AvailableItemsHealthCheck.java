/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.health;

import com.codahale.metrics.health.HealthCheck;
import com.piotrkot.core.MemoryStore;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Available Items Health Check.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Value
@EqualsAndHashCode(callSuper = false)
public final class AvailableItemsHealthCheck extends HealthCheck {
    /**
     * Memory store.
     */
    MemoryStore store;

    @Override
    public Result check() throws Exception {
        if (this.store.empty()) {
            return Result.unhealthy("No available items");
        }
        return Result.healthy();
    }
}
