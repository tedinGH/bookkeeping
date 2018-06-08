package com.fourth.bookkeeping.reposity;

import com.fourth.bookkeeping.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findUsersByName(String name);
}
