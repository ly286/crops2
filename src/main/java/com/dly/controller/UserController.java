package com.dly.controller;

import com.dly.result.Result;
import com.dly.service.UserService;
import com.dly.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Result hello(){
        return Result.success();
    }

//    @GetMapping("/index")
//    public Object index(){
//        return service.getById(1L);
//    }


    @GetMapping("/{id}")
    public Result<UserVo> getById(@PathVariable Long id){
        log.info("根据id查询用户所有数据{}", id);
        UserVo userVo = userService.getById(id);
        return Result.success(userVo);
    }

}
