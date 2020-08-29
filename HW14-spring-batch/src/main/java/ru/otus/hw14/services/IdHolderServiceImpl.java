package ru.otus.hw14.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdHolderServiceImpl implements IdHolderService {
    private final Map<Long, String> bookIdMap;

    public IdHolderServiceImpl() {
        this.bookIdMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addBookMongoID(Long jpaBookId, String mongoBookID) {
        bookIdMap.put(jpaBookId, mongoBookID);
    }

    @Override
    public String getBookMongoId(Long jpaId) {
        return bookIdMap.get(jpaId);
    }
}
