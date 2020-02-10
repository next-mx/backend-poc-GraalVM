const Express = require("express");
const BodyParser = require("body-parser");
const MongoClient = require("mongodb").MongoClient;
const ObjectId = require("mongodb").ObjectID;
var ObjectID = require('mongodb').ObjectID;
var url = "mongodb://poc-user:6r44lvM#@127.0.0.1:27017/poc-db";

const Logger = require('./logger_service')
const log = new Logger('app')

MOVIES_COLLECTION = "Movies";
COMMENTS_COLLECTION = "Comments";

const SERVER_PORT = 8080;

var app = Express();
var database,collection;

app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: true }));

app.listen(SERVER_PORT, () => {
    MongoClient.connect(url,{ useNewUrlParser: true }, function(err, client) {
	  if (err) throw err;
	  database = client.db();
	  log.info("Server running and listening on port: " + SERVER_PORT);
 	});
});



app.post('/pocgraalvm/api/v1/movies/', function(request, response) {
	log.info("Agregando película  ", request.body);
 	database.collection(MOVIES_COLLECTION).insertOne(request.body, (error, result) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.setHeader("LOCATION", request.protocol + "://" + request.get('host') + request.originalUrl + result.insertedId)
		response.status(201).send({"message": 'Película agregada exitosamente', "result": null})
	});
});


app.get('/pocgraalvm/api/v1/movies/', (request, response) => {
	log.info("Consultando listado de películas");
	let dbquery = {};
	database.collection("Movies").find(dbquery).toArray(function(error, movies) {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Película consultada exitosamente', "result": movies})
	});
});


app.get('/pocgraalvm/api/v1/movies/:movieId', (request, response) => {
	var movieId = request.params.movieId;
	log.info("Consultando película ID " + movieId);
	
	var o_id = new ObjectID(movieId);
	var dbquery = { _id:{$eq:o_id} };

	database.collection(MOVIES_COLLECTION).findOne(dbquery, (error, movie) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Película agregada exitosamente', "result": movie})
	});
});


app.put('/pocgraalvm/api/v1/movies/:movieId', (request, response) => {
	var movieId = request.params.movieId;
	log.info("Modificando película ID " + movieId);
	var o_id = new ObjectID(movieId);
 	var dbquery = { _id:{$eq:o_id} };
 	var newvalues = {$set: request.body};
 	database.collection(MOVIES_COLLECTION).replaceOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).json({"message": 'Película modificada exitosamente', "result": null})	
	});
});


app.patch('/pocgraalvm/api/v1/movies/:movieId', (request, response) => {
	var movieId = request.params.movieId;
	log.info("Actualizando película ID " + movieId);
	var o_id = new ObjectID(movieId);
 	var dbquery = { _id:{$eq:o_id} };
 	var newvalues = {$set: request.body};
 	database.collection(MOVIES_COLLECTION).updateOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Película actualizada exitosamente', "result": null})
	});
});


app.delete('/pocgraalvm/api/v1/movies/:movieId', function(request, response){
	var movieId = request.params.movieId;
	log.info("Eliminando película ID " + movieId);
 	var o_id = new ObjectID(movieId);
  	var dbquery = { _id:{$eq:o_id} };
  	database.collection(MOVIES_COLLECTION).deleteOne(dbquery, (error, obj) => {
		if (error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		if(obj.result.n == 1){
			response.status(200).send({"message": 'Película consultada exitosamente', "result": null})
		}else{
			responseDTO.message = 'Película no encontrada';
			response.status(404).send({"message": 'Película no encontrada', "result": null})
		}
  	});
});




/* =========================== COMMENTS ============================*/
app.post('/pocgraalvm/api/v1/movies/:movieId/comments', (request, response) => {
	let movieId = request.params.movieId;
	log.info("Agregando comentario a la película ID " + movieId);
	let comment = request.body;
	comment.movie_id=movieId;
	database.collection(COMMENTS_COLLECTION).insertOne(comment,  (error, result) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.setHeader("LOCATION", request.protocol + "://" + request.get('host') + request.originalUrl + result.insertedId)
		response.status(201).send({"message": 'Comentario agregado exitosamente', "result": null})
	});
});


app.get('/pocgraalvm/api/v1/movies/:movieId/comments/', (request, response) => {
	var movieId = request.params.movieId;
	log.info("Consultando comentarios de la película ID " + movieId);
	var dbquery = { movie_id:{$eq:movieId}};

	database.collection(COMMENTS_COLLECTION).find(dbquery).toArray((error, comments) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Comentario consultado exitosamente', "result": comments})
	});
});



app.get('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	var commentId = request.params.commentId;
	log.info("Consultando comentario ID " + commentId);
	var o_id = new ObjectID(commentId);
	var dbquery = { _id:{$eq:o_id} };

	database.collection(COMMENTS_COLLECTION).findOne(dbquery, (error, comment) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Comentario consultado exitosamente', "result": comment})
	});
});


app.put('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	var commentId = request.params.commentId;
	log.info("Modificando comentario ID " + commentId + " de la película ID " + request.params.movieId); 
	var o_id = new ObjectID(commentId);
 	var dbquery = { _id:{$eq:o_id} };
 	var newvalues = {$set: request.body};
 	database.collection(COMMENTS_COLLECTION).replaceOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).json({"message": 'Comentario modificado exitosamente', "result": null})	
	});
});


app.patch('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	var commentId = request.params.commentId;
	log.info("Actualizando comentario ID " + commentId + " de la película ID " + request.params.movieId); 
	var o_id = new ObjectID(commentId);
 	var dbquery = { _id:{$eq:o_id} };
 	var newvalues = {$set: request.body};
 	database.collection(COMMENTS_COLLECTION).updateOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Comentario actualizado exitosamente', "result": null})
	});
});


app.delete('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	var commentId = request.params.commentId;
	log.info("Eliminando comentario ID " + commentId + " de la película ID " + request.params.movieId); 
	var o_id = new ObjectID(commentId);
 	var dbquery = { _id:{$eq:o_id} };
  	database.collection(COMMENTS_COLLECTION).deleteOne(dbquery, function(error, obj) {
		if (error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		if(obj.result.n == 1){
			response.status(200).send({"message": 'Comentario eliminado exitosamente', "result": null})
		}else{
			response.status(404).send({"message": 'Comentario no encontrado', "result": null})
		}
	});
});


