El código corresponde a una evaluación técnica realidad por ionix para ejecutar la aplicación debe considerar lo siguiente


1) Java 1.8
2) Gradle
3) Spring Boot
4) MySQL
5) Debe crear un bd en blanco llamada testdev
6) Por favor tomar en cuenta lo siguientes parametros del properties, en especial la cadena de conexión, que por tratarse de un ambiente local, no tiene clave root ni
usuario especifico

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/testdev?serverTimezone=UTC
spring.datasource.username = root
spring.datasource.password = 
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.hibernate.ddl-auto = update
server.servlet.contextPath= /v1.0/testdev-ws
logging.level.org.springframework.security=TRACE
spring.security.user.name=admin
spring.security.user.password=ionixdev
rut.service.path = https://my.api.mockaroo.com/test-tecnico/search/
rut.service.xapikey = f2f719e0

7) el parametro spring.jpa.hibernate.ddl-auto = update puede cambiarlo por spring.jpa.hibernate.ddl-auto = create, para crear automaticamente la tabla requerida
8) El controlador principal UserController.class tiene un @PostConstruct que crear un registro en tiempo de ejecución
9) La aplicación cuenta con pruebas unitarias básicas, solo para mostrar habilidades básicas y buenas prácticas

Cualquier inquietud o información omitida en este README puede contactarme al +584124579238 o dannysalberto@gmail.com



