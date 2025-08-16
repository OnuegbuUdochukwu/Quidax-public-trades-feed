package com.codewithudo.quidaxpublictradesfeed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Trade {

    private String type;
    private String price;
    private long timestamp;

    @JsonProperty("trade_id")
    private int tradeId;

    @JsonProperty("base_volume")
    private String baseVolume;

    @JsonProperty("quote_volume")
    private String quoteVolume;
}
