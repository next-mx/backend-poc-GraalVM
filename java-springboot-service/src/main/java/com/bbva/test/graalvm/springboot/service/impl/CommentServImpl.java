package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dao.CommentCustomRepo;
import com.bbva.test.graalvm.springboot.dao.CommentRepo;
import com.bbva.test.graalvm.springboot.model.Comment;
import com.bbva.test.graalvm.springboot.service.CommentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Lazy
@Service
public class CommentServImpl implements CommentServ {

	@Autowired
	private CommentCustomRepo commentCustomRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public void newComment(Comment comment) {
		this.commentRepo.insert(comment);
	}

	@Override
	public List<Comment> findCommentsByMovId(String movieId) {
		return commentRepo.findByMovieId(movieId);
	}

	@Override
	public Comment findCommentByMovIdCommId(String movieId, String commentId) {
		Optional<Comment> commentOp = this.commentRepo.findById(commentId);
		return commentOp.isPresent() ? commentOp.get() : new Comment();
	}

	@Override
	public void modifyComment(String commentID, Comment comment) {
		comment.set_id(commentID);
		this.commentRepo.save(comment);
	}

	@Override
	public void editTextComment(String movieId, String commentId, String text) {
		this.commentCustomRepo.editTextComment(movieId, commentId, text);
	}

	@Override
	public void deleteComment(String movieId, String comentarioId) {
		this.commentRepo.deleteById(comentarioId);
	}
}
