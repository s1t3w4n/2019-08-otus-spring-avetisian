package ru.otus.hw04.shell.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw04.shell.dao.QuestionPrintAdapter;
import ru.otus.hw04.shell.service.LocaleService;
import ru.otus.hw04.shell.service.MSService;

@ComponentScan(/*{"resources.*", "ru.otus.hw04.shell.data.resources.*"}*/)
@PropertySource("classpath:application.yml")
@SpringBootConfiguration
public class TestSpringBootConfiguration {
    @Bean
    QuestionPrintAdapter questionPrintAdapter(MSService mss, LocaleService ls) {
        return new QuestionPrintAdapter(mss, ls);
    }
    @Bean
    MSService mss(MessageSource ms) {
        return new MSService(ms);
    }
    @Bean
    public MessageSource messageSource(@Value("${bundles}") String baseName) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(baseName);
        ms.setDefaultEncoding("windows-1251");

        return ms;
    }
    @Bean
    LocaleService ls(@Value("${bundles}") String baseName) {
        return new LocaleService(baseName);
    }
}
