package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService service;

    public AdminController(BookService service) { this.service = service; }

    @GetMapping("/books")
    public String manageBooks(Model model) {
        model.addAttribute("books", service.getAll());
        return "admin-books";
    }

    @PostMapping("/books/save")
    public String save(Book book) {
        service.save(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/admin/books";
    }
}



