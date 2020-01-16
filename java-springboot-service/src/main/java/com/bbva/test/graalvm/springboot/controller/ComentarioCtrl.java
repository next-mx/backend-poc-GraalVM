package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import com.bbva.test.graalvm.springboot.dto.RespJSON;
import com.bbva.test.graalvm.springboot.dto.comentario.TextoDTO;
import com.bbva.test.graalvm.springboot.service.ComentarioServ;
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

@RequestMapping(path = "/pocgraalvm/api/v1")
public class ComentarioCtrl {

	@Autowired
	private ComentarioServ comentarioServ;

	/**
	 * crear comentario
	 */
	@PostMapping(path = "/movies/{movieId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> addCommentario(@PathVariable(name = "movieId") String movieId,
														   @RequestBody ComentarioDTO comentario) {
		comentario.setMovie_id(movieId);
		this.comentarioServ.newComment(comentario);
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

		ComentarioDTO cm = this.comentarioServ.findCommentByMovIdCommId(movieId, commentId);
		return new ResponseEntity<>(cm, HttpStatus.OK);
	}

	/**
	 * Modificar comentario
	 */
	@PutMapping(path = "/movies/{movieId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> modificarComentario(@PathVariable(name = "movieId") String movieId,
																@RequestBody ComentarioDTO comentario) {
		this.comentarioServ.modifyComment(movieId, comentario);
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
		this.comentarioServ.deleteComment(movieId, comentarioId);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Comentario eliminado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


}
