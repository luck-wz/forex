package com.imsoft.forex.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.imsoft.forex.batch.processor.RestForexProcessor;
import com.imsoft.forex.batch.reader.RestExchangeRatesReader;
import com.imsoft.forex.batch.writer.RestForexWriter;
import com.imsoft.forex.entity.Collection;
import com.imsoft.forex.vo.ExchangeRates;

@Configuration
@EnableBatchProcessing
public class ForexBatchConfig {
    
    private static final String FOREX_REST_API_URL = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public RestForexProcessor restForexProcessor() {
        return new RestForexProcessor();
    }

    @Bean
    public RestForexWriter restForexWriter() {
        return new RestForexWriter();
    }
    
    @Bean
    public Job forexReaderJob(Step chunkStep) {
        
        return jobBuilderFactory.get("forexReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(chunkStep)
                .build();
        
    }
    
    @Bean
    public Step chunkStep(ItemReader<ExchangeRates> restForexReader, 
            RestForexProcessor restForexProcessor, RestForexWriter restForexWriter) {
        
        return stepBuilderFactory.get("forexReaderStep")
                .<ExchangeRates, Collection> chunk(10)
                .reader(restForexReader)
                .processor(restForexProcessor)
                .writer(restForexWriter)
                .allowStartIfComplete(true) // 確保能夠重複執行
                .build();
    }
    
    @Bean
    @StepScope
    public ItemReader<ExchangeRates> restForexReader() {   
        return new RestExchangeRatesReader(FOREX_REST_API_URL, new RestTemplate());
    }

}
