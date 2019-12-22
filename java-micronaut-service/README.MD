# Resultados
| | Micronaut | Micronaut imagen en docker | Micronaut imagen en MacOs | 
|------------|:---------------:|:-----:|:-----:| 
| Tiempo de compilación | 5s | 3m 27s | 2m 20s | 
| Tamaño del componente generado |18.6 MB |169 MB |61 MB | 
| Consumo de RAM en tiempo de compilación |100 MB |4.3 GB |5.8 GB | 
| Consumo de RAM en tiempo de ejecución | 20 MB|160MB |400 MB | 
| Consumo de CPU en tiempo de compilación | 0.63%|3.72% |50% | 
| Consumo de CPU en tiempo de ejecución | 0.50%| 3%|3% | 
| Tiempo de respuesta | 12ms |10ms |6ms | | Tiempo de startup |2s |121ms |180ms |

## Estabilidad de la aplicación 
En las tres pruebas la aplicación se comporto estable y muy rápida.

## Compatibilidad con librerías y frameworks 
Micronaut ya cuenta con mecanismos que resuelven la mayoría de los paradigmas de programación por lo que si buscas algun paradigma es muy probable que ya este resuelto.

La desventaja de las imagenes nativas es que si la librería no tiene aun compatibilidad con micronaut se tiene que realizar una configuración espcial para las clases que usan reflección.
