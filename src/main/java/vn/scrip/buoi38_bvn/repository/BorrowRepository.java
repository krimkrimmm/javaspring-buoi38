package vn.scrip.buoi38_bvn.repository;

import vn.scrip.buoi38_bvn.entites.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId);
}
