package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.UserMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 测试mybatisPlus查询  常用的操作有curd接口和条件构造器，其中条件构造器包括（查询构造器和更新构造器）
 * @Author 何润强
 * @Data 2019/6/20 8:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusMapperTest {
    @Autowired
    private UserMapper userMapper;
    private QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();

    private int rows=0;
    @After
    public void printlnMsg(){
        System.out.println("影响记录数："+rows);
    }
    /**-------------------------------------------mapper----------------------------------------------------------**/
    /**
     * 插入时候传入对象是动态拼接sql
     */
    @Test
    public void testInsert(){
        TestUser user = new TestUser();
        user.setId(getId());
        user.setName("HRQ");
        user.setAge("24");
        user.setEmail("HRQ@qq.com");
        this.rows = userMapper.insert(user);
    }

    /**
     * byId这种情况可能会出现问题，如果是没有id这个字段的话会出现   delete from table_name where null= ?
     */
    @Test
    public void testDelete(){
        rows = userMapper.deleteById("1087982257332887553");//byList
    }

    /**
     * 通过map集合查询key表示字段名而非属性名(mybatisPlus貌似都是字段名)
     */
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "HRQ");
        rows = userMapper.deleteByMap(params);
    }

    /**
     * 查询一条记录，如果是查询出多条的话会抛异常
     */
    @Test
    public void testSelectOne(){
        queryWrapper.like("name","王天风");
        TestUser testUser = userMapper.selectOne(queryWrapper);
        System.out.println(testUser);
    }

    @Test
    public void testSelectMaps(){
        queryWrapper.like("name","王天风");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        System.out.println(maps.size());
    }
    @Test
    public void testSelectByQueryMaper(){
        TestUser user = new TestUser();
        user.setAge("31");
        queryWrapper = new QueryWrapper<>(user);

        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
        testUsers.forEach(System.out::println);
    }

    /**
     * 应用场景，在使用条件构造器的时候如果条件值为空则添加该条件，通过condition参数控制（false,默认为true）
     */
    @Test
    public void testSelectByQueryWrapper(){
        queryWrapper.like(false,"name",null);

        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
        testUsers.forEach((user)->{
            System.out.println(user);
        });
    }

    /**
     * sql 嵌套
     */
    @Test
    public void testNestedByOr(){
        queryWrapper.like("name", "HRQ").or(i -> i.eq("name", "李白").ne("name", "活着").like("id","1111"));
        String sqlSelect = queryWrapper.getSqlSelect();
        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
    }
    /**
     * sql 嵌套
     */
    @Test
    public void testNested(){
        queryWrapper.like("name","王天风").nested(queryWrapper->{
            return queryWrapper.eq("id","1561002330035").eq("age","24");
        });
        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
        testUsers.forEach( System.out::println);
    }

    /**
     * 凭借sql,凭借的时候使用mybatis的占位格式防止sql注入
     */
    @Test
    public void testConcatSql(){
        queryWrapper.apply("id in ( select id from test_user where to_date({0},'yyyyMMdd')<create_time)","20190620");
        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
        testUsers.forEach(System.out::println);
    }

    /**
     *  有点像是凭借sql，不过这里有一点问题不知道如何定义表别名  好像只能自定义查询规则
     */
    @Test
    public void testExits(){
        queryWrapper.exists("select id from test_user where name like 'HRQ%'");
        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
        testUsers.forEach(System.out::println);
    }

    /**
     * 无视规则在最后进行追加sql，多次调用以最后一次为准,这种方式就不能很好
     */
    @Test
    public void testLast(){
        queryWrapper.last(" t where EXISTS( select id from test_user where name like 'HRQ%' and id = t.id)");
       // queryWrapper.isNotNull("email");
        List<TestUser> testUsers = userMapper.selectList(queryWrapper);
        testUsers.forEach(System.out::println);
    }
    private String getId(){
        return System.currentTimeMillis()+"";
    }
}
