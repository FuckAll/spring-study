package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author izgnod
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
