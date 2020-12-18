package com.xuexi.zixue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@PropertySource("classpath:application.properties")
class ZixueApplicationTests {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String userpassword;

    @Test
    void contextLoads() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(userpassword);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql ="SELECT COUNT(*) FROM `user`";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(count);
    }

}
