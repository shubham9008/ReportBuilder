package com.vassarlabs.proj.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import com.vassarlabs.proj.cassandra.CassandraConfiguration;

@SpringBootApplication
@Import({CassandraConfiguration.class})
@EnableAutoConfiguration
@EnableCassandraRepositories("com.vassarlabs.proj.cada.dsp")
@ComponentScan({"com.vassarlabs.proj"})
public class StudentHubLaunchService 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(StudentHubLaunchService.class, args);
    	System.out.println("Application Started");
    }
}
