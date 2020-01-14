# Backend PoC GraalVM
> Prueba de concepto de Graal Virtual Machine

## Información General
>Sección de datos generales de la Prueba de Concepto

#### Versión del documento
> 1.0.0

##### Fecha del documento
> 16-12-2019


#### Autores

| Nombre        | Planeta       | Tribu  |
| ------------- |:-------------:| :-----:|
| [Alejandro Jesús Torres Dimas](alejandrojesus.torres.dimas.next@bbva.com) | Software Crafter |  |
| [Crisanto Jerónimo García](crisanto.jeronimo.next@gmail.com) | Software Crafter | Backend |
| [Daniel Ramírez Herrera](daniel.ramirez3.next@bbva.com) | Software Crafter | DevSecOps |
| [Edgar Alan Valdes Iglesias](edgaralan.valdes.iglesias.next@bbva.com) | Software Crafter | Backend |
| [José Salvador Cortés Figueroa](josesalvador.cortes.next@bbva.com) | Software Crafter | Backend |
| [Octavio Martínez José](octavio.martinez.jose.next@bbva.com) | Software Crafter |  |



#### Tecnologías
| Nombre        | Versión       | Tipo  |
| ------------- |:-------------:| :-----:|
| `GraalVM` | 19.2.1 CE 64-Bit | Virtual Machine |
| `Java` | OpenJDK 1.8.0_232 | Lenguaje |
| `Python` | 3.7.3 | Lenguaje |
| `NodeJS` | 10.16.3 | Runtime |
| `Micronaut` | 1.2.5 | Framework |
| `Quarkus` | 0.26.1 | Framework |
| `Spring-boot` | 2.2.0 | Framework |
| React | 16.10.2 | Framework |
| Angular | 1.7.8 | Framework |
| `MongoDB` | 4.2.1 | Base de datos NoSQL |
| `Docker` | 19.03.4 | Contenedores |
| `MacOS` | 10.15 | Sistema Operativo |


## Introducción
> [GraalVM](https://www.graalvm.org) es una nueva máquina virtual creada por [Oracle](https://www.oracle.com/index.html) para ejecutar con alto rendimiento(menor consumo de RAM y menor tiempo de arranque) aplicaciones escritas en JavaScript, Python, Ruby, R, lenguajes basados en la JVM como Java, Scala, Groovy, Kotlin, Clojure y lenguajes basados en LLVM como C y C++.
 
> Elimina el aislamiento entre lenguajes de programación y permite la interoperabilidad en un runtime compartido en aplicaciones políglotas. Además brinda una utilería para generar aplicaciones nativas(binarios) que podrán ejecutarse sin tener que instalar el JRE.



### Objetivo
> **Validar las principales características de esta tecnología:**
1. Imágenes nativas
2. Aplicaciones políglotas
3. Alto rendimiento
 
> **Usando los 3 lenguajes más importantes actualmente:**
1. Java
2. Javascript con Node
3. Python(conscientes de que este lenguaje aún está en fase experimental)

> **Se crearon los siguientes componentes:** 
* Microservicio Java con Quarkus(se creará aplicación nativa)
* Microservicio Java con Micronaut(se creará aplicación nativa)
* Microservicio Java con Spring-boot(se creará aplicación nativa)
* Microservicio Políglota(python como pivote y secundarios javascript y java)
* Microservicio Políglota(javascript-Node como pivote y secundarios python y java)
* Aplicación front-end con Angular
 
 > **Se considerarán las funcionalidades requeridas frecuentemente:**
 * Lectura-escritura de archivos de texto
 * Operaciones CRUD
 * Persistencia en Base de Datos
 * Logs
 * API REST
 * Ejecución de procesos programados
 * Operaciones aritméticas que incluyan cantidades numéricas grandes(BigDecimal, BigInteger)
 
 > **Medirémos y compararémos los resultados dados por los compiladores, interpretes y runtimes con y sin GraalVM:**
 1. Comportamientos en tiempo de compilación y ejecución
 2. Tamaño del componente generado(.jar, .sh, .js, imagen docker)
 2. Consumo de RAM en tiempo de compilación y ejecución
 3. Consumo de CPU en tiempo de compilación y ejecución
 4. Tiempo de startup
 5. Tiempos de respuesta
 6. Estabilidad de la aplicación
 7. Compatibilidad de librerías y frameworks


### Estado del arte
> Esta sección trata de contextualizar la tecnología usada/evaluada en esta PoC dentro de tecnologías similares en el mercado. Información de cuándo aparecieron, trends de uso, popularidad, desarrollo, versiones disponibles, se identificarán tecnologías similares que pueden pertenecer o no al radar de la compañía

## Material
> Las pruebas y mediciones se realizaron en una computadora Mackbook Pro con las siguientes características:
> * CPU: 
> * RAM:
> * HD:

> La definición del modelo y datos de prueba se encuentran en la carpeta [resources/db/](https://github.com/beeva/backend-poc-GraalVM/blob/master/resources/db/Collections.md) del repositorio

> La definición de la API REST que expone cada microservicio está definido en la carpeta [resources/api/](https://github.com/beeva/backend-poc-GraalVM/tree/master/resources/api) del repositorio


## Métodos
> Para replicar esta PoC es necesario seguir las siguientes instrucciones, la secuencia es importante:

 **NOTA**: Las versiones están arriba en la sección **Tecnología** de este documento

#### Instalación

* ##### SDKman
SDKMAN! es una herramienta para administrar versiones paralelas de multiples Software Development Kits sobre los sistemas operativos más populares basados en linux/unix.
Provee una Interface de Línea de Comandos(CLI) y una API para instalar, intercambiar, borrados y listados de candidatos.

[Sigue las instrucciones para instalarlo en https://sdkman.io/install](https://sdkman.io/install)

* ##### GraalVM
> GraalVM es la nueva máquina virtual que probaremos en esta PoC

En una terminal ejecuta los siguientes comandos:
```bash
sdk ls java
sdk i java 19.2.1-grl
sdk u java 19.2.1-grl
gu install native-image
```

* ##### Open JDK
> Open JDK es una versión de código abierto de la JVM de Oracle

En una terminal ejecuta los siguientes comandos:
```bash
sdk ls java
sdk i java 8.0.232.j9-adpt
sdk u java 8.0.232.j9-adpt
```

* ##### NodeJS
> NodeJS es un entorno de ejecución para javascript

[Descarga la versión node-v10.18.1.pkg en https://nodejs.org/dist/latest-v10.x/](https://nodejs.org/dist/latest-v10.x/), una vez descargado ejecuta el archivo .pkg y sigue las instrucciones en la pantalla que se te abrirá

* ##### Docker
Docker es una herramienta que provee un camino para desplegar aplicaciones de forma segura y asilada en un contenedor en el cual se empaquetan todas sus dependencias y librerías.

[Sigue las instrucciones para instalarlo en https://docs.docker.com/install/](https://docs.docker.com/install/)


#### Ejecución

* ##### MongoDB

* ##### Java Micronaut Service

* ##### Java Quarkus Service

* ##### Java Spring-boot Service

* ##### JavaScript Node Service

* ##### Python Polyglot Service



## Resultados

#### Java Micronaut Service

| Metrica        | GraalVM       | JVM OpenJDK | Imagen Nativa |
| ------------- |:-------------:| :-----:| ------------- |
| Tamaño del componente generado(MB) |  |   |   |
| Consumo de RAM en tiempo de compilación(MB)  |    |   |   |
| Consumo de RAM en tiempo de ejecución(MB)  |    |   |
| Consumo de CPU en tiempo de compilación(MB)  |    |   |   |
| Consumo de CPU en tiempo de ejecución(MB)  |    |   |    |
| Tiempo de startup(Segundos)  |    |   |   |
| Compatibilidad de librerías y frameworks  |    |   |    |
| Comportamientos en tiempo de compilación  |    |   |    |
| Comportamientos en tiempo de ejecución  |    |   |      |

> Comentarios generales/adicionales sobre los resultados de este componente particular


#### Java Quarkus Service

| Metrica        | GraalVM       | JVM OpenJDK | Imagen Nativa |
| ------------- |:-------------:| :-----:| ------------- |
| Tamaño del componente generado(MB) |  |   |   |
| Consumo de RAM en tiempo de compilación(MB)  |    |   |   |
| Consumo de RAM en tiempo de ejecución(MB)  |    |   |
| Consumo de CPU en tiempo de compilación(MB)  |    |   |   |
| Consumo de CPU en tiempo de ejecución(MB)  |    |   |    |
| Tiempo de startup(Segundos)  |    |   |   |
| Compatibilidad de librerías y frameworks  |    |   |    |
| Comportamientos en tiempo de compilación  |    |   |    |
| Comportamientos en tiempo de ejecución  |    |   |      |


> Comentarios generales/adicionales sobre los resultados de este componente particular


#### Java Spring-boot Service

| Metrica        | GraalVM       | JVM OpenJDK | Imagen Nativa |
| ------------- |:-------------:| :-----:| ------------- |
| Tamaño del componente generado(MB) |  |   |   |
| Consumo de RAM en tiempo de compilación(MB)  |    |   |   |
| Consumo de RAM en tiempo de ejecución(MB)  |    |   |
| Consumo de CPU en tiempo de compilación(MB)  |    |   |   |
| Consumo de CPU en tiempo de ejecución(MB)  |    |   |    |
| Tiempo de startup(Segundos)  |    |   |   |
| Compatibilidad de librerías y frameworks  |    |   |    |
| Comportamientos en tiempo de compilación  |    |   |    |
| Comportamientos en tiempo de ejecución  |    |   |      |

> Comentarios generales/adicionales sobre los resultados de este componente particular


#### Javascript Node Service

| Metrica        | GraalVM       | NodeJS |
| ------------- |:-------------:| :-----:|
| Tamaño del componente generado(MB) |  |   |
| Consumo de RAM en tiempo de compilación(MB)  |    |   |
| Consumo de RAM en tiempo de ejecución(MB)  |    |   |
| Consumo de CPU en tiempo de compilación(MB)  |    |   |
| Consumo de CPU en tiempo de ejecución(MB)  |    |   |
| Tiempo de startup(Segundos)  |    |   |
| Compatibilidad de librerías y frameworks  |    |   |
| Comportamientos en tiempo de compilación  |    |   |
| Comportamientos en tiempo de ejecución  |    |   |

> Comentarios generales/adicionales sobre los resultados de este componente particular


#### Python Polyglot Service

| Metrica        | GraalVM       | Python |
| ------------- |:-------------:| :-----:|
| Tamaño del componente generado(MB) |  |   |
| Consumo de RAM en tiempo de compilación(MB)  |    |   |
| Consumo de RAM en tiempo de ejecución(MB)  |    |   |
| Consumo de CPU en tiempo de compilación(MB)  |    |   |
| Consumo de CPU en tiempo de ejecución(MB)  |    |   |
| Tiempo de startup(Segundos)  |    |   |
| Compatibilidad de librerías y frameworks  |    |   | 
| Comportamientos en tiempo de compilación  |    |   |
| Comportamientos en tiempo de ejecución  |    |   |

> Comentarios generales/adicionales sobre los resultados de este componente particular



## Discusión
> En esta sección se comentan los resultados uno a uno, se discuten las comparativas. Es aquí donde debe ir TODO juicio de valor de quien lo escribe, opiniones, etc. En esta sección se deberá revisar el triángulo: tiempo-alcance-incertidumbre con el objetivo de resolver las conclusiones de la última sección.

### Limitaciones
> En esta sección se describen las limitaciones de la PoC:
> * Cosas que no se han podido probar (por qué no se ha probado, el alcance de tu trabajo y de tus conclusiones)
> * Evaluaciones que no se han realizado y que molaría hacer (futuras PoCs?)
> * Limitaciones de la tecnología: Las limitaciones propias de la tecnología deberán discutirse en Discusión y en Resultados así como derivar en conclusiones.

### Ejemplo de caso de uso
> En esta sección se discute, a la vista de resultados, una posible aplicación dentro de [BBVA Next Technologies](https://www.bbvanexttechnologies.com/) de la(s) tecnología(s) evaluada(s). No es necesario implementarla, sino dar idea de utilidades posibles de la tecnología

#### Costes
> Aquí se deben describir los costes del supuesto caso de uso. Pueden ser aproximados, pero en líneas generales deben dar una idea de las posibilidades de la tecnología desde el punto de vista económico.

## Conclusiones
>En esta sección deben estar todas las conclusiones del trabajo. **Cuidado con realizar especulaciones**, es importante que sean conclusiones que deriven directamente del trabajo realizado y de la tecnología: pros y contras así como limitaciones de implantación. Es importante que en las conclusiones aparezcan ventajas y desventajas de la tecnología como resultado de la PoC.
Información relevante que es deseable responder:
¿Es válida la tecnología para producción? ¿Bajo qué condiciones y parámetros?
¿Cómo se relaciona con otras tecnologías del radar?

