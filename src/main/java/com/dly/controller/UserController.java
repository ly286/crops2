package com.dly.controller;

import com.dly.entity.User;
import com.dly.result.Result;
import com.dly.service.UserService;
import com.dly.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@Tag(name = "用户模块")
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

    /**
     * 前端根据id查询用户信息
     * @param id
     * @return
     */
    @Operation(summary = "根据id查询用户")
    @GetMapping("/{id}")
    public Result<UserVo> getById(@Parameter(description = "用户id") @PathVariable Long id){
        log.info("根据id查询用户所有数据:{}", id);
        UserVo userVo = userService.getByIdVo(id);
        return Result.success(userVo);
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @Operation(summary = "新增用户")
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        log.info("新增用户{}", user);
        userService.save(user);
        return Result.success();
    }


    //TODO: 前端修改用户
    @Operation(summary = "修改用户")
    @PutMapping
    public Result update(@RequestBody User user){
        log.info("修改用户{}", user);
        userService.updateById(user);
        return Result.success();
    }

}
