package com.curso.poo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.curso.poo.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties(
		FileStorageConfig.class
)
@EnableAutoConfiguration
@ComponentScan
public class BackEndSpringBootRestFullPratica2Application {

	public static void main(String[] args) {
		SpringApplication.run(BackEndSpringBootRestFullPratica2Application.class, args);
	}

}
