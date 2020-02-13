const Express = require("express");
const BodyParser = require("body-parser");
const MongoClient = require("mongodb").MongoClient;
var schedule = require('node-schedule');
const csv_writer = require('csv-writer').createObjectCsvWriter;
const csv_reader = require('csv-parser');
const fs = require('fs');
var ObjectID = require('mongodb').ObjectID;
var url = "mongodb://poc-user:6r44lvM#@127.0.0.1:27017/poc-db";

const Logger = require('./logger_service')
const log = new Logger('app')

MOVIES_COLLECTION = "Movies";
COMMENTS_COLLECTION = "Comments";

const SERVER_PORT = 8080;

var app = Express();
var database;

app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: true }));

app.listen(SERVER_PORT, () => {
    MongoClient.connect(url,{ useNewUrlParser: true }, function(err, client) {
	  if (err) throw err;
	  database = client.db();
	  log.info("Server running and listening on port: " + SERVER_PORT);
 	});
});




/* =========================== BACKUP ============================*/
app.post('/pocgraalvm/api/v1/movies/backup', (request, response) => {
	log.info("Agendando respaldo de películas");
	scheduleBackup();

	response.status(201).send({"message": 'Respaldo agendado exitosamente', "result": null})
});


app.get('/pocgraalvm/api/v1/movies/backup/', (request, response) => {
	log.info("Consultando respaldo de películas");
	csvData = []

	fs.createReadStream('movies_catalog.csv')
	.pipe(csv_reader())
	.on('data', (row) => {
		csvData.push(JSON.parse(row.MOVIE));
	})
	.on('end', () => {
		response.status(200).send({"message": 'Respaldo consultado exitosamente', "result": csvData})
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
	database.collection(MOVIES_COLLECTION).find(dbquery).toArray(function(error, movies) {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Película consultada exitosamente', "result": movies})
	});
});


app.get('/pocgraalvm/api/v1/movies/:movieId/', (request, response) => {
	let movieId = request.params.movieId;
	log.info("Consultando película ID " + movieId);
	
	let o_id = new ObjectID(movieId);
	let dbquery = { _id:{$eq:o_id} };

	database.collection(MOVIES_COLLECTION).findOne(dbquery, (error, movie) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Película consultada exitosamente', "result": movie})
	});
});


app.put('/pocgraalvm/api/v1/movies/:movieId', (request, response) => {
	let movieId = request.params.movieId;
	log.info("Modificando película ID " + movieId);
	let o_id = new ObjectID(movieId);
 	let dbquery = { _id:{$eq:o_id} };
 	let newvalues = {$set: request.body};
 	database.collection(MOVIES_COLLECTION).replaceOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).json({"message": 'Película modificada exitosamente', "result": null})	
	});
});


app.patch('/pocgraalvm/api/v1/movies/:movieId', (request, response) => {
	let movieId = request.params.movieId;
	log.info("Actualizando película ID " + movieId);
	let o_id = new ObjectID(movieId);
 	let dbquery = { _id:{$eq:o_id} };
 	let newvalues = {$set: request.body};
 	database.collection(MOVIES_COLLECTION).updateOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Película actualizada exitosamente', "result": null})
	});
});


app.delete('/pocgraalvm/api/v1/movies/:movieId', function(request, response){
	let movieId = request.params.movieId;
	log.info("Eliminando película ID " + movieId);
 	let o_id = new ObjectID(movieId);
  	let dbquery = { _id:{$eq:o_id} };
  	database.collection(MOVIES_COLLECTION).deleteOne(dbquery, (error, obj) => {
		if (error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		if(obj.result.n == 1){
			response.status(200).send({"message": 'Película eliminada exitosamente', "result": null})
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
	let movieId = request.params.movieId;
	log.info("Consultando comentarios de la película ID " + movieId);
	let dbquery = { movie_id:{$eq:movieId}};

	database.collection(COMMENTS_COLLECTION).find(dbquery).toArray((error, comments) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Comentario consultado exitosamente', "result": comments})
	});
});



app.get('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	let commentId = request.params.commentId;
	log.info("Consultando comentario ID " + commentId);
	let o_id = new ObjectID(commentId);
	let dbquery = { _id:{$eq:o_id} };

	database.collection(COMMENTS_COLLECTION).findOne(dbquery, (error, comment) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Comentario consultado exitosamente', "result": comment})
	});
});


app.put('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	let commentId = request.params.commentId;
	log.info("Modificando comentario ID " + commentId + " de la película ID " + request.params.movieId); 
	let o_id = new ObjectID(commentId);
 	let dbquery = { _id:{$eq:o_id} };
 	let newvalues = {$set: request.body};
 	database.collection(COMMENTS_COLLECTION).replaceOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).json({"message": 'Comentario modificado exitosamente', "result": null})	
	});
});


app.patch('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	let commentId = request.params.commentId;
	log.info("Actualizando comentario ID " + commentId + " de la película ID " + request.params.movieId); 
	let o_id = new ObjectID(commentId);
 	let dbquery = { _id:{$eq:o_id} };
 	let newvalues = {$set: request.body};
 	database.collection(COMMENTS_COLLECTION).updateOne(dbquery, newvalues,  (error, obj) => {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}
		response.status(200).send({"message": 'Comentario actualizado exitosamente', "result": null})
	});
});


app.delete('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', (request, response) => {
	let commentId = request.params.commentId;
	log.info("Eliminando comentario ID " + commentId + " de la película ID " + request.params.movieId); 
	let o_id = new ObjectID(commentId);
 	let dbquery = { _id:{$eq:o_id} };
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







function scheduleBackup() {
	let startTime = new Date(Date.now() + 4000);
	let endTime = new Date(startTime.getTime() + 1000);
	schedule.scheduleJob({ start: startTime, end: endTime, rule: '*/1 * * * * *' }, async function(){
		try {
			writeBackupFile();
		}
		catch (err) {
			log.console.warn(err);
		}
	});
}

function writeBackupFile() {
	const csvWriter = csv_writer({
		path: 'movies_catalog.csv',
		header: [
		  {id: 'Id', title: 'ID'},
		  {id: 'Movie', title: 'MOVIE'}
		]
	});
	let csvData = [];

	let dbquery = {};
	let numberOfMovies;
	database.collection(MOVIES_COLLECTION).countDocuments(dbquery, function(error, numberOfDocumments){
		if(error) return callback(error);
		numberOfMovies = numberOfDocumments;
	});
	
	database.collection(MOVIES_COLLECTION).find(dbquery).toArray(function(error, movies) {
		if(error) {
			log.error("Error de BD", error)
			return response.status(500).send({"message": 'Error ocurrido al procesar su petición', "result": null})	
		}

		movies.forEach((movie, index) => {
			let id = Date.now(); // Unix timestamp in milliseconds
			id = id + index;

			dbquery = { movie_id:{$eq:movie._id.toString()}};
			database.collection(COMMENTS_COLLECTION).find(dbquery).toArray((error, comments) => {
				if(error) return callback(error);
				let numberOfComments = comments.length;
				id = id * (numberOfComments>0 ? numberOfComments : 1) / numberOfMovies;
				
				csvData.push({'Id': id, 'Movie': JSON.stringify(movie)});
				csvWriter
				.writeRecords(csvData)
				.then(()=> console.log('The CSV file was written successfully'));
			});
		});			
	});
}

