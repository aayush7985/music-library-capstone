package com.musiclibrary.songservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SongServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongServiceApplication.class, args);
    }
}
