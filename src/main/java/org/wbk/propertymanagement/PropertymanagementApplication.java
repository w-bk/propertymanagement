package org.wbk.propertymanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.wbk.propertymanagement.mapper")
public class PropertymanagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(PropertymanagementApplication.class, args);
    }

}
