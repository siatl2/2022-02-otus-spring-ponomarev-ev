package ru.otus.homework14.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@EnableBatchProcessing
public class JobsConfig {
    private final JobBuilderFactory jobBuilderFactory;

    private final Step step1;
    private final Step step2;
    private final Step step3;
    private final Step step4;

    @Autowired
    public JobsConfig(JobBuilderFactory jobBuilderFactory
            , @Qualifier("stepOneAuthor") Step step1
            , @Qualifier("stepTwoGenre") Step step2
            , @Qualifier("stepThreeBook") Step step3
            , @Qualifier("stepFourComments") Step step4) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.step4 = step4;
    }

    @Bean
    public Job importJob()
            throws Exception {
        return this.jobBuilderFactory.get("importJob")
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        System.out.println("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        System.out.println("Конец job");
                    }
                })
                .build();
    }
}
