# Backend PoC GraalVM
> Prueba de concepto de Graal Virtual Machine


## Objetivo
> **Validar las principales características de esta tecnología:**
1. Imágenes nativas
2. Aplicaciones políglotas
3. Alto rendimiento
 
> **Usando los 3 lenguajes más importantes actualmente:**
1. Java
2. Javascript con Node
3. Python(conscientes de que este leguaje aún está en fase experimental)

> **Crearémos los siguientes componentes:** 
* Microservicio Java con Quarkus(se creará aplicación nativa)
* Microservicio Java con Micronaut(se creará aplicación nativa)
* Microservicio Java con Spring-boot(se creará aplicación nativa)
* Microservicio Políglota(python como pivote y secundarios javascript y java)
* Microservicio Políglota(javascript-Node como pivote y secundarios python y java)
* Aplicación front-end con React
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

## Introducción
> [GraalVM](https://www.graalvm.org) es una nueva máquina virtual creada por [Oracle](https://www.oracle.com/index.html) para ejecutar con alto rendimiento(menor consumo de RAM y menor tiempo de arranque) aplicaciones escritas en JavaScript, Python, Ruby, R, lenguajes basados en la JVM como Java, Scala, Groovy, Kotlin, Clojure y lenguajes basados en LLVM como C y C++.
 
> Elimina el aislamiento entre lenguajes de programación y permite la interoperabilidad en un runtime compartido en aplicaciones políglotas. Además brinda una utilería para generar aplicaciones nativas(binarios) que podrán ejecutarse sin tener que instalar el JRE.

## Metodología
> Comparativa con medición cuantitativo y cualitativo


#### Infraestructura
> Se requiere un proyecto privado con repositorios git


#### Estimación de Costes
> No genera costos económicos


#### Planning de tiempos
> Cada integrante dedicará **8 horas semanales** de las cuales 5 serán fuera del horario laboral

* **Definición de objetivos, alcances y Documento propuesta** - del 4 al 8 de noviembre de 2019
* **Ejecución** - Del 11 de noviembre al 16 de diciembre de 2019
* **Review final y Documento de entrega** - del 17 al 30 de diciembre de 2019
* **Formación y/o Artículo para blog** - Después del 2 de enero de 2020


## Información General
> Las pruebas se ejecutarán con los siguientes requerimientos técnicos y tecnológicos:

| Nombre        | Versión       | Tipo  |
| ------------- |:-------------:| :-----:|
| GraalVM | 19.2.1 CE 64-Bit | Virtual Machine |
| Java | OpenJDK 1.8.0_232 | Lenguaje |
| Python | 3.7.3 | Lenguaje |
| Node | 10.16.3 | Runtime |
| Micronaut | 1.2.5 | Framework |
| Quarkus | 0.26.1 | Framework |
| Spring-boot | 2.2.0 | Framework |
| React | 16.10.2 | Framework |
| Angular | 1.7.8 | Framework |
| Docker | 19.03.4 | Contenedores |
| MacOS | 10.15 | Sistema Operativo |
| MongoDB | 4.2.1 | Base de datos NoSQL |
| Linux | Ubuntu 19.10 | Sistema Operativo |
| Linux | Fedora 30 / CentOS 7 | Sistema Operativo |
| Linux | Alpine 3.10.3 | Sistema Operativo  |

#### Autores

| Nombre        | Planeta       | Tribu  |
| ------------- |:-------------:| :-----:|
| [Alejandro Jesús Torres Dimas](alejandrojesus.torres.dimas.next@bbva.com) | Software Crafter |  |
| [Crisanto Jerónimo García](crisanto.jeronimo.next@gmail.com) | Software Crafter | Backend |
| [Daniel Ramírez Herrera](daniel.ramirez3.next@bbva.com) | Software Crafter | DevSecOps |
| [Edgar Alan Valdes Iglesias](edgaralan.valdes.iglesias.next@bbva.com) | Software Crafter | Backend |
| [José Eduardo Radilla Mendoza](joseeduardo.radilla.next@bbva.com) | Software Crafter | Backend |
| [José Daniel Hernández Osorio](josedaniel.hernandez.osorio.next@bbva.com) | Software Crafter | Backend |
| [José Salvador Cortés Figueroa](josesalvador.cortes.next@bbva.com) | Software Crafter | Backend |
| [Octavio Martínez José](octavio.martinez.jose.next@bbva.com) | Software Crafter |  |


##### Fecha del documento
> 14-11-2019


##### Tecnologías relacionadas
> `GraalVM` `Java` `Python` `Node` `React`  `Angular`  `Micronaut`  `Quarkus`  `Spring-boot`  `Docker`  `Native application`
