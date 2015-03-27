/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot.resources;

import com.codahale.metrics.annotation.Timed;
import com.piotrkot.core.MemoryStore;
import com.piotrkot.core.Order;
import com.piotrkot.views.BuyView;
import com.piotrkot.views.ShopView;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * Shop resource.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Path("/shop")
@Slf4j
public final class ShopResource {
    /**
     * Transactional memory store.
     */
    private final transient MemoryStore store;

    /**
     * Class constructor.
     *
     * @param memory Memory.
     */
    public ShopResource(final MemoryStore memory) {
        this.store = memory;
    }
    /**
     * Main view.
     *
     * @return Shop view.
     */
    @GET
    @Timed
    @Synchronized
    @Produces(MediaType.TEXT_HTML)
    public ShopView shopView() {
        return new ShopView(this.store.items());
    }

    /**
     * View after buying.
     *
     * @param ords Orders.
     * @return Buy view.
     */
    @GET
    @Path("/{orders}")
    @Timed
    @Synchronized
    @Produces(MediaType.TEXT_HTML)
    public BuyView buyView(@PathParam("orders") final String ords) {
        return new BuyView(
            new Order(new PathOrder(ords), this.store)
                .boughtItems()
        );
    }
}
