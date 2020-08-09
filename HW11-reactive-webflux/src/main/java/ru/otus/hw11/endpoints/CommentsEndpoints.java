package ru.otus.hw11.endpoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.hw11.models.Comment;
import ru.otus.hw11.models.dto.CommentDto;
import ru.otus.hw11.repositories.CommentRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class CommentsEndpoints {
    @Bean
    public RouterFunction<ServerResponse> composedRoutes(CommentRepository commentRepository) {
        return route()
                .GET("/api/comments", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(commentRepository.findAll(), Comment.class))
                .GET("/api/comments/book/{id}", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(
                                commentRepository.findAllByBook_Id(request.pathVariable("id")), Comment.class))
                .build();
    }
}