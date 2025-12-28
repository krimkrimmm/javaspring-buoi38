package vn.scrip.buoi38_bvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi38_bvn.entites.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}

