package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioCustomRepo;
import com.bbva.test.graalvm.springboot.dao.ComentarioRepo;
import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.service.ComentarioServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Lazy
@Service
public class ComentarioServImpl implements ComentarioServ {

	@Autowired
	private ComentarioCustomRepo comentarioCustomRepo;

	@Autowired
	private ComentarioRepo comentarioRepo;

	@Override
	public void newComment(ComentarioDTO comentario) {
		this.comentarioRepo.insert(comentario);
	}

	@Override
	public List<ComentarioDTO> findCommentsByMovId(String movieId) {
		return comentarioRepo.findByMovieId(movieId);
	}

	@Override
	public ComentarioDTO findCommentByMovIdCommId(String movieId, String commentId) {
		Optional<ComentarioDTO> commentOp = this.comentarioRepo.findById(commentId);
		return commentOp.isPresent() ? commentOp.get() : new ComentarioDTO();
	}

	@Override
	public void modifyComment(String commentID, ComentarioDTO comentario) {
		comentario.set_id(commentID);
		this.comentarioRepo.save(comentario);
	}

	@Override
	public void editTextComment(String movieId, String commentId, String text) {
		this.comentarioCustomRepo.editTextComment(movieId, commentId, text);
	}

	@Override
	public void deleteComment(String movieId, String comentarioId) {
		this.comentarioRepo.deleteById(comentarioId);
	}
}
