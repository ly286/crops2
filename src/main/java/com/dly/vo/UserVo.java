package com.dly.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserVo implements Serializable {

    private Long id;
    //用户姓名
    private String username;
    //用户昵称
    private String avatar;
    //用户邮箱
    private String email;
    //用户性别
    private Integer status;
    //用户创建时间
    //created
    private LocalDateTime createTime;
    //用户最后登录时间
    private LocalDateTime lastLogin;
}
