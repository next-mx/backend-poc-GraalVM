* **URL**

  /comments/{version}

* **Method:**
  
  POST 

* **Body**
```json
	{
		"name": [Comment author's name],
		"email": [Email author's name],
		"movie_id": [Commented movie id],
		"text": [Text of comment]
	}
```
* **Success Response:**
  
  Comment added successfully

  * **Code:** 201 <br />
    **Content:** 
```json	
    {
		"_id": [Id created for this comment],
		"name": [Comment author's name],
		"email": [Email author's name],
		"movie_id": [Commented movie id],
		"text": [Text of comment],
		"date": [Coment creation date]
    }
 ```
* **Error Response:**

  * **Code:** 400 Bad request error <br />
    **Content:** ```json	`{ type:{numeric}, message: "[Error message]" }`     ```
	Types:<br />
		100 - Invalid email address<br />
		200 - Text comment exceded, the maximun text size allowed is 500 characters<br />
		300 - movie_id is required<br />
		400 - Email address is required<br />
		
  * **Code:** 500 Internal server error <br />
    **Content:** `{ type:500, message : "Service is down, please try again later" }`<br />

* **Sample Call:**

  POST: /comments/v1/
```json  
	{
		"name":"Qui-Gon Jin",
		"email":"quigonjin@jedicouncil.org",
		"movie_id": "5a9427648b0beebeb69579d5",
		"text":"I don't like the Star Wars The phantom menace because it's really bored, the most part of movie is about repair a racer's pod for a race. The only good thing is Liam Neeson's acting but his charater is killed by a devil with a double sword."
	}
```


 