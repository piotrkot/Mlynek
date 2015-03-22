package com.piotrkot.health;

import com.codahale.metrics.health.HealthCheck;
import com.piotrkot.core.MemoryStore;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Available Items Health Check.
 */
@Value
@EqualsAndHashCode(callSuper = false)
public final class AvailableItemsHealthCheck extends HealthCheck {
    /**
     * Memory store
     */
    MemoryStore store;

    @Override
    protected Result check() throws Exception {
        if (this.store.empty()) {
            return Result.unhealthy("No available items");
        }
        return Result.healthy();
    }
}
