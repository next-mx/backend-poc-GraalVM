## API REST
> Espeficaciones generales


#### URL BASE
* **Protocolo**: http
* **Host**: localhost
* **Puerto**: 8080
* **Contexto**: /pocgraalvm/api
* **Versión**: /v1/

**http://localhost:8080/pocgraalvm/api/v1/**

#### GENERALES
1. Para cada recurso se implementan los 5 métodos HTTP POST, GET, PUT, PATCH y DELETE
2. Para el método HTTP GET se implementa consulta por Id y consulta de todos los registros
1. Escribir en log URL y BODY de cada petición y respuesta

#### RECURSOS
1. `Películas`
2. `Comentarios`

#### PETICION-RESPUESTA
* **Formato**: `Json`
* **Estructura Petición**:

    | Método HTTP |  Estandar |
    | ----------- | ----------- |
    | POST | En el body viajarán todos los atributos del recurso |
    | GET | El Id del recurso viajará como Path parameter, sin body |
    | PUT | El Id del recurso viajará como Path parameter y en el body viajarán todos los demás atributos del recurso |
    | DELETE | El Id del recurso viajará como Path parameter, sin body |
    | PATCH | En el body viajarán todos los atributos del recurso |
    
* **Estructura Respuesta**:

    | Método HTTP | Código HTTP | Cabecera HTTP |
    | ----------- | :------------: | :------------: |
    | POST | 201 | LOCATION |
    | GET | 200 | |
    | PUT | 200 | |
    | DELETE | 200 | |
    | PATCH | 200 | |
    
    ```json	
    {
        "message": "Mensaje API",
        "result": "El recurso en formato Json para GET, cualquier otro null"
    }
    ``` 

#### CODIGOS DE RESPUESTA

| Código HTTP | Mensaje HTTP | Mensaje API |
| :---------: | ------------ | ----------- |
| 200   | OK            | Nombre_recurso consultado(a) exitosamente  |
| 201   | CREATED       | Nombre_recurso agregado(a) exitosamente    |
| 204   | NO CONTENT    | Nombre_recurso Modificado/Editado/Eliminado exitosamente    |
| 400   | BAD REQUEST   | Parametros de la petición incorrectos  |
| 500   | INTERNAL SERVER ERROR | Error del servidor al procesar la solicitud  |




