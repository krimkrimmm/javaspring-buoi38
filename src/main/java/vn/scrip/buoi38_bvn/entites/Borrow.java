package vn.scrip.buoi38_bvn.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
    @Getter
@Setter
    public class Borrow {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private User user;

        @ManyToOne
        private Book book;

        private LocalDate borrowDate;
        private LocalDate returnDate;
    }

