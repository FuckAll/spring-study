package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dao.User;
import org.springframework.stereotype.Repository;

/**
 * @author izgnod
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
