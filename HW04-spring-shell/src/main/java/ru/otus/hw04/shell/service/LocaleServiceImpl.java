package ru.otus.hw04.shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.LocaleService;
import ru.otus.hw04.shell.helpers.LocalesHolder;

import java.util.*;

@PropertySource("classpath:application.yml")
@Service
public class LocaleServiceImpl implements LocaleService {

    private Locale currentLocale = new Locale("en");

    private final Map<Integer, ResourceBundle> resourceBundles;

    public LocaleServiceImpl(@Value("${bundles}") String baseName) {
        resourceBundles = getResourceBundles(baseName);
    }

    public LocalesHolder getLanguageList() {
        Map<Integer, String> languages = new HashMap<>();
        for (Map.Entry<Integer, ResourceBundle> entry : resourceBundles.entrySet()) {
            languages.put(entry.getKey(),
                    entry.getValue().getLocale().getDisplayLanguage(
                            currentLocale));
        }
        return new LocalesHolder(languages);
    }

    public Locale getAvailableLocales(Integer number) {
        return resourceBundles.get(number).getLocale();
    }

    private Map<Integer, ResourceBundle> getResourceBundles(String baseName) {
        HashSet<ResourceBundle> bundles = getBundlesSet(baseName);

        return fillBundlesMap(bundles);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    private HashSet<ResourceBundle> getBundlesSet(String baseName) {
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
        return bundles;
    }

    private Map<Integer, ResourceBundle> fillBundlesMap(HashSet<ResourceBundle> bundles) {
        int count = 0;
        Map<Integer, ResourceBundle> map = new HashMap<>();
        for (ResourceBundle bundle : bundles) {
            count++;
            map.put(count, bundle);
        }
        return map;
    }
}
