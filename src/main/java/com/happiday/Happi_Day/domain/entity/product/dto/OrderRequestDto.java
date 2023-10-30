package com.happiday.Happi_Day.domain.entity.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class OrderRequestDto {
    private Map<String,Integer> products;
    private String address;
}
