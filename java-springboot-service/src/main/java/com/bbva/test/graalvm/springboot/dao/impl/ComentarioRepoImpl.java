package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioRepo;
import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.comentario.ConsultaComentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class ComentarioRepoImpl implements ComentarioRepo {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void newComment(ComentarioDTO comentario) {

	}

	@Override
	public ConsultaComentDTO findComment(String movieId, String commentId) {
		return null;
	}

	@Override
	public void modifyComment(String movieId, ComentarioDTO comentario) {

	}

	@Override
	public void editTextComment(String movieId, String commentId, String text) {

	}

	@Override
	public void deleteComment(String movieId, String comentarioId) {

	}
}
