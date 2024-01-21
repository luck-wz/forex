package com.imsoft.forex.batch;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.imsoft.forex.ForexApplication;

@SpringBootTest(classes = {(ForexApplication .class)})
@SpringBatchTest
public class ForexBatchConfigTest {
    
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    
    @Autowired
    private Job forexReaderJob;
    
    @Test
    public void testJob() throws Exception {
        this.jobLauncherTestUtils.setJob(forexReaderJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Assert.assertNotNull(jobExecution);
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
    
    @Test
    public void testStep() {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("forexReaderStep");
        Assert.assertNotNull(jobExecution);
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
    
}