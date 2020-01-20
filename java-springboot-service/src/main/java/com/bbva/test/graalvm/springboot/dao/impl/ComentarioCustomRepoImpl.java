package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.ComentarioCustomRepo;
import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Lazy
@Repository
public class ComentarioCustomRepoImpl implements ComentarioCustomRepo {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void editTextComment(String movieId, String commentId, String text) {
		Query select = Query.query(Criteria.where("_id").is(commentId));
		Update update = new Update();
		update.set("text", text);
		this.mongoOperations.findAndModify(select, update, ComentarioDTO.class);
		/**
		 * if you want to get tha new object
		 * ComentarioDTO updateObject = this.mongoOperations.findAndModify(select, update, ComentarioDTO.class);
		 */
	}

	@Override
	public int totalCommnetByMovie(String idMovie) {
		Query selec = Query.query(Criteria.where("movie_id").is(idMovie));
		int tam = this.mongoOperations.find(selec, ComentarioDTO.class).size();
		return tam == 0 ? 1 : tam;
	}

}
