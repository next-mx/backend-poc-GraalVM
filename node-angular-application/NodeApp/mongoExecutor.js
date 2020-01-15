var MongoClient = require('mongodb').MongoClient;
var ObjectID = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/";


exports.insertMovie = function(){
  MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("test");
  var myquery = {
    "title": "Avengers: Endgame",
    "year": 2019,
    "runtime": 1,
    "cast": [
        "Thanos"
    ],
    "poster": "https://pulpfictioncine.com/contenido/4809/se-revela-epico-poster-de-avengers-endgame#&gid=1&pid=1",
    "plot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos",
    "fullplot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos. Con la ayuda de los aliados restantes, los Vengadores deben reunirse una vez más para deshacer las acciones de Thanos y restaurar el orden del universo de una vez y para siempre, sin importar las consecuencias que pueda tener, aunque algunos pagarán el precio. 2​ Además cuenta con la aparición de Ant-Man, Wasp y Capitana Marvel.",
    "lastupdated": 1440565425000,
    "type": "movie",
    "directors": [
        "Anthony y Joe Russo"
    ],
    "imdb": {
        "rating": 5.9,
        "votes": 1032,
        "id": 1
    },
    "countries": [
        "USA"
    ],
    "rated": "NOT RATED",
    "genres": [
        "Superhéroes",
        "Acción",
        "Ciencia ficción"
    ]
};
  var res = dbo.collection("MOVIES").insertOne(myquery, function(err, obj) {
    if (err) {throw err;}
    else{
    	console.log(obj.result.n + " = document inserted");
    	console.log(obj);
    }
    db.close();
  });
  
 });
	//return Date(); ***Especificar este
}


exports.deleteMovie = function(movieId){
  MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("test");
  var movie_id = movieId;
  var o_id = new ObjectID(movieId);
  var myquery = { _id:{$eq:o_id} };
  var res = dbo.collection("MOVIES").deleteOne(myquery, function(err, obj) {
    if (err) {throw err;}
    else{
    	console.log(obj.result.n + " = document deleted");
    	console.log(myquery);
    }
    db.close();
  });
  
 });
	//return Date(); ***Especificar este
}

exports.deleteComment = function(movieId, commentId){
  MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("test");
  //var movie_id = movieId;
  var o_id = new ObjectID(commentId);
  var myquery = { _id:{$eq:o_id} };
  var res = dbo.collection("COMMENTS").deleteOne(myquery, function(err, obj) {
    if (err) {throw err;}
    else{
    	console.log(obj.result.n + " = document deleted");
    	console.log(myquery);
    }
    db.close();
  });
  
 });
	//return Date(); ***Especificar este
}


exports.insertComment = function(){
  MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("test");
  var myquery = {
    name: "Petyr Baelish",
    email: "aidan_gillen@gameofthron.es",
    movie_id: {
        oid: "573a1390f29313caabcd4218"
   	 },
    text: "Quo deserunt ipsam ipsum. Tenetur eos nemo nam sint praesentium minus exercitationem.",
    date: {
        date: {
            numberLong: 995052309000
        }
   	 }
	};
  var res = dbo.collection("COMMENTS").insertOne(myquery, function(err, obj) {
    if (err) {throw err;}
    else{
    	console.log(obj.result.n + " = document inserted");
    	console.log(obj);
    }
    db.close();
  });
  
 });
	//return Date(); ***Especificar este
}


