package com.dly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dly.entity.User;
import com.dly.enums.MessageConstant;
import com.dly.result.Result;
import com.dly.service.UserService;
import com.dly.mapper.UserMapper;
import com.dly.util.BcryptUtil;
import com.dly.util.JwtUtil;
import com.dly.util.RedisUtil;
import com.dly.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author dly
 * @description 针对表【m_user】的数据库操作Service实现
 * @createDate 2024-03-02 15:52:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public UserVo getByIdVo(Long id) {
        //1. 通过id查询用户
        User user = userMapper.selectById(id);
        //2. 将用户信息封装到vo
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        //无法直接copy时间戳为字符串
        userVo.setCreateTime(LocalDateTime.parse(user.getCreated().toString())); // 将 LocalDateTime 转换为字符串
        return userVo;
    }

    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        return user;
    }

    @Override
    public Result login(String username, String password) {
        // 先从数据库查询
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        if (null == user) {
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        if (!BcryptUtil.match(password, user.getPassword())) {
            return Result.error(MessageConstant.PASSWORD_ERROR);
        }
        String jwtToken = jwtUtil.createJwtToken(user.getId().toString(), 60 * 60 * 2);
        redisUtil.set("token_" + jwtToken, user, 60 * 60 * 2, TimeUnit.SECONDS);
        return Result.success(jwtToken);
    }
}




