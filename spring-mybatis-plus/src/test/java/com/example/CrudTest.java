package com.example;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.User;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@EnableTransactionManagement
@SpringBootTest
public class CrudTest {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private A a;

    @Autowired
    private B b;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        assertThat(userMapper.selectList(Wrappers.<User>lambdaQuery()
                .in(User::getId, Arrays.asList(1L, 2L, 3L, 4L, 5L)))).size().isEqualTo(5);

        userMapper.selectList(new QueryWrapper<User>().lambda().select(User::getId, User::getAge)).forEach(c -> {
            assertThat(c.getId()).isNotNull();
            assertThat(c.getName()).isNull();
            assertThat(c.getAge()).isNotNull();
            assertThat(c.getEmail()).isNull();

        });

        assertThat(userMapper.selectById(1L).getEmail()).isEqualTo("test1@baomidou.com");
        assertThat(userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, 1L)).getEmail()).isEqualTo("test1@baomidou.com");
        assertThat(userMapper.selectOne(Wrappers.lambdaQuery(new User().setId(1L))).getEmail()).isEqualTo("test1@baomidou.com");
        assertThat(userMapper.selectOne(Wrappers.lambdaQuery(new User().setName("Jone"))).getEmail()).isEqualTo("test1@baomidou.com");
        assertThat(userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1L)).getEmail()).isEqualTo("test1@baomidou.com");
    }

    @Test
    public void testDelete() {
        assertThat(userMapper.deleteById(5L)).isGreaterThan(0);
        assertThat(userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getId, 5L))).isEqualTo(0L);
    }

    @Test
    public void testUpdate() {
        assertThat(userMapper.updateById(new User().setId(1L).setName("update"))).isGreaterThan(0);
        assertThat(userMapper.update(User.builder().name("update1").build(), new UpdateWrapper<User>().lambda()
                .set(User::getAge, 18).eq(User::getId, 1L))).isGreaterThan(0);
        assertThat(userMapper.update(null, new UpdateWrapper<User>().lambda()
                .set(User::getAge, 19).eq(User::getId, 1L))).isGreaterThan(0);
        assertThat(userMapper.update(null, Wrappers.<User>lambdaUpdate()
                .set(User::getName, "zhangsan").eq(User::getId, 1L))).isGreaterThan(0);
        assertThat(userMapper.update(null, Wrappers.lambdaUpdate(new User().setId(1L))
                .set(User::getName, "lisi"))).isGreaterThan(0);
        assertThat(userMapper.update(null, Wrappers.lambdaUpdate(User.class)
                .set(User::getName, "lisi").eq(User::getId, 1L))).isGreaterThan(0);
    }


    @Test
    public void testInsert() {
        User user = new User();
        user.setName("小羊");
        user.setAge(3);
        user.setEmail("abc@mp.com");
        assertThat(userMapper.insert(user)).isEqualTo(1);
        System.out.println("userMapper.selectList(null) = " + userMapper.selectList(null));
//        assertThat(user.getId()).isEqualTo(6L);
    }

    @Test
    public void testTransactional() {
        try {
            a.insertA();
        } catch (Exception e) {
            System.out.println("异常 = " + userMapper.selectList(null));
        }

        System.out.println("非异常 = " + userMapper.selectList(null));
    }

    @Test
    public void testNo() {
        a.insertA();
        b.insertB();
    }


//    @Test
//    public void testBatchUpdate() {
//        System.out.println("------------ testBatchUpdate ----------");
//        Assert.assertNotNull(userService);
//        final List<User> users = Arrays.asList(
//                User.builder().id(1L).age(99).build(),
//                User.builder().id(2L).age(99).build(),
//                User.builder().id(1L).age(99).build()
//        );
//        userService.updateBatchById(users);
//        System.out.println("------------ testBatchUpdate success ----------");
//
//        final List<User> list = userService.lambdaQuery().in(User::getId, 1L, 2L, 3L).list();
//        System.out.println("update result list = " + list);
//    }
//
//    @Test
//    public void bDelete() {
//        final int i = userMapper.deleteById(3L);
//        assertThat.
//
//
//    }

}