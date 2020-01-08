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



##### Tecnologías relacionadas
`GraalVM` `Java` `Python` `Node` `React`  `Angular`  `Micronaut`  `Quarkus`  `Spring-boot`  `Docker`  `Native application`


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


### Estado del arte
> Esta sección trata de contextualizar la tecnología usada/evaluada en esta PoC dentro de tecnologías similares en el mercado. Información de cuándo aparecieron, trends de uso, popularidad, desarrollo, versiones disponibles, se identificarán tecnologías similares que pueden pertenecer o no al radar de la compañía

## Material
> En esta sección tiene que describirse todo el material utilizado para cualquier parte de la PoC.
> * Plataforma cloud
> * PC donde se ha probado (características técnicas)
> * Cuentas de APIs necesarias
> * Datos de prueba (y dónde conseguirlos mediante link directo)

### Subsección de ejemplo
#### Sub^2-sección de ejemplo
##### Sub^3-sección de ejemplo

## Métodos
> En esta sección se describe la metodología de la PoC:
> * Experimentos al menos una tecnología de las categorías Core o Adoptar con la que comparar (si no es posible, en esta sección se debe especificar por qué)
> * Diseño de la planteados para probar la tecnología
> * Se seleccionará metodología de evaluación si se va a comparar con otra tecnología diferente
> * Procedimientos para montar la infraestructura necesaria
> * Procedimientos para crear cuentas necesarias

> **A veces puede resultar confuso dónde poner cierto contenido. Por ejemplo: Si estoy montando una infraestructura cloud para probar una tecnología, en métodos deberán especificarse los detalles de esa infraestructura, o scripts de terraform. Sin embargo, si el objetivo de la PoC es `probar una infraestructura`, entonces dicho terraform deberá ir en Resultados**

## Resultados
> En esta sección se describen (sin comentarios de valor, o juicios) los resultados obtenidos.
> * Scripts de terraform
> * Código evaluado
> * Tiempos de ejecución
> * Precisión de algoritmos
> * Gráficos de resultados

> Si se ha generado código durante la evaluación, se describirá en una sección cómo reproducir exactamente los resultados de la poc ya sea por pasos a seguir, como comandos a usar y archivos dentro del repositorio.

## Código generado
> En esta sección deberán incluirse todos los links al código generado de la PoC.

> La estructura de archivos será la siguiente:
> * [poc-título-de-la-poc]
> 	* Modelo PoC (este mismo documento en Markdown)
> 	* [code]
> 	* [configuration files]

### Costes
> En esta sección de resultados se deben especificar los costes de la PoC, tanto en infraestructura como en tiempo de realización.

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

