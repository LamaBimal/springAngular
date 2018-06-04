package com.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by bimal on 2/17/18.
 */
@SpringBootApplication(scanBasePackages = {"com.rest"})
public class springAngular {
    public static void main(String args[]){
        SpringApplication.run(springAngular.class,args);
    }
}
