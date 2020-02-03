package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioServ {
	void newComment(ComentarioDTO comentario);

	List<ComentarioDTO> findCommentsByMovId(String movieId);
	ComentarioDTO findCommentByMovIdCommId(String movieId, String commentId);

	void modifyComment(String commentID, ComentarioDTO comentario);

	void editTextComment(String movieId, String commentId, String text);

	void deleteComment(String movieId, String comentarioId);
}
