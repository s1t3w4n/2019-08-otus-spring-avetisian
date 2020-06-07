package ru.otus.hw08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.hw08.models.*;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@ChangeLog
public class DatabaseChangelog {

    private Book pushkinCD;

    @ChangeSet(order = "000", id = "dropDB", author = "me", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initPushkin", author = "me", runAlways = true)
    public void initPushkin(MongoTemplate template) {
        pushkinCD = new Book(generateSequence(template),
                "Captain`s daughter",
                new Author(ObjectId.get().toString(), "Alexander", "Pushkin"),
                new Genre(ObjectId.get().toString(), "novel"));
        template.save(pushkinCD);

    }

    @ChangeSet(order = "002", id = "initTolkien", author = "me", runAlways = true)
    public void initTolkien(MongoTemplate template) {
        template.save(new Book(generateSequence(template),
                "Lord of the rings",
                new Author(ObjectId.get().toString(), "John", "Tolkien"),
                new Genre(ObjectId.get().toString(), "fantasy")));
    }

    @ChangeSet(order = "003", id = "initConanDoyle", author = "me", runAlways = true)
    public void initConanDoyle(MongoTemplate template) {
        template.save(new Book(generateSequence(template),
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

    private long generateSequence(MongoOperations mongoOperations) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(Book.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
