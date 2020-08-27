package ru.otus.hw14.services;

public interface IdHolderService {
    void addBookMongoID(Long jpaBookId, String mongoBookID);

    String getBookMongoId(Long jpaId);
}
