package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioCustomRepo;
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
	private ComentarioCustomRepo comentarioCustomRepo;

	@Autowired
	private ComentarioRepo comentarioRepo;

	@Override
	public void newComment(ComentarioDTO comentario) {
		this.comentarioRepo.insert(comentario);
	}

	@Override
	public ConsultaComentDTO findCommentByMovIdCommId(String movieId, String commentId) {
		return this.comentarioCustomRepo.findComment(movieId, commentId);
	}

	@Override
	public void modifyComment(String movieId, ComentarioDTO comentario) {
		comentario.set_id(movieId);
		this.comentarioRepo.save(comentario);
		//this.comentarioCustomRepo.modifyComment(movieId, comentario);
	}

	@Override
	public void editTextComment(String movieId, String commentId, String text) {
		this.comentarioCustomRepo.editTextComment(movieId, commentId, text);
	}

	@Override
	public void deleteComment(String movieId, String comentarioId) {
		//this.comentarioRepo.deleteById(movieId);
		this.comentarioCustomRepo.deleteComment(movieId, comentarioId);
	}
}
