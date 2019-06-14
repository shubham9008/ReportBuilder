package com.vassarlabs.proj.cassandra.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vassarlabs.*")
public class SpringbootCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCassandraApplication.class, args);
		System.out.println("Cassandra Started");
	}
}
