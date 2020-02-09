package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.model.Comment;
import com.bbva.test.graalvm.springboot.model.ResponseDTO;
import com.bbva.test.graalvm.springboot.model.comment.Texto;
import com.bbva.test.graalvm.springboot.service.CommentServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/pocgraalvm/api/v1")
public class CommentCtrl {
	private static final Logger log = LoggerFactory.getLogger(CommentCtrl.class);

	@Autowired
	private CommentServ commentServ;

	/**
	 * crear comentario
	 */
	@PostMapping(path = "/movies/{movieId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Comment>> addCommentario(@PathVariable(name = "movieId") String movieId,
															   @RequestBody Comment comment) {
		log.info("Registrando nuevo comentario: {} a la película: {}", comment, movieId);
		comment.setMovieId(movieId);
		this.commentServ.newComment(comment);
		ResponseDTO<Comment> responseDTO = new ResponseDTO<>("Comentario agregado exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}


	/**
	 * Consultar listado de comentarios
	 */
	@GetMapping(path = "/movies/{movieId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<List<Comment>>> getComments(@PathVariable(name = "movieId") String movieId) {
		log.info("Consultando comentarios de la película: {}",movieId);
		List<Comment> comments = this.commentServ.findCommentsByMovId(movieId);
		ResponseDTO<List<Comment>> responseDTO = new ResponseDTO<>("Comentario consultado exitosamente", comments);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * recuperar comentario
	 */
	@GetMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Comment>> getComment(@PathVariable(name = "movieId") String movieId,
											  @PathVariable(name = "commentId") String commentId) {
		log.info("Consultando comentario ID: {} de la película: {}", commentId, movieId);
		Comment cm = this.commentServ.findCommentByMovIdCommId(movieId, commentId);
		ResponseDTO<Comment> responseDTO = new ResponseDTO<>("Comentario consultado exitosamente", cm);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * Modificar comentario
	 */
	@PutMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Comment>> modificarComentario(@PathVariable(name = "movieId") String movieId,
																   @PathVariable(name = "commentId") String commentId,
																   @RequestBody Comment comment) {
		log.info("Modificando comentario ID: {} de la película: {}", commentId, movieId);
		comment.setMovieId(movieId);
		this.commentServ.modifyComment(commentId, comment);
		ResponseDTO<Comment> responseDTO = new ResponseDTO<>("Comentario modificado exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * Editar un comentario
	 */
	@PatchMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Comment>> editarUnComentario(@PathVariable(name = "movieId") String movieId,
																  @PathVariable(name = "commentId") String commentId,
																  @RequestBody Texto texto) {
		log.info("Actualizando comentario ID: {} de la película: {}", commentId, movieId);
		this.commentServ.editTextComment(movieId, commentId, texto.getText());
		ResponseDTO<Comment> responseDTO = new ResponseDTO<>("Comentario editado exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * Borrar un comentario
	 */
	@DeleteMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Comment>> borrarComentario(@PathVariable(name = "movieId") String movieId,
																@PathVariable(name = "commentId") String comentarioId) {
		log.info("Eliminando comentario ID: {} a la película: {}", comentarioId, movieId);
		this.commentServ.deleteComment(movieId, comentarioId);
		ResponseDTO<Comment> responseDTO = new ResponseDTO<>("Comentario eliminado exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


}
