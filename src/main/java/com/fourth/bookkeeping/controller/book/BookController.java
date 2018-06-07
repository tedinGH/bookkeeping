package com.fourth.bookkeeping.controller.book;

import com.fourth.bookkeeping.domain.Book;
import com.fourth.bookkeeping.reposity.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public List<Book> get(@PathVariable int userId){
        List<Book> books = bookRepository.findByUserIdOrderByOptimeDesc(userId);
//        Page<Book> books = bookRepository.findAll(PageRequest.of(0,10,new Sort(Sort.Direction.DESC, "optime")));
//        return books.getContent();
        return books;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public boolean update(Book book){
        book.setOptime(new Date());
        bookRepository.save(book);
        return true;
    }
}
