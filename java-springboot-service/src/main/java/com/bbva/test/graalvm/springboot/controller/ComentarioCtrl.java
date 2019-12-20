package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.RespJSON;
import com.bbva.test.graalvm.springboot.dto.comentario.TextoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/pocgraalvm/api/v1")
public class ComentarioCtrl {

	/**
	 * crear comentario
	 */
	@PostMapping(path = "/movies/{movieId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> addCommentario(@PathVariable(name = "movieId") String movieId,
														   @RequestBody ComentarioDTO comentario) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario agregado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * recuperar comentario
	 */
	@GetMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ComentarioDTO> getComment(@PathVariable(name = "movieId") String movieId,
													@PathVariable(name = "commentId") String commentId) {
		ComentarioDTO cm = new ComentarioDTO();
		return new ResponseEntity<>(cm, HttpStatus.OK);
	}

	/**
	 * Modificar comentario
	 */
	@PutMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> modificarComentario(@PathVariable(name = "movieId") String movieId,
																@RequestBody ComentarioDTO comentario) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario modificado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Editar un comentario
	 */
	@PatchMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> editarUnComentario(@PathVariable(name = "movieId") String movieId,
															   @RequestBody ComentarioDTO comentarioId,
															   @RequestBody TextoDTO texto) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario editado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Borrar un comentario
	 */
	@PatchMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> borrarComentario(@PathVariable(name = "movieId") String movieId,
															 @RequestBody ComentarioDTO comentarioId) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario eliminado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


}
