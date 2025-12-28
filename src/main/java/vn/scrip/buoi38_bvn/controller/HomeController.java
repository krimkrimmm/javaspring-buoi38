package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookRepository bookRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }
}
