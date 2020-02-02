const Express = require("express");
const BodyParser = require("body-parser");
const MongoClient = require("mongodb").MongoClient;
const ObjectId = require("mongodb").ObjectID;
var ObjectID = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/";
const DATABASE_NAME = "test";

var app = Express();
var database,collection;

app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: true }));

app.listen(8080, () => {
    MongoClient.connect(url,{ useNewUrlParser: true }, function(err, client) {
	  if (err) throw err;
	  database = client.db(DATABASE_NAME);
	  //collection = database.collection("MOVIES");
	  console.log("Connected to `" + DATABASE_NAME + "`!");
 });
});

app.get('/', function(req, res) {
    res.json({ message: 'Hooolaa :)'});
});

// GET MOVIE [/movies/{movieId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId})
app.get('/pocgraalvm/api/v1/movies/:movieId', (request, response) => {
	var movieid = request.params.movieId;
	var o_id = new ObjectID(movieid);
	var dbquery = { _id:{$eq:o_id} };

	database.collection("MOVIES").findOne(dbquery, (error, result) => {
	if(error) {
	    return response.status(500).send(error);
	}
	response.send(result);
});
});


//Ejemplo: POST MOVIE http://localhost:8888/pocgraalvm/api/v1/movies/{movieId}/comments/
app.post('/pocgraalvm/api/v1/movies/', function(req, response) {
 	database.collection("MOVIES").insertOne(req.body, (error, result) => {
	if(error) {
	    return response.status(500).send(error);
	}
	//response.send(result.insertedId);
	response.json({ message: "insertedId: " + result.insertedId ,result: null});
	});
});

//Ejemplo: DELETE MOVIE http://localhost:8080/movies/:movieId
app.delete('/pocgraalvm/api/v1/movies/:movieId', function(req, response){
	var movieId = req.params.movieId;
 	var o_id = new ObjectID(movieId);
  	var myquery = { _id:{$eq:o_id} };
  	database.collection("MOVIES").deleteOne(myquery, (err, obj) => {
    if (err) {
    	return response.status(500).send(error);
    }
    if(obj.result.n == 1){
    	response.json({ message: '1 Pelicula eliminada :) ', result: null});
    }else{
    	response.json({ message: '0 Peliculas eliminada :) ', result: null});
    }

  });
});

//Ejemplo: PUT MOVIE http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}
app.put('/pocgraalvm/api/v1/movies/:movieId', (req, response) => {
	var o_id = new ObjectID(req.params.movieId);
 	var myquery = { _id:{$eq:o_id} };
 	var newvalues = {$set: req.body};
 	database.collection("MOVIES").replaceOne(myquery, newvalues,  (error, obj) => {
 	//console.log(obj.result);
	if(error) {
	    return response.status(500).send(error);
	}
	response.json({ message: "Movie modified: " + obj.nModified ,result: null});
	});
});


//Ejemplo: PUT MOVIE http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}
app.patch('/pocgraalvm/api/v1/movies/:movieId', (req, response) => {
	var o_id = new ObjectID(req.params.movieId);
 	var myquery = { _id:{$eq:o_id} };
 	var newvalues = {$set: req.body};
 	database.collection("MOVIES").updateOne(myquery, newvalues,  (error, obj) => {
 	//console.log(obj.result);
	if(error) {
	    return response.status(500).send(error);
	}
	response.json({ message: "Movie modified: " + obj ,result: null});
	});
});


/******************COMMENTS*************/






//Ejemplo: POST COMMENT http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/
app.post('/pocgraalvm/api/v1/movies/:movieId/comments', (req, response) => {
 	database.collection("COMMENTS").insertOne(req.body,  (error, result) => {
	if(error) {
	    return response.status(500).send(error);
	}
	response.json({ message: "comment insertedId: " + result.insertedId ,result: null});
	});
});


//DELETE COMMENT http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/{commentId}
app.delete('/pocgraalvm/api/v1/movies/comments/:commentId', (req, response) => {
	var o_id = new ObjectID(req.params.commentId);
 	var myquery = { _id:{$eq:o_id} };
  	database.collection("COMMENTS").deleteOne(myquery, function(err, obj) {
    if (err) {
    	return response.status(500).send(error); }
    if(obj.result.n == 1){
    	response.json({ message: '1 comentario eliminado :) ', result: null});
    }else{
    	response.json({ message: '0 comentarios eliminados :) ', result: null});
    }
});
});


// GET COMMENTs http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/)
app.get('/pocgraalvm/api/v1/movies/:movieId/comments/', (request, response) => {
	var dbquery = { movie_id:{$eq:request.params.movieId}};

	database.collection("COMMENTS").find(dbquery).toArray((error, result) => {
	if(error) {
	    return response.status(500).send(error);
	}
	response.send(result);
});
});

