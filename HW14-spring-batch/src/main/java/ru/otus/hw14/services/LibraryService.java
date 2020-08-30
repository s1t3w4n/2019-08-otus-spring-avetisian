package ru.otus.hw14.services;

public interface LibraryService {
    String getJPAData();

    String getMongoData();

    void dbMigration();

    void clean();
}
