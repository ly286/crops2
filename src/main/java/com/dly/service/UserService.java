package com.dly.service;

import com.dly.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dly.vo.UserVo;

/**
 * @author dly
 * @description 针对表【m_user】的数据库操作Service
 * @createDate 2024-03-02 15:52:08
 */
public interface UserService extends IService<User> {


    UserVo getByIdVo(Long id);
}
