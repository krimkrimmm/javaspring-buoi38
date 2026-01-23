package vn.scrip.buoi38_bvn.services;
import vn.scrip.buoi38_bvn.entities.Book;
import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void delete(Long id);
}