package vn.scrip.buoi38_bvn.services;

import vn.scrip.buoi38_bvn.entities.Category;
import vn.scrip.buoi38_bvn.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) { this.repo = repo; }

    public List<Category> getAll() { return repo.findAll(); }
    public Category getById(Long id) { return repo.findById(id).orElse(null); }
    public Category save(Category c) { return repo.save(c); }

    public void delete(Long id) { repo.deleteById(id); }
}