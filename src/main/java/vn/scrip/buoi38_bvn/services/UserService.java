package vn.scrip.buoi38_bvn.services;

import vn.scrip.buoi38_bvn.entites.Role;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public User register(User user) {
        user.setRole(Role.READER);
        return repo.save(user);
    }

    public User login(String email, String password) {
        User user = repo.findByEmail(email);
        if(user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    public User getById(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
    public User save(User user) { return repo.save(user); }
    public java.util.List<User> getAll() { return repo.findAll(); }
}
