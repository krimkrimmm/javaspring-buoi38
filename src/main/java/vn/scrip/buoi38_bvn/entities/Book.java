package vn.scrip.buoi38_bvn.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    @Column(name = "publish_year")
    private Integer publishYear;
    private BigDecimal price;
    private Integer quantity;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Integer status = 1;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Getter Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
<<<<<<< HEAD:src/main/java/vn/scrip/buoi38_bvn/entities/Book.java
}
=======
}


>>>>>>> 5554403683ffe526db791dd694c5b1f66284e069:src/main/java/vn/scrip/buoi38_bvn/entites/Book.java
