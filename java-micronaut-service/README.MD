# Java Micronaut Service

## Instrucciones
```bash
cd java-micronaut-service  
```


#### OpenJDK
```bash
sdk u java 8.0.232.j9-adpt
./gradlew clean assemble         
java -jar build/libs/poc-graalvm-micronaut-0.1-all.jar  #./gradlew clean run
```

#### GraalVM
```bash
sdk u java 19.2.1-grl
./gradlew clean assemble 
java -jar build/libs/poc-graalvm-micronaut-0.1-all.jar  #./gradlew clean run
```

#### Imagen Nativa
```bash
sdk u java 19.2.1-grl
./gradlew clean assemble          
native-image --no-server -cp build/libs/java-micronaut-service-0.1-all.jar  # generación de imagen nativa
./poc-graalvm-micronaut           
```

#### Imagen Nativa con Docker
```bash
docker build . -t java-micronaut-service:1.0.0
docker run -p 8080:8080 java-micronaut-service:1.0.0
```