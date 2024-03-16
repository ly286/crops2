package com.dly.controller;

import com.dly.result.Result;
import com.dly.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "主页模块")
@RequestMapping("/zhuYe")
public class ZhuYeController {

    @Autowired
    private ContentService contentService;

    @Operation(summary = "查询所有内容")
    @GetMapping("/all")
    public Result all(){
        return Result.success(contentService.list());
    }
}
