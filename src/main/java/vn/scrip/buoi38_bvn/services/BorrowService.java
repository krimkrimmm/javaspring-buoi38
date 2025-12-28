package vn.scrip.buoi38_bvn.services;

import org.springframework.stereotype.Service;
import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.repository.BorrowRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) {
        this.repo = repo;
    }

    public Borrow borrow(Borrow borrow) {
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturned(false);
        return repo.save(borrow);
    }

    public void returnBook(Long borrowId) {
        Borrow borrow = repo.findById(borrowId).orElse(null);
        if (borrow != null) {
            borrow.setReturned(true);
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
