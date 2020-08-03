package ru.otus.hw11.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw11.models.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw11.models.*;

@SuppressWarnings("unused")
@ChangeLog
public class DatabaseChangelog {

    private Book pushkinCD;
    private Book tolkienLoR;

    @ChangeSet(order = "000", id = "dropDB", author = "me", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initPushkin", author = "me", runAlways = true)
    public void initPushkin(MongoTemplate template) {
        final var author = new Author(ObjectId.get().toString(), "Alexander", "Pushkin");
        template.save(author);
        final var genre = new Genre(ObjectId.get().toString(), "novel");
        template.save(genre);
        pushkinCD = new Book(ObjectId.get().toString(),
                "Captain`s daughter",
                author,
                genre);
        template.save(pushkinCD);
    }

    @ChangeSet(order = "002", id = "initTolkien", author = "me", runAlways = true)
    public void initTolkien(MongoTemplate template) {
        final var author = new Author(ObjectId.get().toString(), "John", "Tolkien");
        template.save(author);
        final var genre = new Genre(ObjectId.get().toString(), "fantasy");
        template.save(genre);
        tolkienLoR = new Book(ObjectId.get().toString(),
                "Lord of the rings",
                author,
                genre);
        template.save(tolkienLoR);
    }

    @ChangeSet(order = "003", id = "initConanDoyle", author = "me", runAlways = true)
    public void initConanDoyle(MongoTemplate template) {
        final var author = new Author(ObjectId.get().toString(), "Arthur", "Conan Doyle");
        template.save(author);
        final var genre = new Genre(ObjectId.get().toString(), "detective");
        template.save(genre);
        template.save(new Book(ObjectId.get().toString(),
                "Sherlock Holmes",
                author,
                genre));
    }

    @ChangeSet(order = "005", id = "initComments", author = "me", runAlways = true)
    public void initComments(MongoTemplate template) {
        template.save(new Comment(ObjectId.get().toString(),
                "The best book in the world!!!",
                pushkinCD));
        template.save(new Comment(ObjectId.get().toString(),
                "Greatest I have ever read",
                pushkinCD));
        template.save(new Comment(ObjectId.get().toString(),
                "Boring...",
                tolkienLoR));

    }

}
