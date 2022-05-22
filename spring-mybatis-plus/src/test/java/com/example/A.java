package com.example;

import com.example.dao.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class A {

    @Autowired
    private B b;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertA() {
        User user = new User();
        user.setName("小猪");
        user.setAge(18);
        user.setEmail("zhuzhu@mp.com");
        userMapper.insert(user);
//        try {
//        b.insertB();
//        } catch (Exception e) {
//
//        }
    }
}
