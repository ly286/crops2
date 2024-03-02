package com.dly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dly.entity.User;
import com.dly.service.UserService;
import com.dly.mapper.UserMapper;
import com.dly.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author dly
* @description 针对表【m_user】的数据库操作Service实现
* @createDate 2024-03-02 15:52:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserVo getById(Long id) {
        //1. 通过id查询用户
        User user = userMapper.selectById(id);
        //2. 将用户信息封装到vo
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

}




