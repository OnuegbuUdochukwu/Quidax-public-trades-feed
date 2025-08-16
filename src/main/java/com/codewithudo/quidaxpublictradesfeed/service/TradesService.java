package com.codewithudo.quidaxpublictradesfeed.service;

import com.codewithudo.quidaxpublictradesfeed.dto.Trade;
import com.codewithudo.quidaxpublictradesfeed.dto.TradesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@Service
public class TradesService {

    private final RestTemplate restTemplate;

    public TradesService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Trade> getRecentTrades(String market) {
        String url = "https://app.quidax.io/api/v1/trades/" + market;

        TradesResponse response = restTemplate.getForObject(url, TradesResponse.class);

        if (response != null && "success".equals(response.getStatus())) {
            return response.getData();
        }

        return Collections.emptyList();
    }
}