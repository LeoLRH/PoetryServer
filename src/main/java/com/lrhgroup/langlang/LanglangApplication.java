package com.lrhgroup.langlang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lrhgroup.langlang.repository")
@SpringBootApplication
public class LanglangApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanglangApplication.class, args);
    }

}
