# Base de Datos
> Se utiliza MongoDB versión 4.2.1 en un contenedor docker



### Instalación

1. **Levantar contenedor**
```bash
docker run -d --name mongo-graalvm \
    -e MONGO_INITDB_DATABASE=admin \
    -e MONGO_INITDB_ROOT_USERNAME=root \
    -e MONGO_INITDB_ROOT_PASSWORD=secret \
    -p 27017:27017 \
    mongo:4.2.1-bionic --auth
```



### Configuración  

1. **Conectar al shell de Mongo dentro del contenedor**
```bash
docker exec -it mongo-graalvm mongo -u root -p secret
```
  
    
2. **Crear Base de Datos `poc-db`**
```js
use poc-db
```

    
3. **Crear usuario `poc-user` con privilegios de lectura-escritura sobre la Base de Datos `poc-db`**
```js
db.createUser({ user: "poc-user", pwd: "6r44lvM#", roles:[{ role: "readWrite", db:"poc-db"}]})
```


4. **Salir del shell de Mongo**
```js
exit 
```


5. **Salir del shell del contenedor**
```bash
exit
```
    
   
### Conexión

* **IP:** ``127.0.0.1``
* **Puerto:** ``27017``
* **Base de Datos:** ``poc-db``
* **Usuario:** ``poc-user``
* **Contraseña:** ``6r44lvM#``