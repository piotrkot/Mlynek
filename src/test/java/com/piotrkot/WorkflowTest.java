/**
 * Copyright (c) 2015. piotrkot
 */
package com.piotrkot;

import com.google.common.collect.Range;
import io.dropwizard.testing.junit.DropwizardAppRule;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
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
    private transient String uri = "";

    /**
     * Test setup.
     */
    @Before
    public void setUp() {
        this.uri = String.format(
            "http://localhost:%d/shop",
            WorkflowTest.APP_RULE.getLocalPort()
        );
    }

    /**
     * Check of main page.
     */
    @Test
    @SneakyThrows
    public void checkShopPage() {
        final HttpResponse response = Request.Get(this.uri).execute()
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
    @Test
    @SneakyThrows
    public void checkBuyPage() {
        final HttpResponse response = Request.Post(this.uri).bodyForm(
            Form.form().add("A", "10").add("B", "3").build()
        ).execute().returnResponse();
        final int status = response.getStatusLine().getStatusCode();
        Assert.assertTrue(
            "buy page error",
            Range.closed(WorkflowTest.OK_MIN, WorkflowTest.OK_MAX)
                .contains(status)
        );
    }
}
