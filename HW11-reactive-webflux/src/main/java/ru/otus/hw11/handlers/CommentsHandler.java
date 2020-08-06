package ru.otus.hw11.handlers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.hw11.models.Comment;
import ru.otus.hw11.repositories.CommentRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class CommentsHandler {
    @Bean
    public RouterFunction<ServerResponse> composedRoutes(CommentRepository repository) {
        return route()
                /*.GET("/func/person", queryParam("name", StringUtils::isNotEmpty),
                        request -> request.queryParam("name")
                                .map(repository::findAllByLastName)
                                .map(persons -> ok().body(persons, Person.class))
                                .orElse(notFound().build())
                )*/
                .GET("/api/comments", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(repository.findAll(), Comment.class))
                .GET("api/comments/book/{id}", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(
                                repository.findAllByBook_Id(request.pathVariable("id")), Comment.class))
                /*.GET("/func/person/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(request.pathVariable("id"))
                                .flatMap(person -> ok().contentType(APPLICATION_JSON).body(fromObject(person)))
                )*/.build();
    }
}