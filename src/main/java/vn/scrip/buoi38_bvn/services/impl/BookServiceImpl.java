package vn.scrip.buoi38_bvn.services.impl;

import org.springframework.stereotype.Service;
import vn.scrip.buoi38_bvn.entites.Book;
import vn.scrip.buoi38_bvn.repository.BookRepository;
import vn.scrip.buoi38_bvn.services.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Book> findAll() {
        return repo.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return repo.save(book);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
