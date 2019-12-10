# Comments
> Definición de contrato de interface para el recurso Comentario


### POST
> Agregar un comentario


* **URI**

    [/movies/{movieId}/comments/](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/)


* **PETICION**

```json
{
    "name": "Crisanto Jeronimo",
    "email": "cjeronimomx@gmail.com",
    "text": "Muy buena para algunos criticada por otros.",
    "date": {
        "$date": {
            "$numberLong": "995052309000"
        }
    }
}
```
     
     
* **RESPUESTA**
    
```json	
{
    "message": "Comentario agregado exitosamente",
    "result": null
}
```

    
* **CABECERAS**
    
`LOCATION` = [protocolo://ip:puerto/pocgraalvm/api/v1/movies/{movieId}/comments/{oid}](protocolo://ip:puerto/pocgraalvm/api/v1/comments/{oid})
    

<br/><br/>




### GET
> Consultar un comentario


* **URI**

    [/movies/{movieId}/comments/{commentId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/)


* **PETICION**

    Sin cuerpo
 
 
* **RESPUESTA**

```json	
{
    "_id": {
        "$oid": "5a9427648b0beebeb69579d5"
    },
    "name": "Crisanto Jeronimo",
    "email": "cjeronimomx@gmail.com",
    "movie_id": {
        "$oid": "573a1390f29313caabcd4132"
    },
    "text": "Muy buena para algunos criticada por otros.",
    "date": {
        "$date": {
            "$numberLong": "995052309000"
        }
    }
}
```


<br/><br/>



### PUT
> Modificar un comentario


* **URI**

    [/movies/{movieId}/comments/{commentId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/{commentId})


* **PETICION**

```json
{
    "name": "Crisanto Jeronimo",
    "email": "cjeronimomx@gmail.com",
    "text": "Muy buena para algunos, criticada por otros.",
    "date": {
        "$date": {
            "$numberLong": "995052308000"
        }
    }
}
```
 
 
* **RESPUESTA**

```json	
{
    "message": "Comentario modificado exitosamente",
    "result": null
}
``` 


<br/><br/>



### PATCH
> Editar un comentario


* **URI**

    [/movies/{movieId}/comments/{commentId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}comments/{commentId})


* **PETICION**

```json
{
    "text": "Muy buena para algunos pero criticada por otros."
}
```


* **RESPUESTA**

```json	
{
    "message": "Comentario editado exitosamente",
    "result": null
}
``` 


<br/><br/>



### DELETE
> Eliminar un comentario


* **URI**

    [/movies/{movieId}/comments/{commentId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId}/comments/{commentId})


* **PETICION**

    Sin cuerpo


* **RESPUESTA**

```json	
{
    "message": "Comentario eliminado exitosamente",
    "result": null
}
``` 
