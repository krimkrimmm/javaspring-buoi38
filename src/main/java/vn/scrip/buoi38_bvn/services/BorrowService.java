package vn.scrip.buoi38_bvn.services;

import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) { this.repo = repo; }

    public Borrow borrowBook(Borrow borrow) {
        borrow.setBorrowDate(LocalDate.now());
        borrow.setStatus("Đang mượn");
        return repo.save(borrow);
    }

    public Borrow returnBook(Borrow borrow) {
        borrow.setReturnDate(LocalDate.now());
        borrow.setStatus("Đã trả");
        return repo.save(borrow);
    }

    public List<Borrow> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Borrow> getAll() {
        return repo.findAll();
    }
}



