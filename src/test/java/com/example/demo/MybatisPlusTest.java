package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.UserMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description  MP的相关测试
 * @Author 何润强
 * @Data 2019/6/19 15:16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testStart (){
        List<TestUser> testUsers = userMapper.selectList(null);
        testUsers.forEach(System.out::println);
    }
    @Test
    public void  allEq(){
        LambdaQueryWrapper lambda = new QueryWrapper().lambda();
        List list = userMapper.selectList(lambda);
        list.forEach(System.out::println);
    }
    @Test
    public  void  testCustomSql (){
        List<TestUser> allTest = userMapper.getAllTest();
        allTest.forEach(System.out::println);
    }

}
