package com.imsoft.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imsoft.forex.controller.request.ForexRequest;
import com.imsoft.forex.controller.response.ForexResponse;
import com.imsoft.forex.service.ForexService;

@RestController
@RequestMapping(value = "/forex")
public class ForexController {
    
    @Autowired
    private ForexService forexService;
    
    @PostMapping(value = "/queryUsdToNtd", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ForexResponse queryUsdToNtd(@RequestBody ForexRequest forexRequest) {
         return forexService.queryUsdToNtd(forexRequest);
    }
    
}
