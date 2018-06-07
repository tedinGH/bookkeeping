package com.fourth.bookkeeping.reposity;

import com.fourth.bookkeeping.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer>{

    List<Book> findByUserIdOrderByOptimeDesc(int userId);

}
