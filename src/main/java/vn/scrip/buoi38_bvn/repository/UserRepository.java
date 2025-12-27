package vn.scrip.buoi38_bvn.repository;

import vn.scrip.buoi38_bvn.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
