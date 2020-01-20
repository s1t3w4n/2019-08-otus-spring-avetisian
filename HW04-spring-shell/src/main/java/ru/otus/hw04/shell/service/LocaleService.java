package ru.otus.hw04.shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocaleService {

    private Locale currentLocale = new Locale("en");

    private final Map<Integer, ResourceBundle> rb;

    public LocaleService(@Value("${bundles}") String baseName) {
        rb = getRb(baseName);
    }

    public Map<Integer, String> getLanguageList() {
        Map<Integer, String> languages = new HashMap<>();
        for (Map.Entry<Integer, ResourceBundle> entry : rb.entrySet()) {
            languages.put(entry.getKey(),
                    entry.getValue().getLocale().getDisplayLanguage(
                            currentLocale));
        }
        return languages;
    }

    public Locale getAvailableLocales(Integer number) {
        return rb.get(number).getLocale();
    }

    private Map<Integer, ResourceBundle> getRb(String baseName) {
        HashSet<ResourceBundle> bundles = new HashSet<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            ResourceBundle bundle = null;
            try {
                bundle = ResourceBundle.getBundle(baseName, locale);
            } catch (MissingResourceException ignored) {
            } finally {
                if (Objects.nonNull(bundle)) {
                    bundles.add(bundle);
                }
            }
        }
        int count = 0;
        Map<Integer, ResourceBundle> map = new HashMap<>();
        for (ResourceBundle bundle : bundles) {
            count++;
            map.put(count, bundle);
        }
        return map;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}
