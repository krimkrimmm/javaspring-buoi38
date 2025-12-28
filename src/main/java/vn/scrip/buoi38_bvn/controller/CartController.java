package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.entites.CartItem;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final BookService bookService;
    private final BorrowService borrowService;

    public CartController(BookService bookService, BorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public String viewCart() { return "cart"; }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, HttpSession session) {
        Book book = bookService.getById(id);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if(cart == null) cart = new ArrayList<>();
        cart.add(new CartItem(book, 1));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if(user != null && cart != null) {
            for(CartItem item : cart) {
                Borrow borrow = new Borrow();
                borrow.setBook(item.getBook());
                borrow.setUser(user);
                borrow.setQuantity(item.getQuantity());
                borrowService.borrow(borrow);

                Book book = item.getBook();
                book.setQuantity(book.getQuantity() - item.getQuantity());
                bookService.save(book);
            }
            session.removeAttribute("cart");
        }
        return "redirect:/reader/borrows";
    }
}