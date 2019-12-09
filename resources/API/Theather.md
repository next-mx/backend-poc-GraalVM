* **URL**

  /theathers/{version}

* **Method:**
  
  POST 

* **Body**
```json
	{
    	"location": {
            "address": {
                "street1": [Street address name],
                "city": [City name],
                "state": [State code ID],
                "zipcode": [Zip code]
            },
            "geo": {
                "type": [Type],
                "coordinates": 
                {
                    "longitude": [Number Longitude],
                    "latitude": [Number Latitude]
                }
            }
        }
	}
```
* **Success Response:**
  
  Theater added successfully

  * **Code:** 201 <br />
    **Content:** 
```json	
    {
        "theaterId": [Theater's ID],
    	"location": {
            "address": {
                "street1": [Street address name],
                "city": [City name],
                "state": [State code ID],
                "zipcode": [Zip code]
            },
            "geo": {
                "type": [Type],
                "coordinates":{
                    "longitude": [Number Longitude],
                    "latitude": [Number Latitude]
                }
            }
        }
	}
 ```
 Types:<br />
		Point - Point located over theater building<br />
		
* **Error Response:**

  * **Code:** 400 Bad request error <br />
    **Content:** ```json	`{ type:{numeric}, message: "[Error message]" }`     ```
	Types:<br />
		100 - Invalid state code<br />
		200 - Invalid Zip code<br />
		300 - street1 is required<br />
		400 - city is required<br />
		500 - Coordinates object is required<br />
		600 - longitude is required<br />
		700 - latitude is required<br />
		
  * **Code:** 500 Internal server error <br />
    **Content:** `{ type:500, message : "Service is down, please try again later" }`<br />

* **Sample Call:**

  POST: /theathers/v1/
```json	
    {
    	"location": {
            "address": {
                "street1": "340 W Market",
                "city": "Bloomington",
                "state": "MN",
                "zipcode": "55425"
            },
            "geo": {
                "type": "Point",
                "coordinates":{
                    "longitude": "-93.24565",
                    "latitude": "44.85466"
                }
            }
        }
	}
 ```


 