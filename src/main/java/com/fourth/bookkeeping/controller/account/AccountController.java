package com.fourth.bookkeeping.controller.account;

import com.fourth.bookkeeping.domain.User;
import com.fourth.bookkeeping.reposity.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class AccountController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RedisTemplate<String, User> userRedisTemplate;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
    public boolean login(@PathVariable String username, HttpSession session){

        User user = userRepository.findUsersByName(username);
        if(user == null){
            return false;
        }
        //假装进行了登录校验
        userRedisTemplate.opsForValue().set(session.getId(), user);
        System.out.println(userRedisTemplate.opsForValue().get("key"));
        return true;

    }
}
