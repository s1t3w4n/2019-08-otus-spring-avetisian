package ru.otus.hw08.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.hw08.models.Book;
import ru.otus.hw08.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
class MongoCommentCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {
    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id = Long.parseLong(source.get("_id").toString());
        commentRepository.deleteAllByBook_Id(id);
    }
}
