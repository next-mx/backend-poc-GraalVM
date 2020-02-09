package com.bbva.test.graalvm.springboot.service;


import com.bbva.test.graalvm.springboot.model.Comment;

import java.util.List;

public interface CommentServ {
	void newComment(Comment comment);

	List<Comment> findCommentsByMovId(String movieId);
	Comment findCommentByMovIdCommId(String movieId, String commentId);

	void modifyComment(String commentID, Comment comment);

	void editTextComment(String movieId, String commentId, String text);

	void deleteComment(String movieId, String comentarioId);
}
