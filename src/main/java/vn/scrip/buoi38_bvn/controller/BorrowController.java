package vn.scrip.buoi38_bvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;
    private final BookService bookService;

    public BorrowController(BorrowService borrowService, BookService bookService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    @PostMapping("/add/{bookId}")
    public String borrowBook(@PathVariable Long bookId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        Book book = bookService.getById(bookId);

        if (user != null && book != null) {
            if (book.getQuantity() > 0) {
                Borrow borrow = new Borrow();
                borrow.setUser(user);
                borrow.setBook(book);
                borrow.setQuantity(1);
                borrow.setBorrowDate(java.time.LocalDate.now());

                // 🔹 Sửa dòng này:
                borrowService.borrow(borrow);  // trước đây là borrowBook(borrow)

                // Giảm số lượng sách
                book.setQuantity(book.getQuantity() - 1);
                bookService.save(book);

                redirectAttributes.addFlashAttribute("successMessage",
                        "Bạn đã mượn sách '" + book.getTitle() + "' thành công!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Sách '" + book.getTitle() + "' hiện đã hết!");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Không thể mượn sách. Vui lòng đăng nhập hoặc kiểm tra sách.");
        }

        return "redirect:/reader/borrows";
    }
}