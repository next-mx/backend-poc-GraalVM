package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioRepo;
import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.comentario.ConsultaComentDTO;
import com.bbva.test.graalvm.springboot.service.ComentarioServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class ComentarioSerImpl implements ComentarioServ {

	@Autowired
	private ComentarioRepo comentarioRepo;

	@Override
	public void newComment(ComentarioDTO comentario) {
		this.comentarioRepo.newComment(comentario);
	}

	@Override
	public ConsultaComentDTO findCommentByMovIdCommId(String movieId, String commentId) {
		return this.comentarioRepo.findComment(movieId, commentId);
	}

	@Override
	public void modifyComment(String movieId, ComentarioDTO comentario) {
		this.comentarioRepo.modifyComment(movieId, comentario);
	}

	@Override
	public void editTextComment(String movieId, String commentId, String text) {
		this.comentarioRepo.editTextComment(movieId, commentId, text);
	}

	@Override
	public void deleteComment(String movieId, String comentarioId) {
		this.comentarioRepo.deleteComment(movieId, comentarioId);
	}
}
