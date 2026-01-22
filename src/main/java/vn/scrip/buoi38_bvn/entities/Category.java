package vn.scrip.buoi38_bvn.entities;

import jakarta.persistence.*;
@Entity
@Table(name = "categories")
public class Category
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String color;
    // Getter Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
<<<<<<< HEAD:src/main/java/vn/scrip/buoi38_bvn/entities/Category.java
=======

    public String getColor(){ return color;}
    public void setColor(String color){this.color = color;}
}
>>>>>>> 5554403683ffe526db791dd694c5b1f66284e069:src/main/java/vn/scrip/buoi38_bvn/entites/Category.java

    public String getColor(){ return color;}
    public void setColor(String color){this.color = color;}
}