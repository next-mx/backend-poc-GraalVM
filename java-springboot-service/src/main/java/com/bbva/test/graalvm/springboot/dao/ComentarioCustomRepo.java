package com.bbva.test.graalvm.springboot.dao;

public interface ComentarioCustomRepo {

	void editTextComment(String movieId, String commentId, String text);
}
