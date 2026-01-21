package vn.scrip.buoi38_bvn.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entites.*;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;

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
    public String viewCart() {
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, HttpSession session) {

        Book book = bookService.findById(id);
        if (book == null) return "redirect:/books";

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getBook().getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            cart.add(new CartItem(book, 1));
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }


    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (user == null || cart == null) return "redirect:/login";

        for (CartItem item : cart) {
            Borrow borrow = new Borrow();
            borrow.setUser(user);
            borrow.setBook(item.getBook());
            borrow.setQuantity(item.getQuantity());
            borrowService.borrow(borrow);

            Book book = item.getBook();
            book.setQuantity(book.getQuantity() - item.getQuantity());
            bookService.save(book);
        }

        session.removeAttribute("cart");
        return "redirect:/reader/borrows";
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public void updateQuantity(@RequestParam Long bookId,
                               @RequestParam int quantity,
                               HttpSession session) {

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) return;

        for (CartItem item : cart) {
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(quantity);
                break;
            }
        }

        session.setAttribute("cart", cart);
    }

}
