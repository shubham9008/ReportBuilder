package com.vassarlabs.proj.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/testCassandra")
	String testCassandra(){
//		TestTable testTable = new TestTable();
//		testTable.setId(1);
//		crudService.insertTestTable(testTable);		
		return "HELLO CASSANDRA";
	}

}
