package com.fourth.bookkeeping.controller.book;

import com.fourth.bookkeeping.domain.Book;
import com.fourth.bookkeeping.domain.User;
import com.fourth.bookkeeping.reposity.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private RedisTemplate<String, User> userRedisTemplate;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean add(Book book, HttpSession session){
        User user = userRedisTemplate.opsForValue().get(session.getId());
        book.setOptime(new Date());
        book.setUserId(user.getId());
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
    public boolean update(Book book, HttpSession session){
        User user = userRedisTemplate.opsForValue().get(session.getId());
        if(user.getId() != book.getUserId()){
            return false;
        }
        book.setOptime(new Date());
        bookRepository.save(book);
        return true;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable int id, HttpSession session){
        Book book = bookRepository.findById(id).get();
        User user = userRedisTemplate.opsForValue().get(session.getId());
        if(user.getId() != book.getUserId()){
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }
}
