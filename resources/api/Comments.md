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
    "date": 995052309000
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
    "message": "Comentario consultado exitosamente",
    "result": {
        "_id": "5e4370972c7f431c6b32dea6",
        "name": "Crisanto Jeronimo",
        "email": "cjeronimomx@gmail.com",
        "text": "Muy buena para algunos criticada por otros.",
        "date": 995052309000,
        "movie_id": "5e436985f528b6186601ee2c"
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
    
    "name": "Crisanto Jeronimo G",
    "email": "cjeronimomx@gmail.com",
    "text": "En espera de la siguiente....",
    "date": 995052309000
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
    "text": "Muy buena para algunos pero criticada por otros que no saben."
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
