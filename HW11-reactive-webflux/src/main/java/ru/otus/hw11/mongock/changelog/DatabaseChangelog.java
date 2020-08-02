package ru.otus.hw11.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw11.models.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.hw11.models.*;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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
        pushkinCD = new Book(generateSequence(template),
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
        tolkienLoR = new Book(generateSequence(template),
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
        template.save(new Book(generateSequence(template),
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

    private long generateSequence(MongoOperations mongoOperations) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(Book.SEQUENCE_NAME)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
