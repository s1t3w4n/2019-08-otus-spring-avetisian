package ru.otus.hw14.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw14.models.jpa.Author;
import ru.otus.hw14.repositories.jpa.AuthorJpaRepository;
import ru.otus.hw14.services.EntityMapperService;

import java.util.HashMap;

@SuppressWarnings("SpringFacetCodeInspection")
@AllArgsConstructor
@Configuration
public class JobConfig {
    public static final String MY_JOB = "myJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;

    private final AuthorJpaRepository authorJpaRepository;

    private final EntityMapperService entityMapperService;

    @StepScope
    @Bean
    public RepositoryItemReader<Author> authorReader() {
        return new RepositoryItemReaderBuilder<Author>()
                .name("authorReader")
                .sorts(new HashMap<>())
                .repository(authorJpaRepository)
                .methodName("findAll")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, ru.otus.hw14.models.mongo.Author> processor() {
        return entityMapperService::mapAuthor;
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.hw14.models.mongo.Author> authorWriter() {
        return new MongoItemWriterBuilder<ru.otus.hw14.models.mongo.Author>()
                .collection("authors")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Job myJob(Step step) {
        return jobBuilderFactory.get(MY_JOB)
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step authorStep(ItemReader<Author> reader,
                                 ItemProcessor<Author, ru.otus.hw14.models.mongo.Author> itemProcessor,
                                 ItemWriter<ru.otus.hw14.models.mongo.Author> writer) {
        return stepBuilderFactory.get("authorsStep")
                .<Author, ru.otus.hw14.models.mongo.Author>chunk(3)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }
}
