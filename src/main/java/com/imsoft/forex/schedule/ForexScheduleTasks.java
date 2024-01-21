package com.imsoft.forex.schedule;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ForexScheduleTasks {

    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job forexReaderJob;
    
    @Scheduled(cron = "0 0 18 * * ?")
    public void getForexData() throws Exception {
        System.out.println("執行批次");
        jobLauncher.run(forexReaderJob, new JobParameters());
    }
    
}
