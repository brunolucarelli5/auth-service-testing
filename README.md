# Servicio de Autenticación - Pruebas Unitarias

Este proyecto es un servicio de autenticación para realizar pruebas unitarias, desarrollado con Spring Boot.

## Requisitos

Para ejecutar el proyecto, es necesario tener instalados los siguientes componentes:

- **Java** >= 17
- **Maven**

## Variables de Entorno

Antes de ejecutar el proyecto, es necesario configurar las siguientes variables de entorno:

- `DATABASE_URL`: URL de conexión a la base de datos (ejemplo: `jdbc:postgresql://{host}:{puerto}/{nombre de la base de datos}`)
- `DATABASE_USERNAME`: Nombre de usuario para la base de datos.
- `DATABASE_PASSWORD`: Contraseña del usuario para la base de datos.
- `JWT_SECRET_KEY`: Clave secreta para generar JWT. Debe ser hasheada utilizando SHA256. Puedes generar el hash en la siguiente [herramienta en línea](https://www.devglan.com/online-tools/hmac-sha256-online).

### Cómo Configurar Variables de Entorno

Dependiendo de tu sistema operativo, las variables de entorno se pueden definir de la siguiente manera:

#### En Windows

1. Abre una consola.
2. Ejecuta los siguientes comandos:

```bash
setx DATABASE_URL "jdbc:postgresql://localhost:5432/servicioauth"
setx DATABASE_USERNAME "usuario"
setx DATABASE_PASSWORD "contraseña"
setx JWT_SECRET_KEY "tu_clave_jwt_hash"
```

#### En Linux

1. Abre una consola.
2. Ejecuta los siguientes comandos:

```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/servicioauth
export DATABASE_USERNAME=usuario
export DATABASE_PASSWORD=contraseña
export JWT_SECRET_KEY=tu_clave_jwt_hash
```

# Ejecución del proyecto
Después de haber configurado las variables de entorno, puedes ejecutar el proyecto con el siguiente comando:
```bash
mvn spring-boot:run
```
