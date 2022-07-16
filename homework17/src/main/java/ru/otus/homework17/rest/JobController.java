package ru.otus.homework17.rest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private final Job importJob;
    private final JobLauncher jobLauncher;

    @Autowired
    public JobController(Job importJob, JobLauncher jobLauncher) {
        this.importJob = importJob;
        this.jobLauncher = jobLauncher;
    }

    @GetMapping("/start-m")
    public String startMigration() throws Exception {
        JobExecution execution = jobLauncher.run(importJob, new JobParametersBuilder()
                .toJobParameters());
        return execution.toString();
    }

}
