package vn.scrip.buoi38_bvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String denied() {
        return "access-denied";
    }
}






