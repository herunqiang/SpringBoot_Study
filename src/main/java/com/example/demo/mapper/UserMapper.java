package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TestUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author 何润强
 * @Data 2019/6/19 17:10
 */
public interface UserMapper extends BaseMapper<TestUser> {
    @Select("select * from test_user")
    public List<TestUser> getAllTest();
}

