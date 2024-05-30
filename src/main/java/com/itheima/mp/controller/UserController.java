package com.itheima.mp.controller;

import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;
    @PostMapping
    @ApiOperation("新增用户")
    public void add(@RequestBody UserFormDTO userFormDTO){
        User user = new User();
        BeanUtils.copyProperties(userFormDTO,user);
        iUserService.save(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public void delete(@PathVariable Long id){

        iUserService.removeById(1L);
    }
}
