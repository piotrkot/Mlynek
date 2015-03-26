/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot;

import com.google.common.base.Joiner;
import com.google.common.io.ByteStreams;
import io.dropwizard.testing.junit.DropwizardAppRule;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * Main Workflow tests.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class WorkflowTest {
    /**
     * Application Rule.
     */
    @ClassRule
    public static final DropwizardAppRule<ShopConfiguration> APP_RULE =
        new DropwizardAppRule<>(ShopApplication.class, "shop-test.yml");

    /**
     * Root URI for the application.
     */
    private String root;

    /**
     * Each test setup.
     */
    @Before
    public void setup() {
        this.root = String.format(
            "http://localhost:%d/shop",
            WorkflowTest.APP_RULE.getLocalPort()
        );
    }

    /**
     * Main page workflow.
     */
    @Test
    public void workflow() {
        this.checkShopPage();
        this.checkBuyPage();
    }

    /**
     * Check of main page.
     */
    @SneakyThrows
    private void checkShopPage() {
        final HttpResponse response = Request.Get(this.root).execute()
            .returnResponse();
        final int status = response.getStatusLine().getStatusCode();
        ByteStreams.copy(response.getEntity().getContent(), System.out);
        Assert.assertTrue(status >= 200 && status < 300);
    }

    /**
     * Check of buy page.
     */
    @SneakyThrows
    private void checkBuyPage() {
        final HttpResponse response = Request.Get(
            Joiner.on("").join(this.root, "/A=10&B=3")
        ).execute().returnResponse();
        final int status = response.getStatusLine().getStatusCode();
        ByteStreams.copy(response.getEntity().getContent(), System.out);
        Assert.assertTrue(status >= 200 && status < 300);
    }
}
