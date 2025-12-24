package vn.scrip.buoi38_bvn.repository;
import vn.scrip.buoi38_bvn.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

