# Aplicación Spring Boot

Esta es una aplicación desarrollada en **Spring Boot**. Sigue los pasos a continuación para ejecutarla correctamente.

## Requisitos previos

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

1. **Java JDK 17** o superior.
2. **Maven** (para gestionar dependencias).
3. Un editor o IDE como **IntelliJ IDEA** o **Eclipse**.
4. Una base de datos si tu aplicación la utiliza (por ejemplo, MySQL, PostgreSQL, etc.).

## Pasos para ejecutar la aplicación

1. **Clonar el repositorio**
   ```bash
   git clone <URL-del-repositorio>
   cd <nombre-del-repositorio>
## Pasos para ejecutar la aplicación

2. **Configurar el archivo `application.properties`**
   - Abre el archivo en `src/main/resources/application.properties`.
   - Configura los detalles necesarios, como la base de datos:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

3. **Compilar el proyecto**
   - Ejecuta el siguiente comando para descargar dependencias y compilar el proyecto:
     ```bash
     mvn clean install
     ```

4. **Ejecutar la aplicación**
   - Inicia la aplicación con:
     ```bash
     mvn spring-boot:run
     ```
   - Alternativamente, ejecuta el archivo `.jar` generado:
     ```bash
     java -jar target/tu-aplicacion.jar
     ```

5. **Abrir en el navegador**
   - Una vez iniciada, accede a la aplicación desde:
     ```
     http://localhost:8080
     ```

## Notas adicionales

- Si necesitas cambiar el puerto, edita el archivo `application.properties`:
  ```properties
  server.port=puerto_deseado
