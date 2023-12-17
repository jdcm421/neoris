# neoris
# Importar el proyecto
# configurar properties y usuario y password de pase de datos
# Crear base de datos MySQL
# ejectuar todas las dependencias 
mvn clean package
# generar el compilado
mvnw spring-boot:run
# Visualizar swagger
http://localhost:8080/neoris/swagger-ui/#/