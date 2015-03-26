/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot;

import com.piotrkot.core.MemoryStore;
import com.piotrkot.health.AvailableItemsHealthCheck;
import com.piotrkot.resources.ShopResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import lombok.SneakyThrows;

/**
 * Shop application.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ShopApplication extends Application<ShopConfiguration> {
    /**
     * Main runnable method.
     *
     * @param args Arguments.
     */
    @SneakyThrows
    public static void main(final String[] args) {
        new ShopApplication().run(args);
    }

    @Override
    public String getName() {
        return "Shop";
    }

    @Override
    public void initialize(final Bootstrap<ShopConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(
            new AssetsBundle("/assets/shop", "/shop/js", null, "s-js")
        );
    }

    @Override
    public void run(final ShopConfiguration configuration,
                    final Environment environment) {
        final MemoryStore items = new MemoryStore(configuration.getItems());
        environment.jersey().register(new ShopResource(items));
        environment.healthChecks()
            .register("itemsAvailable", new AvailableItemsHealthCheck(items));
    }
}
