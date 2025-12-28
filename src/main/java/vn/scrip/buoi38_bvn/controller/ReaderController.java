package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.entites.CartItem;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    private final BookService bookService;
    private final BorrowService borrowService;

    public ReaderController(BookService bookService, BorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public String dashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("borrows", borrowService.getByUser(user.getId()));
        return "reader-dashboard";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book-detail";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Book book = bookService.getById(id);
//        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
//        if (cart == null) cart = new ArrayList<>();
//        cart.add(new CartItem(book, 1));
//        session.setAttribute("cart", cart);
        return "redirect:/reader/cart";
    }

    @GetMapping("/cart")
    public String viewCart() {
        return "cart";
    }

    @PostMapping("/borrow")
    public String borrow(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        if (cart != null) {
            for (CartItem item : cart) {
                Borrow borrow = new Borrow();
                borrow.setUser(user);
//                borrow.setBook(item.getBook());
                borrow.setQuantity(item.getQuantity());
                borrowService.borrowBook(borrow);

//                item.getBook().setQuantity(item.getBook().getQuantity() - item.getQuantity());
//                bookService.save(item.getBook());
            }
            cart.clear();
            session.setAttribute("cart", cart);
        }
        return "redirect:/reader";
    }
}