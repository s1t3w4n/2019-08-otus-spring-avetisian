package ru.otus.hw04.shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocaleService {
    private final Set<ResourceBundle> rb;

    public LocaleService(@Value("${bundles}") String baseName) {
        rb = getRb(baseName);
    }


    private Set<ResourceBundle> getRb(String baseName) {
        HashSet<ResourceBundle> bundles = new HashSet<>();

        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                bundles.add(ResourceBundle.getBundle(baseName, locale));
            } catch (MissingResourceException e) {
                e.printStackTrace();
            }
        }
        return bundles;
    }


}
