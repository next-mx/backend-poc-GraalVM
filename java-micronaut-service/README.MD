# Java Micronaut Service

## Instrucciones
```bash
cd java-micronaut-service  
```


#### OpenJDK
```bash
sdk u java 8.0.232.j9-adpt
./gradlew clean assemble          # compilación
./gradlew clean run               # ejecución
```

#### GraalVM
```bash
sdk u java 19.2.1-grl
./gradlew clean assemble          # compilación
./gradlew clean run               # ejecución
```

#### Imagen Nativa
```bash
sdk u java 19.2.1-grl
./gradlew clean assemble          # compilación
native-image --no-server -cp build/libs/java-micronaut-service-0.1-all.jar  # generación de imagen nativa
./poc-graalvm-micronaut           # ejecución
```

#### Imagen Nativa con Docker
```bash
docker build . -t poc-graalvm-micronaut
docker run -p 8080:8080 poc-graalvm-micronaut
```