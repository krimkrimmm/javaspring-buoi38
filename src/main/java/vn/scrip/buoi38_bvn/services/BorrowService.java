package vn.scrip.buoi38_bvn.services;

import vn.scrip.buoi38_bvn.entites.Borrow;
import vn.scrip.buoi38_bvn.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {

    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) { this.repo = repo; }

    public Borrow borrowBook(Borrow borrow) {
        borrow.setBorrowDate(LocalDate.now());
        return repo.save(borrow);
    }
}
