var express 	= require("express"); 
var app 	= express();
var bodyParser 	= require('body-parser');
var mongoExecutor = require('./mongoExecutor');

 
// Soporte para bodies codificados en jsonsupport.
app.use(bodyParser.json());
// Soporte para bodies codificados
//app.use(bodyParser.urlencoded({ extended: true })); 

// Create application/x-www-form-urlencoded parser
var urlencodedParser = bodyParser.urlencoded({ extended: false });

// Seteamos la ruta principal
app.get('/', function(req, res) {
    res.json({ message: 'Hooolaa :)'});
});

app.use(express.static('public'));
app.get('/index.htm', function (req, res) {
   res.sendFile( __dirname + "/" + "index.htm" );
})


//Ejemplo: POST MOVIE http://localhost:8888/pocgraalvm/api/v1/movies/{movieId}/comments/
app.post('/pocgraalvm/api/v1/movies/', function(req, res) {
 	mongoExecutor.insertMovie();
	response = {
    	message: "Movie added",
	    result: null
		}
   		res.json(response);
});


//Ejemplo: DELETE MOVIE http://localhost:8080/movies/:movieId
app.delete('/pocgraalvm/api/v1/movies/:movieId', function(req, res){
	var movieId = req.params.movieId;
	mongoExecutor.deleteMovie(movieId);
	res.json({ message: 'Pelicula eliminada :) ',result: null});
});



//Ejemplo: POST COMMENT http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/
app.post('/pocgraalvm/api/v1/movies/:movieId/comments/', function(req, res) {
	var itemId = req.params.movieId;
	
 	mongoExecutor.insertComment(itemId);
	response = {
    	message: "Comentario agregado exitosamente",
	    result: null
		}
   		res.json(response);
});


//DELETE COMMENT http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/{commentId}
app.delete('/pocgraalvm/api/v1/movies/:movieId/comments/:commentId', function(req, res){
	var movieId = req.params.movieId;
	var commentId = req.params.commentId;
	mongoExecutor.deleteComment(movieId, commentId);
	response = {
      message:'Comentario eliminado exitosamente movie:'+ movieId + " commentId: " + commentId,
      result:null
   };
   console.log(response);
   res.json(response);
});


var server = app.listen(8080, function () {
    console.log('Server is running..'); 
});