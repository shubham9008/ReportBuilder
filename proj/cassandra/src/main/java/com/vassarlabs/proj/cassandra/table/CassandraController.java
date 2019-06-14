package com.vassarlabs.proj.cassandra.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vassarlabs.proj.cassandra.table.SimpleTable;
import com.vassarlabs.proj.cassandra.table.CassandraRepository;

@RestController
public class CassandraController {

	@Autowired
	private CassandraRepository cassandraRepository;

//	@RequestMapping(method=RequestMethod.GET,value="/addentry")
//	public ResponseEntity<SimpleTable> saveIntoSimpleTable(@RequestBody SimpleTable simpleTable) {
//		SimpleTable savedData = cassandraRepository.save(simpleTable);
//		return new ResponseEntity<>(savedData, HttpStatus.OK);
//	}
//
//	@GetMapping(path = "{id}/{name}")
//	public ResponseEntity<SimpleTable> fetchRecordFromSimpleTable(@PathVariable("id") String id,
//			@PathVariable("name") String name) {
//		Optional<SimpleTable> fetchData = cassandraRepository.findByIdAndName(id, name);
//		if (!fetchData.isPresent()) {
//			// throw an exception and catch it in the controller advice and return an
//			// elegant response to the user
//		}
//		return new ResponseEntity<>(fetchData.get(), HttpStatus.OK);
//	}
	
	@RequestMapping("/test")
	public String testRoute() {
		return "success";
	}
	@RequestMapping("/add")
	public void add(@RequestBody SimpleTable simpleTable) {
		cassandraRepository.save(simpleTable);
	}
	@RequestMapping("/get")
	public long getCount() {
		return cassandraRepository.count();
	}
}
