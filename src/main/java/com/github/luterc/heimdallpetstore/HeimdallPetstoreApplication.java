package com.github.luterc.heimdallpetstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The type Heimdall petstore application.
 *
 * @author Luter
 */
@SpringBootApplication
@EnableTransactionManagement
public class HeimdallPetstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeimdallPetstoreApplication.class, args);
    }

}
