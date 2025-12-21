package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", repo.findAll());
        return "books";
    }
}
