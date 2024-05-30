package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.List;
import java.util.stream.BaseStream;

public interface UserMapper extends BaseMapper<User> {
    User queryById(Long userId);


    void deductBanlanceByIds(@Param("money") int i, @Param("ew") LambdaQueryWrapper<User> queryWrapper);
}
