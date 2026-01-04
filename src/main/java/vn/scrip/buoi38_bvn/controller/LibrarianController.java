package vn.scrip.buoi38_bvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;

import java.util.List;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    private final BookService bookService;
    private final BorrowService borrowService;

    public LibrarianController(BookService bookService, BorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping("/books")
    public String manageBooks(Model model) {
        model.addAttribute("books", bookService.findAll()); // ✅ SỬA
        return "librarian-books";
    }

    @PostMapping("/books/save")
    public String saveBook(Book book) {
        bookService.save(book);
        return "redirect:/librarian/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/librarian/books";
    }

    @GetMapping("/borrows")
    public String viewBorrows(Model model) {
        List<Borrow> borrows = borrowService.getAll();
        model.addAttribute("borrows", borrows);
        return "librarian-borrows";
    }

    @PostMapping("/borrows/return/{id}")
    public String returnBook(@PathVariable Long id) {
        borrowService.returnBook(id);
        return "redirect:/librarian/borrows";
    }
}
