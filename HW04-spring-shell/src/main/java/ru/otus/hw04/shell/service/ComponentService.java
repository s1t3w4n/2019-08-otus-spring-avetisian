package ru.otus.hw04.shell.service;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@ComponentScan
@Component
public class ComponentService {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/il8n/bundle");
        //ms.setDefaultEncoding("UTF-8");
        ms.setDefaultEncoding("windows-1251");

        return ms;
    }
}
