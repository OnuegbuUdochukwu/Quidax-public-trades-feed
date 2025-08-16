package com.codewithudo.quidaxpublictradesfeed.controller;

import com.codewithudo.quidaxpublictradesfeed.dto.Trade;
import com.codewithudo.quidaxpublictradesfeed.service.TradesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feeds")
public class TradesController {

    private final TradesService tradesService;

    public TradesController(TradesService tradesService) {
        this.tradesService = tradesService;
    }

    @GetMapping("/{market}/trades")
    public List<Trade> getTradesForMarket(@PathVariable String market) {
        return tradesService.getRecentTrades(market);
    }
}