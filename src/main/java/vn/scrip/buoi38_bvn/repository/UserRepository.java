package vn.scrip.buoi38_bvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi38_bvn.entities.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
}