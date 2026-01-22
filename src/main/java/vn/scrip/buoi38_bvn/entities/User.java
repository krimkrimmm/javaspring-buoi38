package vn.scrip.buoi38_bvn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.scrip.buoi38_bvn.model.UserRole;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
