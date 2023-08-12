package com.happiday.Happi_Day.domain.entity.product.dto;

import com.happiday.Happi_Day.domain.entity.product.Sales;
import com.happiday.Happi_Day.domain.entity.product.SalesCategory;
import com.happiday.Happi_Day.domain.entity.product.SalesStatus;
import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SalesListResponseDto {
    private Long id;
    private SalesCategory salesCategory;
    private User user;
    private String name;
    private SalesStatus salesStatus;

    public static SalesListResponseDto of(Sales sales) {
        return SalesListResponseDto.builder()
                .id(sales.getId())
                .salesCategory(sales.getSalesCategory())
                .user(sales.getUsers())
                .name(sales.getName())
                .salesStatus(sales.getSalesStatus())
                .build();
    }
}
