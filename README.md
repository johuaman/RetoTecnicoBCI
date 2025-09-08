#RetoTecnicoBCI

Descripción ApiRest para registro de usuarios con integración JWT token, documentación Swagger y pruebas unitarias completas.

Tecnologías Utilizadas

-Java 21

-Spring Boot 3.3.4

-Spring Security (JWT Authentication)

-Spring Data JPA

-H2 Database (para desarrollo)

-Maven 3.11

-Swagger/OpenAPI 3 (springdoc-openapi 2.3.0)

-JWT (jjwt 0.11.5)

MapStruct 1.5.5 (Mapeo de objetos)

Lombok (Reducción de código boilerplate)

JUnit 5 (Pruebas unitarias)

Spring Boot DevTools (Desarrollo)

Características

✅ Registro de usuarios con validación

✅ Autenticación JWT

✅ Documentación automática con Swagger

✅ Pruebas unitarias completas

✅ Validación de datos de entrada

✅ Manejo de excepciones personalizado

✅ Arquitectura limpia y escalable

Requisitos Previos

Java 17 o superior

Maven 3.6 o superior

Spring Boot 3.3.4

IDE de tu preferencia (IntelliJ IDEA recomendado)

Instalación y Configuración

Clonar el repositorio
git clone https://github.com/johuaman/RetoTecnicoBCI.git

branch: feature/RetoTecnicoBCI

cd RetoTecnicoBCI

Compilar el proyecto
mvn spring-boot:run

Ejecutar la aplicación
mvn spring-boot:run

La aplicación estará disponible en: http://localhost:8080

Endpoints Principales

Registro de Usuario

http POST /api/users/register Content-Type: application/json

{

"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
    {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
    }
]
}

Respuesta Exitosa

{

"id": "550e8400-e29b-41d4-a716-446655440000",
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"phones": [...],
"created": "2023-01-01T00:00:00",
"modified": "2023-01-01T00:00:00",
"last_login": "2023-01-01T00:00:00",
"token": "eyJhbGciOiJIUzI1NiJ9...",
"isactive": true
}

Documentación API

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación completa de la API en:

Swagger UI: http://localhost:8080/swagger-ui/index.html

OpenAPI Docs: http://localhost:8080/v3/api-docs

Validaciones Implementadas

Email

-Formato válido de email

-Único en el sistema

Password

-Mínimo 8 caracteres

Al menos una letra mayúscula Al menos una letra minúscula Al menos un dígito Al menos un carácter especial

Base de Datos

La aplicación utiliza H2 Database en memoria para desarrollo. La consola H2 está disponible en:

http://localhost:8080/h2-console

Configuración de conexión:

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (vacío)

Notas Adicionales

La aplicación está configurada para desarrollo con H2 Database

Las pruebas incluyen casos de éxito y error

La documentación Swagger se genera automáticamente

Autor: Jonathan Huamán

Email: jonathanhuaman94@gmail.com
