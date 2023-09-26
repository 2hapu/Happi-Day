package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.product.dto.ReadListSalesDto;
import com.happiday.Happi_Day.domain.entity.product.dto.ReadOneSalesDto;
import com.happiday.Happi_Day.domain.entity.product.dto.WriteSalesDto;
import com.happiday.Happi_Day.domain.service.SalesService;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    // 판매글 작성
    @PostMapping(value = "/{categoryId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReadOneSalesDto> createSales(
            @PathVariable("categoryId") Long id,
            @RequestPart(name = "sale") WriteSalesDto requestDto,
            @RequestPart(name = "thumbnailImage", required = false) MultipartFile thumbnailImage){
        String username = SecurityUtils.getCurrentUsername();
        ReadOneSalesDto response = salesService.createSales(id, requestDto, thumbnailImage, username);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 판매글 목록 조회
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ReadListSalesDto>> readSalesList(
            @PathVariable("categoryId") Long id) {
        List<ReadListSalesDto> responseSalesList = salesService.readSalesList(id);
        return new ResponseEntity<>(responseSalesList, HttpStatus.OK);
    }

    // 판매글 상세 조회
    @GetMapping("/{categoryId}/{salesId}")
    public ResponseEntity<ReadOneSalesDto> readSalesOne(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("salesId") Long salesId) {
        ReadOneSalesDto responseSales = salesService.readSalesOne(categoryId,salesId);
        return new ResponseEntity<>(responseSales, HttpStatus.OK);
    }

    // 판매글 수정
    @PutMapping(value = "/{salesId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReadOneSalesDto> updateSales(
            @PathVariable("salesId") Long salesId,
            @RequestPart(name = "sale") WriteSalesDto requestDto,
            @RequestPart(name = "thumbnailImage", required = false) MultipartFile thumbnailImage) {
        String username = SecurityUtils.getCurrentUsername();
        ReadOneSalesDto responseSales =  salesService.updateSales(salesId, requestDto, thumbnailImage, username);
        return new ResponseEntity<>(responseSales, HttpStatus.OK);
    }

    // 판매글 삭제
    @DeleteMapping("/{categoryId}/{salesId}")
    public ResponseEntity<String> deleteSales(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("salesId") Long salesId){
        String username = SecurityUtils.getCurrentUsername();
        salesService.deleteSales(categoryId, salesId, username);
        return new ResponseEntity<>("판매글 삭제 성공", HttpStatus.OK);
    }
}
