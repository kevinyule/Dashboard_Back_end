package com.dashboard_gk.dashboard_gk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@EnableMongoAuditing
@SpringBootApplication
public class DashboardGKApplication {

	public static void main(String[] args) {
        SpringApplication.run(DashboardGKApplication.class, args);
    }
}
