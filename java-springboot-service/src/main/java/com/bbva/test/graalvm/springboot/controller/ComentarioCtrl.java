package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.RespJSON;
import com.bbva.test.graalvm.springboot.dto.comentario.TextoDTO;
import com.bbva.test.graalvm.springboot.service.ComentarioServ;
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
public class ComentarioCtrl {
	private static final Logger log = LoggerFactory.getLogger(ComentarioCtrl.class);

	@Autowired
	private ComentarioServ comentarioServ;

	/**
	 * crear comentario
	 */
	@PostMapping(path = "/movies/{movieId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> addCommentario(@PathVariable(name = "movieId") String movieId,
														   @RequestBody ComentarioDTO comentario) {
		log.info("Registrando nuevo comentario: {} a la película: {}", comentario, movieId);
		comentario.setMovieId(movieId);
		this.comentarioServ.newComment(comentario);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario agregado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


	/**
	 * Consultar listado de comentarios
	 */
	@GetMapping(path = "/movies/{movieId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ComentarioDTO>> getComments(@PathVariable(name = "movieId") String movieId) {
		log.info("Consultando comentarios de la película: {}",movieId);
		List<ComentarioDTO> cm = this.comentarioServ.findCommentsByMovId(movieId);
		return new ResponseEntity<>(cm, HttpStatus.OK);
	}

	/**
	 * recuperar comentario
	 */
	@GetMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ComentarioDTO> getComment(@PathVariable(name = "movieId") String movieId,
													@PathVariable(name = "commentId") String commentId) {
		log.info("Consultando comentario ID: {} de la película: {}", commentId, movieId);
		ComentarioDTO cm = this.comentarioServ.findCommentByMovIdCommId(movieId, commentId);
		return new ResponseEntity<>(cm, HttpStatus.OK);
	}

	/**
	 * Modificar comentario
	 */
	@PutMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> modificarComentario(@PathVariable(name = "movieId") String movieId,
																@PathVariable(name = "commentId") String commentId,
																@RequestBody ComentarioDTO comentario) {
		log.info("Modificando comentario ID: {} de la película: {}", commentId, movieId);
		comentario.setMovieId(movieId);
		this.comentarioServ.modifyComment(commentId, comentario);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario modificado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Editar un comentario
	 */
	@PatchMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> editarUnComentario(@PathVariable(name = "movieId") String movieId,
															   @PathVariable(name = "commentId") String commentId,
															   @RequestBody TextoDTO texto) {
		log.info("Actualizando comentario ID: {} de la película: {}", commentId, movieId);
		this.comentarioServ.editTextComment(movieId, commentId, texto.getText());
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario editado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Borrar un comentario
	 */
	@DeleteMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> borrarComentario(@PathVariable(name = "movieId") String movieId,
															 @PathVariable(name = "commentId") String comentarioId) {
		log.info("Eliminando comentario ID: {} a la película: {}", comentarioId, movieId);
		this.comentarioServ.deleteComment(movieId, comentarioId);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario eliminado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


}
