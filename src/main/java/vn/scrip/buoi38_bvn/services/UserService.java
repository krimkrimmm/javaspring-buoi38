package vn.scrip.buoi38_bvn.services;

import vn.scrip.buoi38_bvn.entites.Role;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User u) {
        u.setRole(Role.CUSTOMER);
        return repo.save(u);
    }

    public User login(String email, String password) {
        User u = repo.findByEmail(email);
        if (u != null && u.getPassword().equals(password)) return u;
        return null;
    }
}
