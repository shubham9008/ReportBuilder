package com.vassarlabs.proj.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@Import({CassandraConfiguration.class})
//@EnableAutoConfiguration
//@EnableCassandraRepositories("com.vassarlabs.proj.cada.dsp.repository")
@ComponentScan({"com.vassarlabs.proj"})
public class StudentHubLaunchService 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(StudentHubLaunchService.class, args);
    	System.out.println("Application Started");
    }
}
