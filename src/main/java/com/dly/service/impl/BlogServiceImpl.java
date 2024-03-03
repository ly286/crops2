package com.dly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dly.entity.Blog;
import com.dly.service.BlogService;
import com.dly.mapper.BlogMapper;
import org.springframework.stereotype.Service;

/**
 * @author dly
 * @description 针对表【m_blog】的数据库操作Service实现
 * @createDate 2024-03-02 15:52:08
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
        implements BlogService {

}




