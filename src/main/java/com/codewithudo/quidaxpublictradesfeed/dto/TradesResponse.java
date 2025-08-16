package com.codewithudo.quidaxpublictradesfeed.dto;

import lombok.Data;
import java.util.List;

@Data
public class TradesResponse {
    private String status;
    private List<Trade> data;
}
