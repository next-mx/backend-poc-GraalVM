* **URL**

  /movies/{version}

* **Method:**
  
  POST 

* **Body**
```json
{
    "title": [Title],
    "year":[Year],
    "runtime":[Minutes lenght],
    "cast": [
        Actor/Actress 1,Actor/Actress 2, ...,Actor/Actress N
    ],
    "poster": [Poster image URL],
    "plot": [Plot],
    "fullplot": [Full plot],
    "type": "movie",
    "directors": [
        Director 1, Director 2, ....., Director N
    ],
    "imdb": {
        "rating": [number],
        "votes": [votes number],
        "id": [id]
    },
    "countries": [
        Country 1, Conuntry 2, ...., Country N
    ],
    "rated": [Rated],
    "genres": [
        Genre 1, Genre 2, ...., Genre N
    ]
}
```
* **Success Response:**
  
  Movie added successfully

  * **Code:** 201 <br />
    **Content:** 
```json	
   {
    "_id": "573a1390f29313caabcd4132",
    "title": "Carmencita",
    "year": "1894",
    "runtime": "1",
    "cast": [
        "Carmencita"
    ],
    "poster": "http://ia.media-imdb.com/images/M/MV5BMjAzNDEwMzk3OV5BMl5BanBnXkFtZTcwOTk4OTM5Ng@@._V1_SX300.jpg",
    "plot": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
    "fullplot": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
    "lastupdated":"1440565425000",
    "type": "movie",
    "directors": [
        "William K.L. Dickson"
    ],
    "imdb": {
        "rating": "5.9",
        "votes":"1032",
        "id": "1"
    },
    "countries": [
        "USA"
    ],
    "rated": "NOT RATED",
    "genres": [
        "Documentary",
        "Short"
    ]
}


 ```
 Types:<br />
		Movie<br />
		Documentary<br />
		
* **Error Response:**

  * **Code:** 400 Bad request error <br />
    **Content:** ```json	`{ type:{numeric}, message: "[Error message]" }`     ```
	Types:<br />
	100 - Title is mandatory<br />
    200 - poster is mandatory<br />
		
  * **Code:** 500 Internal server error <br />
    **Content:** `{ type:500, message : "Service is down, please try again later" }`<br />

* **Sample Call:**

  POST: /theathers/v1/
```json	
     {
    "title": "Carmencita",
    "year": "1894",
    "runtime": "1",
    "cast": [
        "Carmencita"
    ],
    "poster": "http://ia.media-imdb.com/images/M/MV5BMjAzNDEwMzk3OV5BMl5BanBnXkFtZTcwOTk4OTM5Ng@@._V1_SX300.jpg",
    "plot": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
    "fullplot": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
    "type": "movie",
    "directors": [
        "William K.L. Dickson"
    ],
    "imdb": {
        "rating": "5.9",
        "votes":"1032",
        "id": "1"
    },
    "countries": [
        "USA"
    ],
    "rated": "NOT RATED",
    "genres": [
        "Documentary",
        "Short"
    ]
}

 ```


 