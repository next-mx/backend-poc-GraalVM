package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.comentario.ConsultaComentDTO;

public interface ComentarioCustomRepo {
	//void newComment(ComentarioDTO comentario);

	ConsultaComentDTO findComment(String movieId, String commentId);

	//void modifyComment(String movieId, ComentarioDTO comentario);

	void editTextComment(String movieId, String commentId, String text);

	void deleteComment(String movieId, String comentarioId);
}
