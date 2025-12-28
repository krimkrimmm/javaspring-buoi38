package vn.scrip.buoi38_bvn.controller;
import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    private final BookService bookService;
    private final BorrowService borrowService;

    public LibrarianController(BookService bookService, BorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("borrows", borrowService.getAll());
        return "librarian-dashboard";
    }

    @PostMapping("/books/save")
    public String saveBook(Book book) {
        bookService.save(book);
        return "redirect:/librarian";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/librarian";
    }

    @PostMapping("/borrows/return/{id}")
    public String returnBorrow(@PathVariable Long id) {
        Borrow borrow = borrowService.getAll().stream()
                .filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        if(borrow != null) borrowService.returnBook(borrow);
        return "redirect:/librarian";
    }
}