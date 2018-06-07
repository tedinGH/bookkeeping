package com.fourth.bookkeeping.controller.book;

import com.fourth.bookkeeping.domain.Book;
import com.fourth.bookkeeping.reposity.BookRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("book")
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(Book book){
        book.setOptime(new Date());
        book.setUserId(1);
        bookRepository.save(book);
        return true;
    }
}
