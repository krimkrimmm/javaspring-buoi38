package vn.scrip.buoi38_bvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.scrip.buoi38_bvn.entities.Borrow;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    // Lấy danh sách mượn theo userId
    List<Borrow> findByUserId(Long userId);
}
