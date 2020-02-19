# Java Quarkus Service

## Instrucciones
```bash
cd java-quarkus-service  
# ./mvnw clean compile quarkus:dev <modo desarrollo>
```


#### OpenJDK
```bash
sdk u java 8.0.242.hs-adpt
./mvnw clean
./mvnw package -DskipTests             
java -jar target/poc-graalvm-quarkus-1.0-SNAPSHOT-runner.jar    # ./mvnw compile quarkus:dev              
```

#### GraalVM
```bash
sdk u java 19.2.1-grl
./mvnw clean
./mvnw package -DskipTests           
java -jar target/poc-graalvm-quarkus-1.0-SNAPSHOT-runner.jar            
```

#### Imagen Nativa
```bash
sdk u java 19.2.1-grl
./mvnw clean
./mvnw package -Pnative -DskipTests   
target/poc-graalvm-quarkus-1.0-SNAPSHOT-runner        
```

#### Imagen Nativa con Docker
```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t java-quarkus-service:1.0.0 .
docker run -i --rm -p 8080:8080 java-quarkus-service:1.0.0
```
