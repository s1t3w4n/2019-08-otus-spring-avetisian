package ru.otus.hw11.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @GetMapping("/comment")
    public String addCommentPage(@RequestParam("id") String id) {
        return "comment";
    }

    @GetMapping("/comments")
    public String commentsListPage() {
        return "commentsList";
    }

}
