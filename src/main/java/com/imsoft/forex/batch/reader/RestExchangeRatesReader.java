package com.imsoft.forex.batch.reader;

import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imsoft.forex.vo.ExchangeRates;

public class RestExchangeRatesReader implements ItemReader<ExchangeRates> {
    
    private final String apiUrl;
    private final RestTemplate restTemplate;
    private int nextExchangeRatesIndex;
    private List<ExchangeRates> exchangeRatesData;
    
    public RestExchangeRatesReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextExchangeRatesIndex = 0;
    }
    
    public ExchangeRates read() throws Exception {
        
        if (exchangeRatesIsNotInitialized()) {
            this.exchangeRatesData = fetchExchangeRatesFromAPI();
            System.out.println("exchange rates data : " + this.exchangeRatesData);
        }
        
        ExchangeRates nextData = null;
        
        if (nextExchangeRatesIndex < exchangeRatesData.size()) {
            nextData = exchangeRatesData.get(nextExchangeRatesIndex);
            nextExchangeRatesIndex++;
        } else {
            nextExchangeRatesIndex = 0;
            nextData = null;
        }
        
        return nextData;
    }
    
    private boolean exchangeRatesIsNotInitialized() {
        return this.exchangeRatesData == null;
    }
    
    private List<ExchangeRates> fetchExchangeRatesFromAPI() throws JsonMappingException, JsonProcessingException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        String exchangeRatesJson = restTemplate.getForEntity(apiUrl, String.class).getBody();
        
        return objectMapper.readValue(exchangeRatesJson, new TypeReference<List<ExchangeRates>>() {});
    }


}
