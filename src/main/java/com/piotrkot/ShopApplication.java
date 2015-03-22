package com.piotrkot;

import com.piotrkot.core.MemoryStore;
import com.piotrkot.health.AvailableItemsHealthCheck;
import com.piotrkot.resources.ShopResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Shop application.
 */
public final class ShopApplication extends Application<ShopConfiguration> {
    public static void main(final String[] args) throws Exception {
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
        bootstrap.addBundle(new AssetsBundle("/assets/shop", "/shop/js", null, "s-js"));
    }

    @Override
    public void run(final ShopConfiguration shopConfiguration, final Environment environment) throws Exception {
        final MemoryStore items = new MemoryStore(shopConfiguration.getItems());
        environment.jersey().register(new ShopResource(items));
        environment.healthChecks().register("itemsAvailable", new AvailableItemsHealthCheck(items));
    }
}
