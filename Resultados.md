# Título de la PoC
>Título de la PoC (debe ser lo suficientemente conciso para poder identificarla)

## Información General
>Sección de datos generales de la Prueba de Concepto

#### [v1.0]
> Versión del documento (si procede)

#### Autores
> Quién ha desarrollado y escrito este documento (el orden por cantidad de tiempo dedicado)
* [NombreAutor1 ApellidoAutor1](mailto:nombre1.apellido1.next@bbva.com)
* [NombreAutor2 ApellidoAutor2](mailto:nombre2.apellido2.next@bbva.com)

#### TRIBU1 TRIBU2 DD-MM-YYYY
> Tribu(s) de realización de la PoC y Fecha del documento

#### keyword1, keyword2, keyword3
> Al menos tres tags que identifiquen las tecnologías relacionadas

## Introducción
>En esta sección se describirá un poco de contexto de la tecnología a evaluar con referencias a otras tecnologías (así como links a sus webs y documentación). Es tu puerta de entrada, merece la pena que sea acorde a tu trabajo.

### Objetivo
>Es la sección **más importante** y tiene que quedar claro el qué quiero hacer exactamente. Deberá haber venido definido por el documento previo de aprobación de la PoC, por lo que no podrá variar a lo largo de la PoC.

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
