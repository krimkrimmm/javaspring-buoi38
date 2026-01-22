package vn.scrip.buoi38_bvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi38_bvn.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
