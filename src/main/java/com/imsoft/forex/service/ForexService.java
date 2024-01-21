package com.imsoft.forex.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imsoft.forex.controller.request.ForexRequest;
import com.imsoft.forex.controller.response.ForexResponse;
import com.imsoft.forex.controller.response.ForexStatus;
import com.imsoft.forex.entity.Collection;
import com.imsoft.forex.repository.CollectionRepository;

@Service
public class ForexService {
    
    @Autowired
    private CollectionRepository collectionRepository;
    
    public ForexResponse queryUsdToNtd(ForexRequest request) {
        
        ForexResponse response = new ForexResponse();
        ForexStatus status = new ForexStatus();

        if (validate(request, status)) {
            
            List<Collection> collectionList = collectionRepository.findClassAttrByChidAndCaidIn(
                    request.getStartDate(), request.getEndDate());
            
            response.setCurrency(collectionList);
            
            status.setCode("0000");
            status.setMessage("成功");
        }
        
        response.setError(status);

        return response;
    }
    
    private boolean validate(ForexRequest request, ForexStatus status) {
        
        boolean isPassValidate = true;
        
        if (!"usd".equals(request.getCurrency())) {
            status.setMessage("僅提供查詢美元/台幣的歷史資料");
            isPassValidate = false;
        }
        
        LocalDate now = LocalDate.now();
        
        LocalDate oneYearAgo = now.minusYears(1);
        
        LocalDate startDate = LocalDate.parse(request.getStartDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate endDate = LocalDate.parse(request.getEndDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        if (isPassValidate && 
                (!startDate.isAfter(oneYearAgo.minusDays(1)) || !endDate.isBefore(now))) {
            
            status.setMessage("日期區間不符");
            status.setCode("E001");
            isPassValidate = false;
        } 
        
        return isPassValidate;
    }
    
}
