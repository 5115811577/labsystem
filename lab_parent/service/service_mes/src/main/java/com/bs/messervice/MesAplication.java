package com.bs.messervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bs"})
public class MesAplication {
    public static void main(String[] args) {
        SpringApplication.run(MesAplication.class,args);
    }
}
