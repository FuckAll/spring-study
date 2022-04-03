package com.test.jta.mapper;

import com.test.jta.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuxiangdong
 */
@Mapper
public interface UserMapper {
    /**
     * 插入
     * @param user 用户
     * @return
     */
    int insert(User user);
}
