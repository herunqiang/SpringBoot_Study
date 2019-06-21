package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author 何润强
 * @Data 2019/6/20 10:42
 */
@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper,TestUser> {
}
