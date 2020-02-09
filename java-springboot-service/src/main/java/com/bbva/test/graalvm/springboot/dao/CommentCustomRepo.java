package com.bbva.test.graalvm.springboot.dao;

public interface CommentCustomRepo {

	void editTextComment(String movieId, String commentId, String text);
}
