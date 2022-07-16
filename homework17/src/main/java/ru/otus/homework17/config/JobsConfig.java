package ru.otus.homework17.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@EnableBatchProcessing
public class JobsConfig {
    private final JobBuilderFactory jobBuilderFactory;

    private final Step stepOneImportAuthors;
    private final Step stepTwoImportGenres;
    private final Step stepThreeImportBooks;
    private final Step stepFourImportComments;

    @Autowired
    public JobsConfig(JobBuilderFactory jobBuilderFactory
            , Step stepOneImportAuthors
            , Step stepTwoImportGenres
            , Step stepThreeImportBooks
            , Step stepFourImportComments) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepOneImportAuthors = stepOneImportAuthors;
        this.stepTwoImportGenres = stepTwoImportGenres;
        this.stepThreeImportBooks = stepThreeImportBooks;
        this.stepFourImportComments = stepFourImportComments;
    }

    @Bean
    public Job importJob()
            throws Exception {
        return this.jobBuilderFactory.get("importJob")
                .start(stepOneImportAuthors)
                .next(stepTwoImportGenres)
                .next(stepThreeImportBooks)
                .next(stepFourImportComments)
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
