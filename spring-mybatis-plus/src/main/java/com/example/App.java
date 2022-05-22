package com.example;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.User;
import com.example.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * Hello world!
 *
 * @author izgnod
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
public class App {
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(App.class);
        final App app = (App) context.getBean("app");
        app.selectMapsPage();
    }

    public void selectMapsPage() {
        IPage<Map<String, Object>> page = userMapper.selectMapsPage(new Page<>(1, 3), Wrappers.<User>query().orderByAsc("id"));
//        assertThat(page).isNotNull();
//        assertThat(page.getRecords()).isNotEmpty();
//        assertThat(page.getRecords().get(0)).isNotEmpty();

        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
}


