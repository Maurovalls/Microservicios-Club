# Microservicios-Club

**Objetivos:** 
* Brindar soporte en la gestion de los clientes del club deportivo.
* Proveer informacion de los procesos que abarca.

**¿Que se uso para el proyecto?**

* **Spring Boot:** para poder realizar mejores practicas y sin perder la flexibilidad.
* **Maven:** para facilitar el proyecto y poder utilizar librerias de terceros como JPA,Hibernate,Lombok,etc.
* **JPA:** para traductir los objetos a tabla y visiversa usando el mecanismo de anotaciones.
* **Hbernate:** implementacion de JPA para falicitar el mapeo de objetos de atributos entre una base de datos relacional tradicional y el modelo de objetos de la aplicacion.
* **Lombok:** para generar codigo repetitivo que es necesario para nuestro codigo (getters,setters).
* **JPA Queris:** para hacer consultas o peticiones a la base de datos.
* Se realiazo codigo generico ya que permite la reutilizacion de codigo para varios tipos diferentes,cantidad de mayor calidad,legible,limpio,reduce que ocurra un error,
evita el casteo de clases, rapidez de ejecucion, etc.
* Para la gestion de base de datos se utilizo XAMPP y SQLyog.
* **Postman:** para consumir servicio REST
* **Eureka Server:** para registrar microservicios que son clientes y su ubicacion fisica.
* **Zuul:** para que nos permita el acceso a los demas microservicios, al ecositema que esta registrado en el servidor eureka. Nos permite enrutado dinamico,balanceo de carga, monitorizacion y securizacion de peticiones.
* **Patron de diseño de base compartida:** para mejor integracion de los datos, mas simple de operar.
