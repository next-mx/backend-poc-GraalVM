# Backend PoC GraalVM
> Prueba de concepto de Graal Virtual Machine

## Información General

#### Versión del documento
> 1.0.0

##### Fecha del documento
> 18-02-2020


#### Autores

| Nombre        | Planeta       | Tribu  |
| ------------- |:-------------:| :-----:|
| [Crisanto Jerónimo García](crisanto.jeronimo.next@gmail.com) | Software Crafter | Backend |
| [Octavio Martínez José](octavio.martinez.jose.next@bbva.com) | Software Crafter |  |
| [José Salvador Cortés Figueroa](josesalvador.cortes.next@bbva.com) | Software Crafter | Backend |
| [Alejandro Jesús Torres Dimas](alejandrojesus.torres.dimas.next@bbva.com) | Software Crafter |  |
| [Edgar Alan Valdes Iglesias](edgaralan.valdes.iglesias.next@bbva.com) | Software Crafter | Backend |



#### Tecnologías
| Nombre        | Versión       | Tipo  |
| ------------- |:-------------:| :-----:|
| `GraalVM` | 19.2.1 CE 64-Bit | Virtual Machine |
| `Java` | OpenJDK 1.8.0_232 | Lenguaje |
| `Python` | 3.7.3 | Lenguaje |
| `NodeJS` | 10.18.1 | Runtime |
| `Micronaut` | 1.2.5 | Framework |
| `Quarkus` | 0.26.1 | Framework |
| `Spring-boot` | 2.2.0 | Framework |
| `MongoDB` | 4.2.1 | Base de datos NoSQL |
| `Docker` | 19.03.4 | Contenedores |
| `MacOS` | Catalina 10.15.2 | Sistema Operativo |


## Introducción
> [GraalVM](https://www.graalvm.org) es una nueva máquina virtual creada por [Oracle](https://www.oracle.com/index.html) para ejecutar con alto rendimiento(menor consumo de RAM y menor tiempo de arranque) aplicaciones escritas en JavaScript, Python, Ruby, R, lenguajes basados en la JVM como Java, Scala, Groovy, Kotlin, Clojure y lenguajes basados en LLVM como C y C++.
 
> Elimina el aislamiento entre lenguajes de programación y permite la interoperabilidad en un runtime compartido en aplicaciones políglotas. Además brinda una utilería para generar aplicaciones nativas(binarios) que podrán ejecutarse sin tener que instalar el JRE.



### Objetivo
> **Validar las principales características de esta tecnología:**
1. Imágenes nativas
2. Compatibilidad con frameworks y librerías
3. Alto rendimiento
 
> **Usando los 3 lenguajes más importantes actualmente:**
1. Java
2. Javascript con NodeJS
3. Python(conscientes de que este lenguaje aún está en fase experimental)

> **Se crearon los siguientes componentes:** 
* Microservicio Java con Quarkus(se creó aplicación nativa)
* Microservicio Java con Micronaut(se creó aplicación nativa)
* Microservicio Java con Spring-boot(no fue posible generar aplicación nativa porque aun no es soportado)
* Microservicio Javascript con NodeJS
* Microservicio Python con Django(No se pudo implementar por incompatibilidad con librerías)
 
 > **Se consideraron las funcionalidades requeridas frecuentemente:**
 * Lectura-escritura de archivos de texto
 * Operaciones CRUD
 * Persistencia en Base de Datos
 * Logs
 * API REST
 * Ejecución de procesos programados
 * Operaciones aritméticas que incluyan cantidades numéricas grandes(BigDecimal, BigInteger)
 
 > **Se midieron y compararon los resultados dados por los compiladores, interpretes y runtimes con y sin GraalVM:**
 1. Comportamientos en tiempo de compilación y ejecución
 2. Tamaño del componente generado(.jar, .sh, .js)
 2. Consumo de RAM en tiempo de compilación y ejecución
 3. Consumo de CPU en tiempo de compilación y ejecución
 4. Tiempo de startup
 5. Tiempos de respuesta en peticiones HTTP
 6. Estabilidad de la aplicación
 7. Compatibilidad de librerías y frameworks


### Estado del arte
> GraalVM es distribuido como versiones:
>- Enterprise Edition(Oracle Java)
>- Community Edition(OpenJDK)
>
> para plataformas x86 64 bits con sistemas operativos:
>- Linux
>- macOS
>- Windows
>
> El 09-05-2019 fue liberada la versión 19.0.0 como primer versión productiva con soporte para Java 8 y
> fue hasta la versión 19.3.0 que añadieron compatibilidad con Java 11.
>
> En el útlimo año se ha vuelto bastante popular, varios artículos se han escrito en diferentes blogs y 
> se han hecho presentaciones en los útlimos pequeños y grandes eventos que tienen que ver con desarrollo de software, tales
> como el Oracle Code One.
>
> Oracle ha impulsado mucho esta tecnología tanto que ha creado el [Intership Program](https://www.graalvm.org/community/internship/)
> en diferentes paises del mundo para reunir desarrolladores de software que aprendan, usen y contribuyan a GraalVM.
>
> Actualmente no existe otra tecnología similar a GraalVM que ofrezca los mismos beneficios para Java.


## Material
> Las pruebas y mediciones se realizaron en una computadora Mackbook Pro con las siguientes características:
> * CPU: Intel i5 Dual Core 2.3 GHz
> * RAM: 16 GB 2133 MHz LPDDR3
> * HD: 250 GB Flash Storage

> La definición del modelo y datos de prueba se encuentran en la carpeta [resources/db/](https://github.com/beeva/backend-poc-GraalVM/blob/master/resources/db/Collections.md) del repositorio

> La definición del API REST que expone cada microservicio está definido en la carpeta [resources/api/](https://github.com/beeva/backend-poc-GraalVM/tree/master/resources/api) del repositorio

> Los payloads de pruebas y variables(con Postman) y jmx de Jmeter se enuentran en la carpeta [resources/test/](https://github.com/beeva/backend-poc-GraalVM/tree/develop/resources/test) del repositorio


## Métodos
> Para replicar esta PoC es necesario seguir las siguientes instrucciones, la secuencia es importante.
> 
> **NOTA**: Las versiones están arriba en la sección **Tecnología** de este documento

#### Instalación

* ##### SDKman
> [SDKMAN!](https://sdkman.io) es una herramienta para administrar versiones paralelas de multiples Software Development Kits sobre los sistemas operativos más populares basados en linux/unix.
> Provee una Interface de Línea de Comandos(CLI) y un API para instalar, intercambiar, borrados y listados de candidatos.
> 
> Sigue las instrucciones para instalarlo en [https://sdkman.io/install](https://sdkman.io/install)

* ##### GraalVM
> [GraalVM](https://www.graalvm.org) es la nueva máquina virtual que probaremos en esta PoC. 
> 
> En una terminal ejecuta los siguientes comandos:
```bash
sdk ls java
sdk i java 19.2.1-grl
gu install native-image
```

* ##### Open JDK
> [Open JDK](https://adoptopenjdk.net) es una versión de código abierto de la JVM de Oracle. 
> 
> En una terminal ejecuta los siguientes comandos:
```bash
sdk ls java
sdk i java 8.0.232.j9-adpt
```

* ##### NodeJS
> [NodeJS](https://nodejs.org/es/) es un entorno de ejecución para javascript. 
> 
> Descarga la versión **node-v10.18.1.pkg** en [https://nodejs.org/dist/latest-v10.x/](https://nodejs.org/dist/latest-v10.x/), una vez descargado ejecuta el archivo .pkg y sigue las instrucciones en la pantalla que se te mostrará

> o usa [Homebrew](https://docs.brew.sh/Installation)
```bash
brew install node@10
```

* ##### Docker
> [Docker](https://www.docker.com) es una herramienta que provee un camino para desplegar aplicaciones de forma segura y asilada en un contenedor en el cual se empaquetan todas sus dependencias y librerías. 
> 
> Sigue las instrucciones para instalarlo en [https://docs.docker.com/install/](https://docs.docker.com/install/)


#### Ejecución

* ##### MongoDB
> [MongoDB](https://www.mongodb.com) es un sistema de base de datos NoSQL orientado a documentos de código abierto
> 
> Sigue las instrucciones para ejecutarlo en la carpeta [resuorces/db](https://github.com/beeva/backend-poc-GraalVM/tree/master/resources/db)

* ##### Java Micronaut Service
> [Micronaut](https://micronaut.io) Es un framework moderno para desarrollar microservicios full stack, basado en la JVM
> diseñado para construir aplicaciones modulares faciles de testear.
> 
> Sigue las instrucciones para ejecutarlo en la carpeta [java-micronaut-service](https://github.com/beeva/backend-poc-GraalVM/tree/master/java-micronaut-service)

* ##### Java Quarkus Service
> [Quarkus](https://quarkus.io) es un framework nativo de Java para Kubernetes diseñado para GraalVM y JVM, creado a partir de las mejores librerías y estándares Java del mercado
> 
> Sigue las instrucciones para ejecutarlo en la carpeta [java-quarkus-service](https://github.com/beeva/backend-poc-GraalVM/tree/master/java-quarkus-service)

* ##### Java Spring-boot Service
> [Spring-boot](https://spring.io/projects/spring-boot) es un proyecto de **Spring Framework** que facilita la configuración y desarrollo de aplicaciones
>
> Sigue las instrucciones para ejecutarlo en la carpeta [java-springboot-service](https://github.com/beeva/backend-poc-GraalVM/tree/master/java-springboot-service)

* ##### JavaScript Node Service
> [NodeJS](https://nodejs.org/es/) es un entorno de ejecución para javascript. 
> 
> Sigue las instrucciones para ejecutarlo en la carpeta [javascript-node-service](https://github.com/beeva/backend-poc-GraalVM/tree/master/javascript-node-service)

* ##### Python Polyglot Service
> [Python](https://www.python.org/) es un lenguaje de programación interpretado, multiproposito y multiparadigma ya que soporta orientación a objetos, imperativa y funcional
> 
> Sigue las instrucciones para ejecutarlo en la carpeta [python-polyglot-service](https://github.com/beeva/backend-poc-GraalVM/tree/master/python-polyglot-service)



## Resultados

#### Java Micronaut Service

| Métrica                                                         | GraalVM  | JVM OpenJDK | Imagen Nativa |
| --------------------------------------------------------------- |:--------:| :----------:| :-----------: |
| Tamaño del componente generado                                  | 15 MB    | 15 MB       | 58 MB         |
| Consumo de RAM en tiempo de compilación                         | 832 MB   | 377 MB      | 6.83 GB       |
| Consumo de CPU en tiempo de compilación                         | 54%      | 32%         | 97.3%         |
| Consumo de RAM en tiempo de ejecución                           | 334.1 MB | 145.4 MB    | 14.3 MB       |
| Consumo de CPU en tiempo de ejecución                           | 0.1%     | 0.2%        | 0%            |
| Tiempo de startup                                               | 6.3s     | 6.4s        | 22ms          |
| Tiempo de compilación                                           | 7s       | 7s          | 3.86m         |
| Consumo de CPU con 100 peticiones HTTP (GET y POST) por segundo | 64.95%   | 62.32%      | 62.19%        |
| Consumo de RAM con 100 peticiones HTTP (GET y POST) por segundo | 3.5 GB   | 3.1 GB      | 1.63 GB       |
| Throughput mínimo con 100 peticiones HTTP (GET y POST)          | 29.0/s   | 32.2/s      | 23.0/s        |


#### Java Quarkus Service

| Métrica                                                         | GraalVM  | JVM OpenJDK | Imagen Nativa |
| --------------------------------------------------------------- |:--------:| :----------:| :-----------: |
| Tamaño del componente generado                                  | 365 KB   | 374 KB      | 45 MB         |
| Consumo de RAM en tiempo de compilación                         | 470 MB   | 320 MB      | 8.5 GB        |
| Consumo de CPU en tiempo de compilación                         | 67%      | 70%         | 96%           |
| Consumo de RAM en tiempo de ejecución                           | 384 MB   | 168 MB      | 7.6 MB        |
| Consumo de CPU en tiempo de ejecución                           | 0.5%     | 0.4%        | 0.2%          |
| Tiempo de startup                                               | 16.2s    | 16.2s       | 14ms          |
| Tiempo de compilación                                           | 5.4s     | 6.5s        | 3.55m         |
| Consumo de CPU con 100 peticiones HTTP (GET y POST) por segundo | 60.16%   | 62.32%      | 54.5%         |
| Consumo de RAM con 100 peticiones HTTP (GET y POST) por segundo | 2.15 GB  | 1.9 GB      | 703 MB        |
| Throughput mínimo con 100 peticiones HTTP (GET y POST)          | 30.0/s   | 29.0/s      | 10.9/s        |


#### Java Spring-boot Service

| Métrica                                                         | GraalVM  | JVM OpenJDK | Imagen Nativa |
| --------------------------------------------------------------- |:--------:| :----------:| :-----------: |
| Tamaño del componente generado                                  | 23 MB    | 23 MB       | NA            |
| Consumo de RAM en tiempo de compilación                         | 890 MB   | 421 MB      | NA            |
| Consumo de CPU en tiempo de compilación                         | 54%      | 19.7%       | NA            |
| Consumo de RAM en tiempo de ejecución                           | 533.7 MB | 252.1 MB    | NA            |
| Consumo de CPU en tiempo de ejecución                           | 0.4%     | 0.2%        | NA            |
| Tiempo de startup                                               | 17.8s    | 17.8s       | NA            |
| Tiempo de compilación                                           | 6s       | 1s          | NA            |
| Consumo de CPU con 100 peticiones HTTP (GET y POST) por segundo | 69.97%   | 67.2%       | NA            |
| Consumo de RAM con 100 peticiones HTTP (GET y POST) por segundo | 3.03 GB  | 2.99 GB     | NA            |
| Throughput mínimo con 100 peticiones HTTP (GET y POST)          | 27.2/s   | 26.3/s      | NA            |


#### Javascript Node Service

| Métrica                                                         | GraalVM  | NodeJS      | Imagen Nativa |
| --------------------------------------------------------------- |:--------:| :----------:| :-----------: |
| Tamaño del componente generado                                  | 3.6 KB   | 3 KB        | NA            |
| Consumo de RAM en tiempo de compilación                         | NA       | NA          | NA            |
| Consumo de CPU en tiempo de compilación                         | NA       | NA          | NA            |
| Consumo de RAM en tiempo de ejecución                           | 855.7 MB | 49.8 MB     | NA            |
| Consumo de CPU en tiempo de ejecución                           | 0.1%     | 0.1%        | NA            |
| Tiempo de startup                                               | 0.54s    | 0.13s       | NA            |
| Tiempo de compilación                                           | NA       | NA          | NA            |
| Consumo de CPU con 100 peticiones HTTP (GET y POST) por segundo | 63.87%   | 44.7%       | NA            |
| Consumo de RAM con 100 peticiones HTTP (GET y POST) por segundo | 1.1 GB   | 179.9 MB    | NA            |
| Throughput mínimo con 100 peticiones HTTP (GET y POST)          | 8.6/s    | 20.0/s      | NA            |


#### Python Django Service

> No fue posible desarrollar este microservicio con Django ya que la implementación de
> python en GraalVM solo soporta un número muy limitado de librerías
> https://www.graalvm.org/docs/reference-manual/languages/python/



## Conclusiones
> Después de haber utilizado esta tecnología y haber comparado resultados se ha comprobado que el alto performance 
> no es el que presume la documentación, la generación de aplicaciones nativas aún tiene varias limitantes lo cual tiene
> mejor resulto Golang.
>
> En general se recomienda usar los runtimes nativos de cada lenguaje y No GraalVM, ya que los tiempos y consumos de recurso
> son mejores en los runtimes nativos.
>
> * Con Microanut framework se alcanza el througthput más alto en la aplicación nativa.
> * En general con Quarkus framework se logran los mejores resultados para GraalVM.
> * Spring-Boot no soporta oficialmente la compilación a aplicación nativa pero ya existe un esfuerzo para tener este feature,
> [aquí hay una demo hecha por Pivotal](https://www.infoq.com/presentations/spring-boot-graalvm/)
> * Con NodeJS se obtiene el througthput más bajo usando GraalVM en comparación con los otros frameworks validados.
> * La compilación a aplicación nativa solo está disponible para java.
> * La compilación a imagen nativa tiene algunos defectos:
>   1. El tiempo de compilación es alto.
>   2. El tamaño del componente generado es pesado comparado contra un jar común.
>   3. Requiere de un equipo de computo potente ya que el consumo de CPU y RAM es muy alto.


### Limitaciones de la PoC
> * No fue posible validar la versión Enterprise de GraalVM en la que se esperaría que el performance sean mayor que la 
> versión community la cual se utilizó en esta PoC.
> * No fue posible probar la versión 20.0.0 de GraalVM(presume tener varias mejoras y features) porque fue liberada justo el día en el que finaliza esta PoC.
> * Por tiempos quedó pendiente implementar aplicaciones políglotas.
> * Sería bueno implementar un microservicio similar a los de esta PoC utilizando [Golang](https://golang.org/) y medir la aplicación nativa de GraalVM contra la que genera Go.


### Ejemplo de caso de uso
> Algunas ideas son:
> * Una imagen docker genérica que sirva para contenerizar aplicaciones escritas en diferentes lenguajes de programación.
> * Creación de un lenguaje ya que GraalVM soporta a través de Truffle la compilación/ejecución de lenguajes externos.
> * Implementación de una herramienta similar a [Jupyter](https://jupyter.org/) o [Colab](https://colab.research.google.com/notebooks/intro.ipynb#recent=true).


