package vn.scrip.buoi38_bvn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private double price;
    private int quantity;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Integer status = 1;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}
