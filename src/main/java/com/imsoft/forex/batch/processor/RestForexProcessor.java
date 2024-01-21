package com.imsoft.forex.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import com.imsoft.forex.entity.Collection;
import com.imsoft.forex.vo.ExchangeRates;

public class RestForexProcessor implements ItemProcessor<ExchangeRates, Collection> {
    
    public Collection process(ExchangeRates exchangeRates) throws Exception {
        
        Collection collection = new Collection();
        BeanUtils.copyProperties(exchangeRates, collection);
        
        return collection;
    }
}
