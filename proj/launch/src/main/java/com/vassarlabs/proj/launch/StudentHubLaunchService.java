package com.vassarlabs.proj.launch;

import com.vassarlabs.ng.proj.sw.elasticsearch.controller.ElasticSearchController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;



@SpringBootApplication
@ComponentScan(basePackageClasses= ElasticSearchController.class)
@ComponentScan({"com.vassarlabs.ng.proj.sw.elasticsearch"})
public class StudentHubLaunchService 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(StudentHubLaunchService.class, args);
    	System.out.println("Application Started");
    }
}
