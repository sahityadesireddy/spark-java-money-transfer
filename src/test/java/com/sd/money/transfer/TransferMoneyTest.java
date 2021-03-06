/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer;

import com.sd.money.transfer.SparkServer;
import com.sd.money.transfer.utils.JsonUtils;
import com.sd.money.transfer.utils.TransactionPayload;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransferMoneyTest {

    @BeforeAll
    static void setUp() {
        SparkServer.startWithData();
    }

    @AfterAll
    static void tearDown() {
        SparkServer.stop();
    }

    @Test
    void transferMoney() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost httpPost = new HttpPost("http://localhost:8080/transactions");
            final TransactionPayload payload = new TransactionPayload(1L, 2L, BigDecimal.valueOf(123456, 2));
            final String jsonPayload = JsonUtils.make().toJson(payload);
            final StringEntity stringEntity = new StringEntity(jsonPayload);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());
                assertTrue(response.containsHeader("Location"));
                final HttpEntity entity = response.getEntity();
                assertNotNull(entity);
                assertEquals("application/json", entity.getContentType().getValue());
                final String json = EntityUtils.toString(entity);
                assertTrue(json.contains("\"amount\": 1234.56"));
                assertTrue(json.contains("\"state\": \"COMPLETED\""));
            }
        }
    }
}