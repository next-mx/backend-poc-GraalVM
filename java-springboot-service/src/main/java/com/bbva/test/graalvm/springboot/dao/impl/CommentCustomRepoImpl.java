package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.CommentCustomRepo;
import com.bbva.test.graalvm.springboot.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class CommentCustomRepoImpl implements CommentCustomRepo {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void editTextComment(String movieId, String commentId, String text) {
		Query select = Query.query(Criteria.where("_id").is(commentId));
		Update update = new Update();
		update.set("text", text);
		this.mongoOperations.findAndModify(select, update, Comment.class);
	}

}
