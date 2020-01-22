package com.kandi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//通用myMapper接口类不能被扫描到，放在其他包里
@MapperScan(basePackages = "com.kandi.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.kandi", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
