package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.comentario.ConsultaComentDTO;

public interface ComentarioServ {
	void newComment(ComentarioDTO comentario);

	ConsultaComentDTO findCommentByMovIdCommId(String movieId, String commentId);

	void modifyComment(String movieId, ComentarioDTO comentario);

	void editTextComment(String movieId, String commentId, String text);

	void deleteComment(String movieId, String comentarioId);
}
