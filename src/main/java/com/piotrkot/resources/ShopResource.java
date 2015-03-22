package com.piotrkot.resources;

import com.codahale.metrics.annotation.Timed;
import com.piotrkot.core.MemoryStore;
import com.piotrkot.core.Order;
import com.piotrkot.views.BuyView;
import com.piotrkot.views.ShopView;
import lombok.Synchronized;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Shop resource,
 */
@Path("/shop")
@Value
@Slf4j
public class ShopResource {
    /**
     * Transactional memory store.
     */
    MemoryStore store;

    @GET
    @Timed
    @Synchronized
    @Produces(MediaType.TEXT_HTML)
    public ShopView shopView() {
        return new ShopView(ShopView.Template.MUSTACHE, this.store.items());
    }

    @GET
    @Path("/{orders}")
    @Timed
    @Synchronized
    @Produces(MediaType.TEXT_HTML)
    public BuyView buyView(@PathParam("orders") final String ords) {
        return new BuyView(BuyView.Template.MUSTACHE,
            new Order(new PathOrder(ords), this.store).boughtItems());
    }
}
