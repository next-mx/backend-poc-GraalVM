# Comments collection

|fileName  |Type  |
|--|--|
|_id  |ObjectId()  |
|name  |String  |
|email  |String  |
|movie_id  |ObjectId()  |
|text  |String  |
|date  |Date  |

##### Example

```json
{
    "_id": {
        "$oid": "5a9427648b0beebeb69579d5"
    },
    "name": "Petyr Baelish",
    "email": "aidan_gillen@gameofthron.es",
    "movie_id": {
        "$oid": "573a1390f29313caabcd4218"
    },
    "text": "Quo deserunt ipsam ipsum. Tenetur eos nemo nam sint praesentium minus exercitationem.",
    "date": {
        "$date": {
            "$numberLong": "995052309000"
        }
    }
}
```


# Collection Movies

| FileName | Type | Type 
|-|-|-|
| _id | ObjectId | . |
| title | String | . |
| year | Int32 | . |
| runtime | Int32 | . |
| cast | Array | . |
| --> | String | . |
| poster | String | . |
| plot | String | . |
| fullplot | String | . |
| lastupdated | Date | . |
| type | String | . |
| director | Array | . |
| --> | String | . |
| imdb | Object | . |
| --> | rating | Double |
| . | votes | Int32 |
| . | id | Int32 |
| countries | array | . |
| --> | String | . |
| rated | String | . |
| genres | Array | . |
| --> | String | . |

##### Example

```json
{
    "_id": {
        "$oid": "573a1390f29313caabcd4132"
    },
    "title": "Carmencita",
    "year": {
        "$numberInt": "1894"
    },
    "runtime": {
        "$numberInt": "1"
    },
    "cast": [
        "Carmencita"
    ],
    "poster": "http://ia.media-imdb.com/images/M/MV5BMjAzNDEwMzk3OV5BMl5BanBnXkFtZTcwOTk4OTM5Ng@@._V1_SX300.jpg",
    "plot": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
    "fullplot": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
    "lastupdated": {
        "$date": {
            "$numberLong": "1440565425000"
        }
    },
    "type": "movie",
    "directors": [
        "William K.L. Dickson"
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
        "Documentary",
        "Short"
    ]
}
```

