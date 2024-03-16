package com.dly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName m_content
 */
@TableName(value ="m_content")
@Data
public class Content implements Serializable {
    private Long id;

    private Long userId;

    private String title;

    private String img;

    private String description;

    private String content;

    private Date created;

    private Integer status;

    private Long liulan;

    private static final long serialVersionUID = 1L;
}