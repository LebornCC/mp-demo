package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(6L);
        user.setUsername("abc");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {

        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }
//
    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }
//
    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    @Test
    void query(){
        User user = userMapper.queryById(1L);
        System.out.println("user:" + user.getId());
    }

    @Test
    void testQueryWrapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","username","info","balance")
                        .gt("balance",1000)
                                .like("username" ,"o");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateWrapper(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .eq("username","jack");
        User user = new User();
        user.setBalance(2000);
        userMapper.update(user,userUpdateWrapper);

    }

    @Test
    void testUpdate(){
        List<Long> ids = List.of(1L, 2L, 4L);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.in("id",ids)
                .setSql("balance = balance - 200, password = 789");

        userMapper.update(null,userUpdateWrapper);
    }

    @Test
    void testLambdaQueryWrapper(){
        LambdaQueryWrapper<User> select = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername,"o")
                .ge(User::getBalance,1000);

        List<User> users = userMapper.selectList(select);
        users.forEach(System.out::println);
    }

    @Test
    void testCustomWrapper() {
        List<Long> longs = List.of(1L, 2L, 3L);
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda().in(User::getId, longs);
        userMapper.deductBanlanceByIds(200,queryWrapper);
    }
}