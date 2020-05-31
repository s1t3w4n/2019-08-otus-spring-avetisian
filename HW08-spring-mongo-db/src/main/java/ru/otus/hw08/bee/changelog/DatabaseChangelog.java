package ru.otus.hw08.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import ru.otus.hw08.models.Author;
import ru.otus.hw08.models.Book;
import ru.otus.hw08.models.Genre;

@ChangeLog
public class DatabaseChangelog {
    private static final String FIELD_NAME_ID = "_id";

    @ChangeSet(author = "me", id = "001", order = "001")
    public void insertPushkin(DB db) {
        DBCollection myCollection = db.getCollection(Book.COLLECTION_NAME);

        BasicDBObject genreBuilder = new BasicDBObject()
                .append(FIELD_NAME_ID, 1)
                .append(Genre.GENRE_GENRE, "novel");
        BasicDBObject authorBuilder = new BasicDBObject()
                .append(Author.AUTHOR_FIRST_NAME, "Alexander")
                .append(Author.AUTHOR_LAST_NAME, "Pushkin");
        BasicDBObject bookBuilder = new BasicDBObject()
                .append(Book.BOOK_TITLE, "Captain`s daughter")
                .append(Book.BOOK_AUTHOR, authorBuilder)
                .append(Book.BOOK_GENRE, genreBuilder);

        myCollection.insert(bookBuilder);
    }
}
