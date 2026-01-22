package vn.scrip.buoi38_bvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entities.User;
import vn.scrip.buoi38_bvn.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Trang quản lý user
    @GetMapping("/users")
    public String manageUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-users"; // Tên template Thymeleaf
    }

    // Cập nhật role
    @PostMapping("/users/update-role/{id}")
    public String updateRole(@PathVariable Long id, @RequestParam String role) {
        userService.updateRole(id, role);
        return "redirect:/admin/users";
    }

    // Xóa user
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";

    }
    //bcao- thong ke
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        return "admin-dashboard";
    }

}