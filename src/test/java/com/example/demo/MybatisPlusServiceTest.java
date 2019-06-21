package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.TestUser;
import com.example.demo.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description 测试mybatisPlus查询  常用的操作有curd接口和条件构造器，其中条件构造器包括（查询构造器和更新构造器）
 * @Author 何润强
 * @Data 2019/6/20 8:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusServiceTest {

    private QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();

    private int rows=0;
    @After
    public void printlnMsg(){
        //System.out.println("影响记录数："+rows);
    }
    @Autowired
    private UserService userService;
    @Test
    public void testSave(){
        TestUser user = new TestUser();
        user.setId(getId());
        user.setName("HRQ11");
        user.setAge("24");
        userService.save(user);
    }






    private String getId(){
        return System.currentTimeMillis()+"";
    }
}
