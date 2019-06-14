package com.vassarlabs.proj.cassandra.table;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassandraRepository extends CrudRepository<SimpleTable, Serializable> {
	
	//Optional<SimpleTable> findByIdAndName(String id, String name);

}
