# Walmart Api Shopping
Walmart Api Shopping nos permite buscar productos por id, brand o description.
En caso que la busqueda sea un palindrome retorna el precio con un 50% de descuento.
Y solo buscara por brad o descripcion cuando el largo de busqueda se mayor que 3.

Walmart Api Shopping trabaja con una base de datos remota, entonces solo se
puede usar conectado a internet.

### Operaciones

1. Para levantar en tu local
    ```
    $ make run
    ```
2. Para levantar en tu docker local
    ```
    $ make docker-run
    ```
3. Probar endpoint local
   
    El valor *{search}* es la busqueda para los productos
    ```
        http://localhost:8080/api/walmart/v1/product/filter/{search}
    ```

4. La respuesta cuando la busqueda es palindrome

    ```
    [
      {
        "id": 121,
        "brand": "erehzgj",
        "description": "gzifl ngfxpr",
        "image": "www.lider.cl/catalogo/images/electronicsIcon.svg",
        "price": 213408,
        "priceWithOutDiscount": 426816,
        "priceHaveDiscount": true
      }
    ]
    ```

4. La respuesta cuando la busqueda no es palindrome

    ```
    [
      {
        "id": 121,
        "brand": "erehzgj",
        "description": "gzifl ngfxpr",
        "image": "www.lider.cl/catalogo/images/electronicsIcon.svg",
        "price": 426816,
        "priceWithOutDiscount": 0,
        "priceHaveDiscount": false
      }
    ]
    ```

### Pruebas

1. Para correr las pruebas unitarias en tu local
    ```
    $ make unit-test
    ```
2. Para correr las pruebas unitarias en docker
    ```
    $ make docker-unit-test
    ```   
3. Para correr las pruebas de integracion en tu local
    ```
    $ make integration-test
    ```
     > Nota: El contenedor de pruebas de SpringBoot2 junto con Flapdoodle OSS levanta una base de datos 
     de mongoDB embebida en cada ejecucion y se cargan 3 registros para realizar 
     las pruebas. Puedes mirar la clase MongoInit.
     Referencia: https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo

4. Para correr las pruebas de integracion en docker
    ```
    $ make docker-integration-test
    ```

### Dependencias
1. Java 8
2. Gradle 4.10.2
3. Docker
4. Springboot 2
5. MongoDB



