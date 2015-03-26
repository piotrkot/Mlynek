/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot;

import com.google.common.base.Joiner;
import com.google.common.collect.Range;
import io.dropwizard.testing.junit.DropwizardAppRule;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.Assert;
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
     * HTTP minimum OK status.
     */
    private static final int OK_MIN = 200;
    /**
     * HTTP maximum OK status.
     */
    private static final int OK_MAX = 299;

    /**
     * Root URI for the application.
     */
    private final String root = String.format(
        "http://localhost:%d/shop",
        WorkflowTest.APP_RULE.getLocalPort()
    );

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
        Assert.assertTrue(
            "main page error",
            Range.closed(WorkflowTest.OK_MIN, WorkflowTest.OK_MAX)
                .contains(status)
        );
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
        Assert.assertTrue(
            "buy page error",
            Range.closed(WorkflowTest.OK_MIN, WorkflowTest.OK_MAX)
                .contains(status)
        );
    }
}
