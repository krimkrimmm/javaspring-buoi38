package vn.scrip.buoi38_bvn.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entites.*;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    private final BorrowService borrowService;
    private final BookService bookService;

    public ReaderController(BorrowService borrowService, BookService bookService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    @GetMapping("/borrows")
    public String viewBorrows(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("borrows", borrowService.findByUser(user.getId()));
        }
        return "reader-borrows";
    }

    @PostMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Book book = bookService.findById(bookId); // ✅ SỬA

        if (user != null && book != null && book.getQuantity() > 0) {
            Borrow borrow = new Borrow();
            borrow.setUser(user);
            borrow.setBook(book);
            borrow.setQuantity(1);
            borrowService.borrow(borrow);

            book.setQuantity(book.getQuantity() - 1);
            bookService.save(book);
        }
        return "redirect:/reader/borrows";
    }

    @PostMapping("/return/{borrowId}")
    public String returnBook(@PathVariable Long borrowId) {
        Borrow borrow = borrowService.getById(borrowId);
        if (borrow != null) {
            borrowService.returnBook(borrowId);

            Book book = borrow.getBook();
            book.setQuantity(book.getQuantity() + 1);
            bookService.save(book);
        }
        return "redirect:/reader/borrows";
    }
}
