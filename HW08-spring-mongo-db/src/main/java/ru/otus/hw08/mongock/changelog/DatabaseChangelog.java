package ru.otus.hw08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw08.models.Author;
import ru.otus.hw08.models.Book;
import ru.otus.hw08.models.Comment;
import ru.otus.hw08.models.Genre;

@ChangeLog
public class DatabaseChangelog {

    private Book pushkinCD;

    @ChangeSet(order = "000", id = "dropDB", author = "me", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initPushkin", author = "me", runAlways = true)
    public void initPushkin(MongoTemplate template) {
        pushkinCD = new Book(1,
                "Captain`s daughter",
                new Author(ObjectId.get().toString(), "Alexander", "Pushkin"),
                new Genre(ObjectId.get().toString(), "novel"));
        template.save(pushkinCD);
    }

    @ChangeSet(order = "002", id = "initTolkien", author = "me", runAlways = true)
    public void initTolkien(MongoTemplate template) {
        template.save(new Book(2,
                "Lord of the rings",
                new Author(ObjectId.get().toString(), "John", "Tolkien"),
                new Genre(ObjectId.get().toString(), "fantasy")));
    }

    @ChangeSet(order = "003", id = "initConanDoyle", author = "me", runAlways = true)
    public void initConanDoyle(MongoTemplate template) {
        template.save(new Book(3,
                "Sherlock Holmes",
                new Author(ObjectId.get().toString(), "Arthur", "Conan Doyle"),
                new Genre(ObjectId.get().toString(), "detective")));
    }

    @ChangeSet(order = "005", id = "initComments", author = "me", runAlways = true)
    public void initComments(MongoTemplate template) {
        template.save(new Comment(ObjectId.get().toString(),
                "The best book in the world!!!",
                pushkinCD));
        template.save(new Comment(ObjectId.get().toString(),
                "Greatest I have ever read",
                pushkinCD));
    }

}
