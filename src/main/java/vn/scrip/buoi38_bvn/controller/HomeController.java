package vn.scrip.buoi38_bvn.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.scrip.buoi38_bvn.services.BookService;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "index";
    }
}
