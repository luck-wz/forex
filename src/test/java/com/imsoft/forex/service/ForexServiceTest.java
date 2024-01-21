package com.imsoft.forex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.imsoft.forex.controller.request.ForexRequest;
import com.imsoft.forex.controller.response.ForexResponse;

@SpringBootTest
public class ForexServiceTest {
    
    @Autowired
    private ForexService forexService;
    
    @DisplayName("測試成功案例")
    @Test
    @Order(1)
    void testSuccess() {
        
        LocalDate now = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate oneYearAgo = now.minusYears(1);
        
        String yesterdayStr = yesterday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String oneYearAgoStr = oneYearAgo.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        ForexRequest forexRequest = new ForexRequest();
        forexRequest.setStartDate(oneYearAgoStr);
        forexRequest.setEndDate(yesterdayStr);
        forexRequest.setCurrency("usd");

        ForexResponse response = forexService.queryUsdToNtd(forexRequest);
        
        assertEquals("0000", response.getError().getCode());
        assertEquals("成功", response.getError().getMessage());
        assertNotNull(response.getCurrency());
    }
    
    @DisplayName("測試錯誤的貨幣")
    @Test
    @Order(2)
    void testWrongCurrency() {
        
        LocalDate now = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate oneYearAgo = now.minusYears(1);
        
        String yesterdayStr = yesterday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String oneYearAgoStr = oneYearAgo.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        ForexRequest forexRequest = new ForexRequest();
        forexRequest.setStartDate(oneYearAgoStr);
        forexRequest.setEndDate(yesterdayStr);
        forexRequest.setCurrency("twd");

        ForexResponse response = forexService.queryUsdToNtd(forexRequest);
        
        assertEquals("僅提供查詢美元/台幣的歷史資料", response.getError().getMessage());
        assertNull(response.getCurrency());
    }
    
    @DisplayName("測試錯誤的查詢起訖日")
    @Test
    @Order(3)
    void testWrongDate() {
        
        LocalDate now = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate oneYearAgo = now.minusYears(1);
        
        String yesterdayStr = yesterday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String oneYearAgoStr = oneYearAgo.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        String errorStartDateStr = oneYearAgo.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String errorEndDateStr = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        // 起日測試
        ForexRequest forexRequest = new ForexRequest();
        forexRequest.setStartDate(errorStartDateStr);
        forexRequest.setEndDate(yesterdayStr);
        forexRequest.setCurrency("usd");

        ForexResponse response = forexService.queryUsdToNtd(forexRequest);
        
        assertEquals("E001", response.getError().getCode());
        assertEquals("日期區間不符", response.getError().getMessage());
        assertNull(response.getCurrency());
        
        // 訖日測試
        forexRequest = new ForexRequest();
        forexRequest.setStartDate(oneYearAgoStr);
        forexRequest.setEndDate(errorEndDateStr);
        forexRequest.setCurrency("usd");
        
        response = forexService.queryUsdToNtd(forexRequest);
        
        assertEquals("E001", response.getError().getCode());
        assertEquals("日期區間不符", response.getError().getMessage());
        assertNull(response.getCurrency());
    }
    
}
