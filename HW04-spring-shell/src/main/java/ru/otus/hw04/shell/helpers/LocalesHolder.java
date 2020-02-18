package ru.otus.hw04.shell.helpers;

import java.util.Map;

public class LocalesHolder {
    private final Map<Integer, String> nameLocalesMap;

    public LocalesHolder(Map<Integer, String> nameLocalesMap) {
        this.nameLocalesMap = nameLocalesMap;
    }

    public String getLaguageMessage() {
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Integer, String> entry : nameLocalesMap.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
