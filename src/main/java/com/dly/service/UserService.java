package com.dly.service;

import com.dly.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dly.vo.TestUser1;
import com.dly.vo.UserVo;

import java.util.Date;

/**
* @author dly
* @description 针对表【m_user】的数据库操作Service
* @createDate 2024-03-02 15:52:08
*/
public interface UserService extends IService<User> {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserVo getById(Long id);

}
