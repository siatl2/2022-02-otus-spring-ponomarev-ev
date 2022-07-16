package ru.otus.homework17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
class IntegrationTest {
    private static final String JOB_NAME = "importJob";
    private static final String JOB_STATUS = "COMPLETED";
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParametersBuilder()
                .toJobParameters()
        );

        assertAll(
                () -> assertEquals(JOB_NAME, job.getName()),
                () -> assertEquals(JOB_STATUS, jobExecution.getExitStatus().getExitCode())
        );
    }
}
