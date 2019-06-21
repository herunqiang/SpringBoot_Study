package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import java.sql.Date;

public class TestUser {

  private String id;
  private String name;
  private String age;
  @TableField(strategy = FieldStrategy.NOT_EMPTY)
  private String email;

  private String managerId;
  private java.sql.Date createTime;
  //非表字段
  @TableField(exist=false)
  private String temp;
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getManagerId() {
    return managerId;
  }

  public void setManagerId(String managerId) {
    this.managerId = managerId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getTemp() {
    return temp;
  }

  public void setTemp(String temp) {
    this.temp = temp;
  }

  @Override
  public String toString() {
    return "TestUser{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age='" + age + '\'' +
            ", email='" + email + '\'' +
            ", managerId='" + managerId + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}
