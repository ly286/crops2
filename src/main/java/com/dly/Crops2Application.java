package com.dly;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.dly.mapper")
public class Crops2Application {

	public static void main(String[] args) {
		SpringApplication.run(Crops2Application.class, args);
		log.info("启动成功");
	}
}
