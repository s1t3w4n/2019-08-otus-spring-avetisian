package ru.otus.hw11.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/update")
    public String editPage(@RequestParam("id") String id) {
        return "update";
    }

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @GetMapping("/read")
    public String readPage() {
        return "read";
    }
}
