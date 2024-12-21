README.md
markdown
Copiar código
# Literalura: Gestión de Catálogo de Libros

Literalura es una aplicación de consola desarrollada en **Java** con **Spring Boot** y **PostgreSQL**. Permite gestionar un catálogo de libros, obteniendo datos desde la API [Gutendex](https://gutendex.com/) y almacenándolos en una base de datos.

## Características

- **Buscar libro por título:** Obtiene datos desde Gutendex, incluyendo título, autor, idioma y descargas. Guarda el libro en la base de datos, evitando duplicados.
- **Listar libros registrados:** Muestra todos los libros almacenados.
- **Listar autores registrados:** Muestra todos los autores presentes en la base de datos.
- **Listar autores vivos en un año determinado:** Permite buscar autores que estaban vivos en un año específico.
- **Listar libros por idioma:** Filtra libros según el idioma (ES, EN, FR, PT).

## Tecnologías Utilizadas

- **Java 11**
- **Spring Boot**:
  - Spring Web
  - Spring Data JPA
- **PostgreSQL**
- **Gutendex API** para obtener información de libros
- **JSON** para procesar respuestas de la API

## Requisitos Previos

1. **Java 11 o superior** instalado.
2. **Maven** configurado.
3. **PostgreSQL** instalado y en funcionamiento.
4. Conexión a Internet para consumir la API Gutendex.

## Configuración Inicial

### 1. Clonar el repositorio
```bash
git clone https://github.com/SebaOlat3/literalura.git
cd literalura
2. Configurar PostgreSQL
Crea una base de datos llamada literalura:

sql
Copiar código
CREATE DATABASE literalura;
Actualiza el archivo src/main/resources/application.properties con las credenciales de tu base de datos PostgreSQL:

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
3. Compilar el proyecto
Ejecuta el siguiente comando para compilar y construir el proyecto:

bash
Copiar código
mvn clean install
Ejecución
Ejecuta la aplicación con:

bash
Copiar código
mvn spring-boot:run
La aplicación estará disponible en http://localhost:8080.

Uso de la API
1. Buscar libro por título
Obtén información de un libro y guárdalo en la base de datos:

http
Copiar código
GET /api/book?title={title}
Parámetro:

title: Título del libro que deseas buscar.
Ejemplo:

http
Copiar código
GET /api/book?title=pride and prejudice
2. Listar libros registrados
http
Copiar código
GET /api/books
3. Listar autores registrados
http
Copiar código
GET /api/authors
4. Listar autores vivos en un año
http
Copiar código
GET /api/authors/alive?year={year}
Parámetro:

year: Año para buscar autores vivos.
Ejemplo:

http
Copiar código
GET /api/authors/alive?year=1850
5. Listar libros por idioma
http
Copiar código
GET /api/books/language?language={language_code}
Parámetro:

language_code: Código del idioma (ES, EN, FR, PT).
Ejemplo:

http
Copiar código
GET /api/books/language?language=EN
Estructura del Proyecto
bash
Copiar código
literalura/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── literalura/
│   │   │           ├── controller/      # Controladores de API
│   │   │           ├── model/           # Modelos de datos (Book, Author)
│   │   │           ├── repository/      # Repositorios de datos
│   │   │           └── service/         # Lógica de negocio
│   ├── resources/
│       ├── application.properties       # Configuración de base de datos
├── pom.xml                              # Dependencias y configuración de Maven
└── README.md                            # Documentación del proyecto
Ejemplo de Respuesta
Buscar libro por título
Request:

http
Copiar código
GET /api/book?title=pride and prejudice
Response:

json
Copiar código
{
  "id": 1,
  "title": "Pride and Prejudice",
  "language": "EN",
  "downloads": 250000,
  "author": {
    "id": 1,
    "name": "Jane Austen",
    "birthYear": 1775,
    "deathYear": 1817
  }
}
Contribución
¡Contribuye a mejorar Literalura!

Haz un fork del repositorio.
Crea una rama para tus cambios:
bash
Copiar código
git checkout -b mi-rama
Realiza tus cambios y haz un commit:
bash
Copiar código
git commit -m "Añadí una nueva funcionalidad"
Sube los cambios a tu repositorio:
bash
Copiar código
git push origin mi-rama
Crea un Pull Request en el repositorio original.
Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

Autor
Sebastian Vásquez
GitHub: SebaOlat3
