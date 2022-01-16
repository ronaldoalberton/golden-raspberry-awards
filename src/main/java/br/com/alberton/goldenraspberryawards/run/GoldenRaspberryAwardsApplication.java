package br.com.alberton.goldenraspberryawards.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("br.com.alberton.goldenraspberryawards.api.domain")
@EnableJpaRepositories("br.com.alberton.goldenraspberryawards.api.repository")
@SpringBootApplication(scanBasePackages = "br.com.alberton.goldenraspberryawards.api.*")
public class GoldenRaspberryAwardsApplication {

    public static void main(String[] args) {

        SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
    }

}
