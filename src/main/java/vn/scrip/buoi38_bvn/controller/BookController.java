package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", service.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("book", service.findById(id));
        return "book-detail";
    }
}
