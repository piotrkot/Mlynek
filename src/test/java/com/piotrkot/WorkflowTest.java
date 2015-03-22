package com.piotrkot;

import com.google.common.base.Joiner;
import com.google.common.io.ByteStreams;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Main Workflow tests.
 */
public class WorkflowTest {
    @ClassRule
    public static final DropwizardAppRule<ShopConfiguration> appRule =
        new DropwizardAppRule<>(ShopApplication.class, "shop-test.yml");

    /**
     * Root URI for the application.
     */
    private String root;

    @Before
    public void setup() {
        this.root = String.format("http://localhost:%d/shop", appRule.getLocalPort());
    }

    @Test
    public void workflow() throws IOException {
        checkShopPage();
        checkBuyPage();
    }

    private void checkShopPage() throws IOException {
        final HttpResponse response = Request.Get(this.root).execute().returnResponse();
        final int statusCode = response.getStatusLine().getStatusCode();
        ByteStreams.copy(response.getEntity().getContent(), System.out);
        assertTrue((statusCode >= 200 && statusCode < 300));
    }

    private void checkBuyPage() throws IOException {
        final HttpResponse response = Request.Get(
            Joiner.on("").join(this.root, "/A=10&B=3")).execute().returnResponse();
        final int statusCode = response.getStatusLine().getStatusCode();
        ByteStreams.copy(response.getEntity().getContent(), System.out);
        assertTrue((statusCode >= 200 && statusCode < 300));
    }
}
