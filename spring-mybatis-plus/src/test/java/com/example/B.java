package com.example;

import com.example.dao.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class B {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertB() {
        User user = new User();
        user.setName("小羊");
        user.setAge(3);
        user.setEmail("abc@mp.com");
        userMapper.insert(user);
        throw new RuntimeException("good");
    }
}
