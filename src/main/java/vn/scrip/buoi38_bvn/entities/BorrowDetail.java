package vn.scrip.buoi38_bvn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "borrow_detail")
public class BorrowDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;

    @ManyToOne
    private Book book;

    private int quantity;
<<<<<<< HEAD
}
=======
}
>>>>>>> 31513c7b17ddf40d8746ad8a3b230501aa5905c4
