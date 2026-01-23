package vn.scrip.buoi38_bvn.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
<<<<<<< HEAD
@Table(name = "borrows")
=======
@Getter
@Setter
@Table(name = "borrow")
>>>>>>> 31513c7b17ddf40d8746ad8a3b230501aa5905c4
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "borrow", cascade = CascadeType.ALL)
    private List<BorrowDetail> borrowDetails = new ArrayList<>();

    private LocalDate borrowDate;

<<<<<<< HEAD
    private boolean returned = false;

    // ===== Getter Setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
=======
    private LocalDate dueDate;
    private String status;

>>>>>>> 31513c7b17ddf40d8746ad8a3b230501aa5905c4
}