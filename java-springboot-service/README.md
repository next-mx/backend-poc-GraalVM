# Java Spring-boot Service

## Instrucciones
```bash
cd java-springboot-service  
```


#### OpenJDK
```bash
sdk u java 8.0.242.hs-adpt
./gradlew clean
./gradlew assemble         
java -jar build/libs/java-springboot-service-1.0.0.jar  #./gradlew clean bootRun 
```

#### GraalVM
```bash
sdk u java 19.2.1-grl
./gradlew clean
./gradlew assemble 
java -jar build/libs/java-springboot-service-1.0.0.jar  #./gradlew clean bootRun 
```

#### Imagen Nativa
No soportado, pero ya existe un [proyecto experimental para implementar esta funcionalidad](https://github.com/spring-projects-experimental/spring-graal-native).         


#### Imagen Nativa con Docker
No soportado, pero ya existe un [proyecto experimental para implementar esta funcionalidad](https://github.com/spring-projects-experimental/spring-graal-native).         
