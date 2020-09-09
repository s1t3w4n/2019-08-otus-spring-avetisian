package ru.otus.hw15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.hw15.service.MotherNature;

@IntegrationComponentScan
@EnableIntegration
@SpringBootApplication
public class Hw15SpringIntegrationApplication {

    public static void main(String[] args) {
        final var run = SpringApplication.run(Hw15SpringIntegrationApplication.class, args);
        final var bean = run.getBean(MotherNature.class);
        bean.makeMiracle();
    }

}
