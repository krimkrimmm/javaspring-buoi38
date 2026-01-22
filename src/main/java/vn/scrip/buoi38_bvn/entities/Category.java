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

    public String getColor(){ return color;}
    public void setColor(String color){this.color = color;}
}