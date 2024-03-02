package com.dly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dly.entity.User;
import com.dly.service.UserService;
import com.dly.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author dly
* @description 针对表【m_user】的数据库操作Service实现
* @createDate 2024-03-02 15:52:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




