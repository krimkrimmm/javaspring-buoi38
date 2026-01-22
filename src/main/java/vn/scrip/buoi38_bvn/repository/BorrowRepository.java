package vn.scrip.buoi38_bvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi38_bvn.entities.Borrow;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId);
}
