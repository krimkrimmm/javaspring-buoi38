package vn.scrip.buoi38_bvn.services;

import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;
    public BookService(BookRepository repo) { this.repo = repo; }

    public List<Book> getAll() { return repo.findAll(); }
    public Book getById(Long id) { return repo.findById(id).orElse(null); }
    public Book save(Book book) { return repo.save(book); }
    public void delete(Long id) { repo.deleteById(id); }
}
