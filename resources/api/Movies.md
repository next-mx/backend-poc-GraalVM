# Movies
> Definición de contrato de interface para el recurso Pelicula


### POST
> Agregar una pelicula


* **URI**

    [/movies/](http://localhost:8080/pocgraalvm/api/v1/movies/)


* **PETICION**

```json
{
	"title" : "Avengers: Endgame",
	"year" : 2019,
	"runtime" : 1,
	"cast" : [
		"Thanos"
	],
	"poster" : "https://pulpfictioncine.com/contenido/4809/se-revela-epico-poster-de-avengers-endgame#&gid=1&pid=1",
	"plot" : "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos",
	"fullplot" : "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos. Con la ayuda de los aliados restantes, los Vengadores deben reunirse una vez más para deshacer las acciones de Thanos y restaurar el orden del universo de una vez y para siempre, sin importar las consecuencias que pueda tener, aunque algunos pagarán el precio. 2​ Además cuenta con la aparición de Ant-Man, Wasp y Capitana Marvel.",
	"lastupdated" : 1440565425000,
	"type" : "movie",
	"directors" : [
		"Anthony y Joe Russo"
	],
	"imdb" : {
		"rating" : 5.9,
		"votes" : 1032,
		"id" : 1
	},
	"countries" : [
		"USA"
	],
	"rated" : "NOT RATED",
	"genres" : [
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
    "message": "Película agregada exitosamente",
    "result": {
        "_id": "5e44c730bb90620d4a40ae69",
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
	"title" : "Avengers: final Endgame",
	"year" : 2020,
	"runtime" : 1,
	"cast" : [
		"Thanos 2"
	],
	"poster" : "https://pulpfictioncine.com/contenido/4809/se-revela-epico-poster-de-avengers-endgame#&gid=1&pid=1",
	"plot" : "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos",
	"fullplot" : "Tras los eventos devastadores de Avengers: Infinity War, el universo está en ruinas debido a los efectos del titán loco, Thanos. Con la ayuda de los aliados restantes, los Vengadores deben reunirse una vez más para deshacer las acciones de Thanos y restaurar el orden del universo de una vez y para siempre, sin importar las consecuencias que pueda tener, aunque algunos pagarán el precio. 2​ Además cuenta con la aparición de Ant-Man, Wasp y Capitana Marvel.",
	"lastupdated" : 1440565425000,
	"type" : "movie",
	"directors" : [
		"Anthony y Joe Russo"
	],
	"imdb" : {
		"rating" : 5.9,
		"votes" : 1032,
		"id" : 1
	},
	"countries" : [
		"USA"
	],
	"rated" : "XXXX",
	"genres" : [
		"Superhéroes",
		"Acción",
		"Ciencia ficción"
	]
}
_
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


<br/><br/>



### POST
> Respaldar catalogo de películas bajo los siguientes criterios:

1. Se respaldan todas las peliculas existentes en la colección Movies
2. El respaldo se agenda en un job que será ejecutado 5 segundos después de haber recibido la petición
3. El respaldo se guarda en un archivo de texto plano llamado movies_catalog.csv
4. Si el archivo de respaldo ya fue creado será reemplazado.
4. El archivo de respaldo se guarda en el mismo directorio donde se encuentra el .jar del microservicio que se está ejecutando
4. El archivo de respaldo debe tener el siguiente layout:

    | Id |  Película | 
    | ----------- | ----------- |
    | Obtener la fecha y hora actual del sistema operativo y convertirla a formato EPOCH con precisión de milisegundos, luego sumarle el número de registro que le corresponde(es un consecutivo), después multiplicarlo por el número de comentarios que tenga la película y finalmente dividirlo entre el número de registros/documentos que tenga la colección de Movies | Contenido del documento en formato json de una película |
    
    ``Ejemplo de cálculo para columna Id:``
    
     _10-12-2019 23:59:59.123 -> 1576043999000 + 4 -> 1576043999004 * 7 -> 11032307993028 / 5 -> 2206461598605.6_ 

    ``Ejemplo de contenido para columna Id:``
    
    _2206461598605.6_

    ``Ejemplo de contenido para columna Película``
    
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
        }_
    

* **URI**

    [/movies/backup](http://localhost:8080/pocgraalvm/api/v1/movies/backup)


* **PETICION**

```json
{}
```
     
     
* **RESPUESTA**
    
```json 
{
  "message": "Respaldo agendado exitosamente",
  "result": null
}
```
  

<br/><br/>



### GET
> Consultar listado de películas del archivo de respaldo


* **URI**

    [/movies/backup](http://localhost:8080/pocgraalvm/api/v1/movies/backup)


* **PETICION**

    Sin cuerpo
 
 
* **RESPUESTA**

```json
{
"message": "Respaldo consultado exitosamente",
"result": [
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
  ]
}
```

