package vn.scrip.buoi38_bvn.services;
import org.springframework.stereotype.Service;
import vn.scrip.buoi38_bvn.entities.Role;
import vn.scrip.buoi38_bvn.entities.User;
import vn.scrip.buoi38_bvn.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // Đăng ký user mới
    public User register(User user) {
        if (repo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email đã tồn tại");
        }
        user.setRole(Role.READER);
        user.setStatus(1);
        return repo.save(user);
    }


    // Đăng nhập
    public User login(String email, String password) {
        User user = repo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Lấy danh sách tất cả user
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // Cập nhật role của user
    public void updateRole(Long id, String roleStr) {
        User user = repo.findById(id).orElse(null);
        if (user != null) {
            try {
                Role role = Role.valueOf(roleStr.toUpperCase());
                user.setRole(role);
                repo.save(user);
            } catch (IllegalArgumentException e) {
                System.out.println("Role không hợp lệ: " + roleStr);
            }
        }
    }

    // Xóa user
    public void delete(Long id) {
        repo.deleteById(id);
    }
}