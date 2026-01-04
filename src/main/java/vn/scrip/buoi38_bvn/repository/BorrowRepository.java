package vn.scrip.buoi38_bvn.repository;

import vn.scrip.buoi38_bvn.entites.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId); // cần để findByUser gọi được
}