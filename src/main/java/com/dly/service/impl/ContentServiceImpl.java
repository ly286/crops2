package com.dly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dly.entity.Content;
import com.dly.service.ContentService;
import com.dly.mapper.ContentMapper;
import org.springframework.stereotype.Service;

/**
* @author dly
* @description 针对表【m_content】的数据库操作Service实现
* @createDate 2024-03-16 22:34:22
*/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService{

}




