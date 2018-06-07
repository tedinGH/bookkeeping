package com.fourth.bookkeeping.reposity;

import com.fourth.bookkeeping.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer>{
}
