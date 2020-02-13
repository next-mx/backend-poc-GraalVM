# Python Django Service
> No fue posible desarrollar este microservicio con Django ya que la implementación de
> python en GraalVM solo soporta un número muy limitado de librerías
> https://www.graalvm.org/docs/reference-manual/languages/python/

## Instrucciones
```bash
cd python-django-service 
```


#### GraalVM
```bash
sdk u java 19.2.1-grl
graalpython -m ginstall install django
# Please note: This Python implementation is in the very early stages, and can run little more than basic benchmarks 
# at this point. Unknown package: 'django'           
```