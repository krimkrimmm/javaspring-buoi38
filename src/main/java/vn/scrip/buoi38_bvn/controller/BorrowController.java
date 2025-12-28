package vn.scrip.buoi38_bvn.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;

import java.time.LocalDate;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;
    private final BookService bookService;

    public BorrowController(BorrowService borrowService, BookService bookService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    @GetMapping
    public String viewBorrowCart(HttpSession session, Model model) {
        model.addAttribute("cart", session.getAttribute("cart"));
        return "borrow-cart";
    }

    @PostMapping("/add/{id}")
    public String addToBorrowCart(@PathVariable Long id, HttpSession session) {
        Book book = bookService.getById(id);
        if (book == null) return "redirect:/books";

        @SuppressWarnings("unchecked")
        java.util.List<BorrowItem> cart = (java.util.List<BorrowItem>) session.getAttribute("cart");
        if (cart == null) cart = new java.util.ArrayList<>();
        cart.add(new BorrowItem(book, 1));
        session.setAttribute("cart", cart);

        return "redirect:/borrow";
    }

    @PostMapping("/confirm")
    public String confirmBorrow(HttpSession session) {
        @SuppressWarnings("unchecked")
        java.util.List<BorrowItem> cart = (java.util.List<BorrowItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) return "redirect:/borrow";

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        for (BorrowItem item : cart) {
            Borrow borrow = new Borrow();
            borrow.setUser(user);
            borrow.setBook(item.getBook());
            borrow.setQuantity(item.getQuantity());
//            borrowService.borrow(borrow);

            item.getBook().setQuantity(item.getBook().getQuantity() - item.getQuantity());
            bookService.save(item.getBook());
        }

        session.removeAttribute("cart");
        return "redirect:/borrow/history";
    }

    @GetMapping("/history")
    public String borrowHistory(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

//        model.addAttribute("borrows", borrowService.getBorrowsByUser(user));
        return "borrow-history";
    }

    public static class BorrowItem {
        private Book book;
        private int quantity;

        public BorrowItem(Book book, int quantity) { this.book = book; this.quantity = quantity; }
        public Book getBook() { return book; }
        public void setBook(Book book) { this.book = book; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
