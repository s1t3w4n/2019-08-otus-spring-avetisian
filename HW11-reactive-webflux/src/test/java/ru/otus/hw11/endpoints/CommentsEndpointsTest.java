package ru.otus.hw11.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

@SuppressWarnings("rawtypes")
@SpringBootTest
class CommentsEndpointsTest {
    public static final String BOOK_ID = "5f3009a749f3ae78c5195dee";
    @Autowired
    private RouterFunction route;

    @Test
    void getAllCommentsURL() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/comments")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getBookCommentsURL() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/comments/book/" + BOOK_ID)
                .exchange()
                .expectStatus()
                .isOk();
    }
}