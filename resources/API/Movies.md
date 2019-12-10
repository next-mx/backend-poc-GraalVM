# Movies
> Definición de contrato de interface para el recurso Pelicula


### POST
> Agregar una pelicula


* **URI**

    [/movies/](http://localhost:8080/pocgraalvm/api/v1/movies/)


* **PETICION**

```json
{
    "title": "Avengers: Endgame",
    "year": {
        "$numberInt": "2019"
    },
    "runtime": {
        "$numberInt": "1"
    },
    "cast": [
        "Thanos"
    ],
    "poster": "https://pulpfictioncine.com/contenido/4809/se-revela-epico-poster-de-avengers-endgame#&gid=1&pid=1",
    "plot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos",
    "fullplot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos. Con la ayuda de los aliados restantes, los Vengadores deben reunirse una vez más para deshacer las acciones de Thanos y restaurar el orden del universo de una vez y para siempre, sin importar las consecuencias que pueda tener, aunque algunos pagarán el precio. 2​ Además cuenta con la aparición de Ant-Man, Wasp y Capitana Marvel.",
    "lastupdated": {
        "$date": {
            "$numberLong": "1440565425000"
        }
    },
    "type": "movie",
    "directors": [
        "Anthony y Joe Russo"
    ],
    "imdb": {
        "rating": {
            "$numberDouble": "5.9"
        },
        "votes": {
            "$numberInt": "1032"
        },
        "id": {
            "$numberInt": "1"
        }
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
}
```
     
     
* **RESPUESTA**
    
```json	
{
    "message": "Pelicula agregada exitosamente",
    "result": null
}
```

    
* **CABECERAS**
    
`LOCATION` = [protocolo://ip:puerto/pocgraalvm/api/v1/movies/{oid}](protocolo://ip:puerto/pocgraalvm/api/v1/movies/{oid})
    

<br/><br/>




### GET
> Consultar una pelicula


* **URI**

    [/movies/{movieId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId})


* **PETICION**

    Sin cuerpo
 
 
* **RESPUESTA**

```json	
{
"message": "Pelicula consultada exitosamente",
"result":
    {
        "_id": {
            "$oid": "573a1390f29313caabcd4132"
        },
        "title": "Avengers: Endgame",
        "year": {
            "$numberInt": "2019"
        },
        "runtime": {
            "$numberInt": "1"
        },
        "cast": [
            "Thanos"
        ],
        "poster": "https://pulpfictioncine.com/contenido/4809/se-revela-epico-poster-de-avengers-endgame#&gid=1&pid=1",
        "plot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos",
        "fullplot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos. Con la ayuda de los aliados restantes, los Vengadores deben reunirse una vez más para deshacer las acciones de Thanos y restaurar el orden del universo de una vez y para siempre, sin importar las consecuencias que pueda tener, aunque algunos pagarán el precio. 2​ Además cuenta con la aparición de Ant-Man, Wasp y Capitana Marvel.",
        "lastupdated": {
            "$date": {
                "$numberLong": "1440565425000"
            }
        },
        "type": "movie",
        "directors": [
            "Anthony y Joe Russo"
        ],
        "imdb": {
            "rating": {
                "$numberDouble": "5.9"
            },
            "votes": {
                "$numberInt": "1032"
            },
            "id": {
                "$numberInt": "1"
            }
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
    }
}
```


<br/><br/>



### PUT
> Modificar una pelicula


* **URI**

    [/movies/{movieId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId})


* **PETICION**

```json
{
    "title": "Avengers: Endgame",
    "year": {
        "$numberInt": "2019"
    },
    "runtime": {
        "$numberInt": "2"
    },
    "cast": [
        "Thanos"
    ],
    "poster": "https://pulpfictioncine.com/contenido/4809/se-revela-epico-poster-de-avengers-endgame#&gid=1&pid=1",
    "plot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco Thanos",
    "fullplot": "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos. Con la ayuda de los aliados restantes, los Vengadores deben reunirse una vez más para deshacer las acciones de Thanos y restaurar el orden del universo de una vez y para siempre, sin importar las consecuencias que pueda tener, aunque algunos pagarán el precio. 2​ Además cuenta con la aparición de Ant-Man, Wasp y Capitana Marvel.",
    "lastupdated": {
        "$date": {
            "$numberLong": "1440565425000"
        }
    },
    "type": "movie",
    "directors": [
        "Anthony y Joe Russo"
    ],
    "imdb": {
        "rating": {
            "$numberDouble": "8.9"
        },
        "votes": {
            "$numberInt": "100032"
        },
        "id": {
            "$numberInt": "1"
        }
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
}
```
 
 
* **RESPUESTA**

```json	
{
    "message": "Pelicula modificada exitosamente",
    "result": null
}
``` 


<br/><br/>



### PATCH
> Editar una pelicula


* **URI**

    [/movies/{movieId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId})


* **PETICION**

```json
{
    "imdb": {
        "rating": {
            "$numberDouble": "9.9"
        },
        "votes": {
            "$numberInt": "100000"
        },
        "id": {
            "$numberInt": "1"
        }
    }
}
```


* **RESPUESTA**

```json	
{
    "message": "Pelicula editada exitosamente",
    "result": null
}
``` 


<br/><br/>



### DELETE
> Eliminar una pelicula


* **URI**

    [/movies/{movieId}](http://localhost:8080/pocgraalvm/api/v1/movies/{movieId})


* **PETICION**

    Sin cuerpo


* **RESPUESTA**

```json	
{
    "message": "Pelicula eliminada exitosamente",
    "result": null
}
``` 
