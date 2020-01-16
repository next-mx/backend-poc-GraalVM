package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;

public interface ComentarioServ {
	void newComment(ComentarioDTO comentario);

	ComentarioDTO findCommentByMovIdCommId(String movieId, String commentId);

	void modifyComment(String movieId, ComentarioDTO comentario);

	void editTextComment(String movieId, String commentId, String text);

	void deleteComment(String movieId, String comentarioId);
}
