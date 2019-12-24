package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioRepo;
import com.bbva.test.graalvm.springboot.service.ComentarioServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class ComentarioSerImpl implements ComentarioServ {

	@Autowired
	private ComentarioRepo comentarioRepo;
}
