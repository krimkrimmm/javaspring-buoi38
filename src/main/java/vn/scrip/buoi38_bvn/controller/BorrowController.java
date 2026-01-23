package vn.scrip.buoi38_bvn.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.scrip.buoi38_bvn.entities.Book;
import vn.scrip.buoi38_bvn.entities.Borrow;
import vn.scrip.buoi38_bvn.entities.User;
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

    @PostMapping("/add/{bookId}")
    public String borrowBook(@PathVariable Long bookId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        Book book = bookService.findById(bookId); // ✅ SỬA Ở ĐÂY

        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng đăng nhập");
            return "redirect:/login";
        }

        if (book == null || book.getQuantity() <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sách đã hết");
            return "redirect:/books";
        }

        Borrow borrow = new Borrow();
        borrow.setUser(user);
//        borrow.setBook(book);
//        borrow.setQuantity(1);
        borrow.setBorrowDate(LocalDate.now());

        borrowService.borrow(borrow);

        book.setQuantity(book.getQuantity() - 1);
        bookService.save(book);

        redirectAttributes.addFlashAttribute("successMessage", "Mượn sách thành công");
        return "redirect:/reader/borrows";
    }
}