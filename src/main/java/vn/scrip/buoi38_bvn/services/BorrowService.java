package vn.scrip.buoi38_bvn.services;
import org.springframework.stereotype.Service;
import vn.scrip.buoi38_bvn.entities.*;
import vn.scrip.buoi38_bvn.repository.BorrowRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) {
        this.repo = repo;
    }

    public Borrow borrow(Borrow borrow) {
        borrow.setBorrowDate(LocalDate.now());
//        borrow.setReturned(false);
        return repo.save(borrow);
    }



    public Borrow saveOrders(List<CartItem> cartItemList, User user) {
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusYears(1));
        borrow.setStatus("BORROWED");
        for (CartItem item : cartItemList) {
            BorrowDetail borrowDetail = new BorrowDetail();
            borrowDetail.setBook(item.getBook());
            borrowDetail.setQuantity(item.getQuantity());
            borrowDetail.setBorrow(borrow);
            borrow.getBorrowDetails().add(borrowDetail);

//            Book book = item.getBook();
//            book.setQuantity(book.getQuantity() - item.getQuantity());
//            bookRepository.save(book);
        }
        return repo.save(borrow);
    }

    public void returnBook(Long borrowId) {
        Borrow borrow = repo.findById(borrowId).orElse(null);
        if (borrow != null) {
//            borrow.setReturned(true);
            repo.save(borrow);
        }
    }

    public List<Borrow> findByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Borrow> getAll() {
        return repo.findAll();
    }

    public Borrow getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}