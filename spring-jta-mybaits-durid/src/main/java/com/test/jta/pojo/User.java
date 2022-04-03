package com.test.jta.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author wuxiangdong
 */
@Data
@ToString
public class User {
    private Integer id;
    private String username;
    private Integer age;
}