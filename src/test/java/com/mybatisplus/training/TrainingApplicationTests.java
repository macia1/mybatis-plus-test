package com.mybatisplus.training;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.training.quickstart.bean.User;
import com.mybatisplus.training.quickstart.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class TrainingApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(("----------------SelectAll method test-------------------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void insertTest(){
        System.out.println("--------------------------Insert method test---------------------------------");
        User user = new User();
        user.setAge(21);
        user.setName("Macia");
        user.setEmail("99@gami.com");
        int insert = userMapper.insert(user);
    }

    @Test
    void updateTest(){
        System.out.println("--------------------------Update method test---------------------------------");
        User user = new User();
        user.setId(1377075752819257346L);
        user.setName("Joker");
        user.setAge(100);
        user.setEmail("joker@gmail.com");
        userMapper.updateById(user);
    }

    @Test
    void testOptimisticLocker1(){
        User user = userMapper.selectById(11);
        user.setName("RedJoker");
        // 测试旧版本
        int result = userMapper.updateById(user);
        if (result == 1){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
    }

    @Test
    void testSelectPage(){
        // 构建分页条件第二页每页显示3条
        Page<User> page = new Page<>(2,2);
        // 使用分页条件查询，不使用其他条件
        userMapper.selectPage(page, null);
        // 获取分页后查询出的记录
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("记录总数：" + page.getTotal());
    }

    @Test
    void testLogicDelete(){
        int i = userMapper.deleteById(1);
        System.out.println(i);
    }

    @Test
    void testSelectById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,100));
        users.forEach(System.out::println);
    }

    @Test
    void testSelectByMap(){
        Map<String, Object> param = new HashMap<>();
        param.put("name", "Jack");
        param.put("age", 18);
        List<User> users = userMapper.selectByMap(param);
        users.forEach(System.out::println);
    }

//    @Test
//    void testSelectMap(){
//        Page<User> page = new Page<>(2,3);
//        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage();
//
//    }

    @Test
    void testBatchDeletedIds(){
        userMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }

}
