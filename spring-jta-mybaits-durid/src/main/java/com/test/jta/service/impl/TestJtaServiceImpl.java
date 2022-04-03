package com.test.jta.service.impl;

import com.test.jta.aop.DataSource;
import com.test.jta.aop.DataSourceNames;
import com.test.jta.mapper.UserMapper;
import com.test.jta.pojo.User;
import com.test.jta.service.TestJtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiangdong
 */
@Service
public class TestJtaServiceImpl implements TestJtaService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void testInsertUser(User user) {
        int insertNum = userMapper.insert(user);
        System.out.println("插入成功,条数：" + insertNum);
    }

    @DataSource(DataSourceNames.DataSourceTwo)
    @Override
    public void testInsertUser2(User user) {
        int insertNum = userMapper.insert(user);
        System.out.println("插入成功,条数：" + insertNum);
    }
}
