package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.CartItem;
import vn.scrip.buoi38_bvn.services.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final BookService service;
    public CartController(BookService service){ this.service = service; }

    @GetMapping
    public String viewCart(){ return "cart"; }

//    @PostMapping("/add/{id}")
//    public String add(@PathVariable Long id, HttpSession session){
//        Book b = service.getById(id);
//        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
//        if(cart == null) cart = new ArrayList<>();
//        cart.add(new CartItem(b,1));
//        session.setAttribute("cart", cart);
//        return "redirect:/cart";
//    }
}







