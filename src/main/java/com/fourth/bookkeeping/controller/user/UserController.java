package com.fourth.bookkeeping.controller.user;

import com.fourth.bookkeeping.domain.User;
import com.fourth.bookkeeping.reposity.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping(value = "/demo")
    public String demo(){
        return "1";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public boolean add(User user) {
        userRepository.save(user);
        return true;
    }

}
