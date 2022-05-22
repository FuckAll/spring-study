package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.User;
import com.example.dao.User2;
import com.example.mapper.UserMapper;
import com.example.mapper.UserMapper2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@EnableTransactionManagement
@SpringBootTest
@ComponentScan("com.example")
public class CrudTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapper2 userMapper2;
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
    public void testOrderBy() {
        final List<User> users = userMapper.selectList(new QueryWrapper<User>().orderByAsc("id"));
        assertThat(users).isNotNull();
        final List<User> users2 = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getId));
        assertThat(users2).isNotNull();
    }

    @Test
    public void testSelectMaps() {
        List<Map<String, Object>> mapList = userMapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        assertThat(mapList).isNotEmpty();
        assertThat(mapList.get(0)).isNotEmpty();
        System.out.println(mapList.get(0));
    }

    @Test
    public void selectMapsPage() {
        IPage<Map<String, Object>> page = userMapper.selectMapsPage(new Page<>(0, 3), Wrappers.<User>query().orderByAsc("id"));
        assertThat(page).isNotNull();
        assertThat(page.getRecords()).isNotEmpty();
        assertThat(page.getRecords().get(0)).isNotEmpty();

        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }

    @Test
    public void selectMaxId() {
        final User user = userMapper.selectOne(new QueryWrapper<>(new User()).select("max(id) as id"));
        System.out.println("user = " + user);
    }

    @Test
    public void testGroupBy() {
        //1. QueryWrapper
        final QueryWrapper<User> groupByAge = new QueryWrapper<User>().select("age , count(*) cnt").groupBy("age");
        final List<Map<String, Object>> results = userMapper.selectMaps(groupByAge);
        System.out.println("maps = " + results);
        results.forEach(c -> System.out.println("c= " + c));

        //3. LambdaQuery 没法用count
        final LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery().select(User::getAge).groupBy(User::getId);
        final List<Map<String, Object>> lists = userMapper.selectMaps(userLambdaQueryWrapper);
        lists.forEach(c -> System.out.println("c = " + c));
    }

    @Test
    public void testSqlCondition() {
        Assertions.assertEquals(userMapper2.selectList(Wrappers.<User2>query()
                .setEntity(new User2().setName("n"))).size(), 2);
        Assertions.assertEquals(userMapper2.selectList(Wrappers.<User2>query().like("name", "J")).size(), 2);
        Assertions.assertEquals(userMapper2.selectList(Wrappers.<User2>query().gt("age", 18)
                .setEntity(new User2().setName("J"))).size(), 1);
    }
}