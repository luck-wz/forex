package com.imsoft.forex.batch.writer;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.imsoft.forex.entity.Collection;
import com.imsoft.forex.repository.CollectionRepository;

public class RestForexWriter implements ItemWriter<Collection> {
    
    @Autowired
    private CollectionRepository collectionRepository;

    public void write(List<? extends Collection> items) throws Exception {
        collectionRepository.saveAll(items);
    }
}
