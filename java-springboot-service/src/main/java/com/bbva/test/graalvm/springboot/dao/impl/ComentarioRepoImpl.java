package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class ComentarioRepoImpl implements ComentarioRepo {

	@Autowired
	private MongoOperations mongoOperations;
}
