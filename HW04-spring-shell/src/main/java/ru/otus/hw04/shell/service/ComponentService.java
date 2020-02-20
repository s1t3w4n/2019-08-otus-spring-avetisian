package ru.otus.hw04.shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/*
@PropertySource("classpath:application.yml")
@ComponentScan
@Component
*/
public class ComponentService {
    /*@Bean
    public MessageSource messageSource(@Value("${bundles}") String baseName) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(baseName);
        ms.setDefaultEncoding("UTF-8");
        //ms.setDefaultEncoding("windows-1251");

        return ms;
    }*/
}
