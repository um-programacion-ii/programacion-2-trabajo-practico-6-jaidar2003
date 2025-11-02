# 🚀 Trabajo Práctico: Sistema de Microservicios con Spring Boot y Feign

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-3.9.6-red)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2025.0.0-blue)
![Feign](https://img.shields.io/badge/Feign-13.0-purple)
![Docker](https://img.shields.io/badge/Docker-25.0-cyan)
![MySQL](https://img.shields.io/badge/MySQL-8.4-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![H2](https://img.shields.io/badge/H2-2.2-green)

## ⚠️ Importante: Antes de Comenzar

1. **Lectura Completa**
   - Es **OBLIGATORIO** leer la consigna completa antes de comenzar a trabajar
   - Asegúrate de entender todos los requisitos y etapas
   - Consulta las dudas antes de iniciar el desarrollo

2. **Configuración del Repositorio**
   - La rama `main` debe estar protegida
   - No se permiten pushes directos a `main`
   - Todo el desarrollo debe realizarse en ramas feature
   - Los cambios deben integrarse mediante Pull Requests

## 🔧 Configuración Inicial del Repositorio

### 1. Protección de la Rama Main
1. En "Branch name pattern" escribir `main`
2. Marcar la siguiente opción:
   - ✓ Require a pull request before merging
3. Hacer clic en "Create"

> 💡 **Nota**: La protección de la rama main es obligatoria y asegura que:
> - No se puedan hacer cambios directos en la rama main
> - Todos los cambios deben hacerse a través de Pull Requests
> - Esto ayuda a mantener un historial de cambios ordenado y a seguir buenas prácticas de desarrollo

### 2. Configuración de Issues y Pull Requests
1. Ir a Settings > General
2. En la sección "Features":
   - ✓ Habilitar Issues
   - ✓ Habilitar Pull Requests (para trabajo individual con ramas)
3. En la sección "Pull Requests":
   - ✓ Habilitar "Allow merge commits"
   - ✓ Habilitar "Allow squash merging"
   - ✓ Deshabilitar "Allow rebase merging"

### 3. Configuración de Project Board
1. Ir a la pestaña "Projects"
2. Crear nuevo proyecto "Sistema de Microservicios"
3. Configurar las siguientes columnas:
   - To Do
   - In Progress
   - Code Review (auto-revisión)
   - Done

### 4. Configuración de Milestones
1. Ir a la pestaña "Milestones"
2. Crear los siguientes milestones:
   - Etapa 1: Configuración y Microservicio de Datos
   - Etapa 2: Microservicio de Negocio y Feign
   - Etapa 3: Profiles y Configuración de BD
   - Etapa 4: Docker, Testing y Documentación

### 5. Configuración de Labels
1. Ir a Issues > Labels
2. Crear las siguientes etiquetas:
   - `enhancement` (verde)
   - `bug` (rojo)
   - `documentation` (azul)
   - `testing` (amarillo)
   - `setup` (gris)
   - `microservice` (morado)
   - `feign` (naranja)
   - `database` (rosa)
   - `docker` (turquesa)
   - `profile` (lila)

### 6. Configuración de Git Local
```bash
# Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_REPOSITORIO>

# Crear rama de desarrollo
git checkout -b develop

# Subir la rama develop
git push -u origin develop
```

> 💡 **Nota**: Aunque este trabajo se realiza individualmente, se utilizan Pull Requests para mantener un historial de cambios ordenado y seguir buenas prácticas de desarrollo. Los Pull Requests serán auto-aprobados por el mismo estudiante.

## 🎯 Objetivo General

Desarrollar un sistema de microservicios utilizando Spring Boot y Feign, implementando dos microservicios independientes: uno para manejar la base de datos y otro para las reglas de negocio. El sistema deberá utilizar diferentes profiles para trabajar con múltiples bases de datos (H2 en desarrollo, MySQL y PostgreSQL en producción), aplicando las mejores prácticas de arquitectura de microservicios y comunicación entre servicios.

## ⏰ Tiempo Estimado y Entrega

- **Tiempo estimado de realización:** 35-40 horas

### Desglose estimado por etapa:
- Configuración inicial y microservicio de datos: 10-12 horas
- Microservicio de negocio y Feign: 12-15 horas
- Profiles y configuración de bases de datos: 8-10 horas
- Docker, Testing y documentación: 5-8 horas

> 💡 **Nota**: Esta estimación considera la complejidad de configurar microservicios, comunicación entre servicios con Feign, múltiples bases de datos y Docker. El tiempo incluye el aprendizaje de conceptos de microservicios y Spring Cloud.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Juan Manuel Aidar
- **Legajo**: 62005

> ⚠️ **IMPORTANTE**: Este trabajo práctico se realiza **INDIVIDUALMENTE**. Aunque se utilizan herramientas de colaboración como Pull Requests y Code Review, estas son para mantener buenas prácticas de desarrollo y un historial ordenado. Todo el desarrollo debe ser realizado por el mismo estudiante.

## 📋 Requisitos Previos

- Java 21 o superior
- Maven 3.9.6 o superior
- Docker y Docker Compose
- Conocimientos básicos de:
  - Programación orientada a objetos
  - Spring Framework básico
  - Conceptos básicos de bases de datos
  - Docker básico
  - Conceptos básicos de microservicios

## 🧩 Tecnologías y Herramientas

- Spring Boot 3.5.0
- Spring Cloud 2025.0.0
- Spring Cloud OpenFeign 4.1.0
- Spring Data JPA
- Spring Web
- Spring Test
- Hibernate 6.4
- H2 Database 2.2
- MySQL 8.4
- PostgreSQL 16
- Docker 25.0 y Docker Compose
- JUnit 5.10.1
- Mockito 5.8.0
- Git y GitHub

## 📊 Casos de Uso del Sistema

### CU-001: Gestionar Productos
**Actor Principal**: Administrador del Sistema

**Descripción**: El administrador debe poder gestionar la información de los productos del catálogo, incluyendo su registro, modificación, consulta y eliminación.

**Flujo Principal**:
1. El administrador accede al microservicio de negocio
2. Selecciona la opción "Gestionar Productos"
3. El sistema muestra la lista de productos
4. El administrador puede realizar las siguientes operaciones:
   - Registrar nuevo producto
   - Consultar información de producto
   - Modificar datos de producto
   - Eliminar producto
   - Buscar productos por categoría
   - Filtrar productos por precio

**Casos de Uso Relacionados**:
- CU-002: Gestionar Categorías
- CU-003: Gestionar Inventario

### CU-002: Gestionar Categorías
**Actor Principal**: Administrador del Sistema

**Descripción**: El administrador debe poder gestionar las categorías de productos, incluyendo su creación, modificación y consulta de productos asociados.

**Flujo Principal**:
1. El administrador accede al microservicio de negocio
2. Selecciona la opción "Gestionar Categorías"
3. El sistema muestra la lista de categorías
4. El administrador puede realizar las siguientes operaciones:
   - Crear nueva categoría
   - Consultar información de categoría
   - Modificar datos de categoría
   - Eliminar categoría
   - Consultar productos de la categoría
   - Calcular estadísticas de la categoría

### CU-003: Gestionar Inventario
**Actor Principal**: Administrador de Inventario

**Descripción**: El administrador de inventario debe poder gestionar el stock de productos, incluyendo actualizaciones de cantidad y alertas de stock bajo.

**Flujo Principal**:
1. El administrador accede al microservicio de datos
2. Selecciona la opción "Gestionar Inventario"
3. El sistema muestra el estado del inventario
4. El administrador puede realizar las siguientes operaciones:
   - Actualizar cantidad de stock
   - Consultar productos con stock bajo
   - Generar reportes de inventario
   - Configurar alertas de stock mínimo
   - Registrar movimientos de inventario

### CU-004: Consultar Reportes
**Actor Principal**: Gerente General

**Descripción**: El gerente general debe poder consultar reportes sobre productos, categorías e inventario para la toma de decisiones.

**Flujo Principal**:
1. El gerente accede al microservicio de negocio
2. Selecciona la opción "Reportes"
3. El sistema muestra las opciones de reportes disponibles:
   - Productos por categoría
   - Productos con stock bajo
   - Valor total del inventario
   - Productos más vendidos
   - Estadísticas por categoría

## 🔄 Diagramas de Secuencia

### Diagrama de Secuencia: Registrar Producto
```
Cliente HTTP    BusinessController    BusinessService    FeignClient    DataController    DataService    Base de Datos
     |                   |                     |                |                |                |                |
     | POST /api/productos|                     |                |                |                |                |
     |------------------->|                     |                |                |                |                |
     |                   |                     |                |                |                |                |
     |                   | validarProducto()   |                |                |                |                |
     |                   |-------------------->|                |                |                |                |
     |                   |                     |                |                |                |                |
     |                   |                     | POST /data/productos            |                |                |
     |                   |                     |----------------------------->|                |                |
     |                   |                     |                                |                |                |
     |                   |                     |                                | guardarProducto()               |
     |                   |                     |                                |---------------->|                |
     |                   |                     |                                |                 |                |
     |                   |                     |                                |                 | save()         |
     |                   |                     |                                |                 |--------------->|
     |                   |                     |                                |                 |                |
     |                   |                     |                                |                 |<---------------|
     |                   |                     |                                |<----------------|                |
     |                   |                     |                                |                |                |
     |                   |                     |<-----------------------------|                |                |
     |                   |<--------------------|                |                |                |                |
     |                   |                     |                |                |                |                |
     | 201 Created       |                     |                |                |                |                |
     |<------------------|                     |                |                |                |                |
```

### Diagrama de Secuencia: Consultar Productos por Categoría
```
Cliente HTTP    BusinessController    BusinessService    FeignClient    DataController    DataService    Base de Datos
     |                   |                     |                |                |                |                |
     | GET /api/productos/|                     |                |                |                |                |
     | categoria/{nombre} |                     |                |                |                |                |
     |------------------->|                     |                |                |                |                |
     |                   |                     |                |                |                |                |
     |                   | buscarPorCategoria()                 |                |                |                |
     |                   |-------------------->|                |                |                |                |
     |                   |                     |                |                |                |                |
     |                   |                     | GET /data/productos/categoria/{nombre}          |                |
     |                   |                     |----------------------------->|                |                |
     |                   |                     |                                |                |                |
     |                   |                     |                                | buscarPorCategoria()             |
     |                   |                     |                                |---------------->|                |
     |                   |                     |                                |                 |                |
     |                   |                     |                                |                 | findByCategoria() |
     |                   |                     |                                |                 |--------------->|
     |                   |                     |                                |                 |                |
     |                   |                     |                                |                 |<---------------|
     |                   |                     |                                |<----------------|                |
     |                   |                     |                                |                |                |
     |                   |                     |<-----------------------------|                |                |
     |                   |<--------------------|                |                |                |                |
     |                   |                     |                |                |                |                |
     | 200 OK            |                     |                |                |                |                |
     |<------------------|                     |                |                |                |                |
```

### Diagrama de Secuencia: Actualizar Inventario
```
Cliente HTTP          DataController         DataService         Base de Datos
     |                       |                     |                    |
     | PUT /data/inventario/ |                     |                    |
     | {id}/stock            |                     |                    |
     |---------------------->|                     |                    |
     |                       |                     |                    |
     |                       | actualizarStock()   |                    |
     |                       |-------------------->|                    |
     |                       |                     |                    |
     |                       |                     | findById()         |
     |                       |                     |------------------->|
     |                       |                     |                    |
     |                       |                     |<-------------------|
     |                       |                     |                    |
     |                       |                     | save()             |
     |                       |                     |------------------->|
     |                       |                     |                    |
     |                       |                     |<-------------------|
     |                       |<--------------------|                    |
     |                       |                     |                    |
     | 200 OK                |                     |                    |
     |<----------------------|                     |                    |
```

## 📘 Etapas del Trabajo

### Etapa 1: Configuración del Proyecto y Microservicio de Datos

#### Objetivos
- Configurar la estructura de microservicios
- Implementar el microservicio de datos
- Configurar las entidades JPA
- Implementar repositories y servicios de datos

#### Tareas
1. Crear la estructura de proyectos:
   ```
   microservices-system/
   ├── data-service/          # Microservicio de datos
   ├── business-service/      # Microservicio de negocio
   ├── docker-compose.yml     # Configuración de bases de datos
   └── README.md
   ```

2. Configurar el microservicio de datos (`data-service`):
   - `spring-boot-starter-data-jpa`
   - `spring-boot-starter-web`
   - `spring-boot-starter-test`
   - `h2` (para desarrollo)
   - `mysql-connector-java`
   - `postgresql`

3. Implementar las siguientes entidades JPA:
   - `Producto` (id, nombre, descripcion, precio, categoria, stock)
   - `Categoria` (id, nombre, descripcion, productos)
   - `Inventario` (id, producto, cantidad, stockMinimo, fechaActualizacion)

4. Configurar las relaciones:
   - Un producto pertenece a una categoría (ManyToOne)
   - Una categoría tiene muchos productos (OneToMany)
   - Un producto tiene un inventario (OneToOne)

#### Ejemplo de Implementación
```java
// data-service/src/main/java/com/example/dataservice/entity/Producto.java
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(length = 500)
    private String descripcion;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Inventario inventario;
}

// data-service/src/main/java/com/example/dataservice/entity/Categoria.java
@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    
    @Column(length = 500)
    private String descripcion;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();
}

// data-service/src/main/java/com/example/dataservice/entity/Inventario.java
@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(name = "stock_minimo")
    private Integer stockMinimo;
    
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaActualizacion;
}
```

### Etapa 2: Microservicio de Negocio y Feign

#### Objetivos
- Implementar el microservicio de negocio
- Configurar Feign para comunicación entre servicios
- Implementar reglas de negocio
- Crear DTOs para transferencia de datos

#### Tareas
1. Configurar el microservicio de negocio (`business-service`):
   - `spring-boot-starter-web`
   - `spring-cloud-starter-openfeign`
   - `spring-boot-starter-test`
   - `spring-cloud-dependencies`

2. Implementar Feign Client para comunicación con el microservicio de datos:
   - `DataServiceClient` interface
   - Configuración de Feign
   - Manejo de errores y timeouts

3. Implementar DTOs:
   - `ProductoDTO`
   - `CategoriaDTO`
   - `InventarioDTO`
   - `ProductoRequest`
   - `ProductoResponse`

4. Implementar servicios de negocio:
   - `ProductoBusinessService`
   - `CategoriaBusinessService`
   - `InventarioBusinessService`

#### Ejemplo de Implementación
```java
// business-service/src/main/java/com/example/businessservice/client/DataServiceClient.java
@FeignClient(name = "data-service", url = "${data.service.url}")
public interface DataServiceClient {
    
    @GetMapping("/data/productos")
    List<ProductoDTO> obtenerTodosLosProductos();
    
    @GetMapping("/data/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable Long id);
    
    @PostMapping("/data/productos")
    ProductoDTO crearProducto(@RequestBody ProductoRequest request);
    
    @PutMapping("/data/productos/{id}")
    ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest request);
    
    @DeleteMapping("/data/productos/{id}")
    void eliminarProducto(@PathVariable Long id);
    
    @GetMapping("/data/productos/categoria/{nombre}")
    List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable String nombre);
    
    @GetMapping("/data/categorias")
    List<CategoriaDTO> obtenerTodasLasCategorias();
    
    @GetMapping("/data/inventario/stock-bajo")
    List<InventarioDTO> obtenerProductosConStockBajo();
}

// business-service/src/main/java/com/example/businessservice/dto/ProductoDTO.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoriaNombre;
    private Integer stock;
    private Boolean stockBajo;
}

// business-service/src/main/java/com/example/businessservice/service/ProductoBusinessService.java
@Service
@Slf4j
public class ProductoBusinessService {
    
    private final DataServiceClient dataServiceClient;
    
    public ProductoBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }
    
    public List<ProductoDTO> obtenerTodosLosProductos() {
        try {
            return dataServiceClient.obtenerTodosLosProductos();
        } catch (FeignException e) {
            log.error("Error al obtener productos del microservicio de datos", e);
            throw new MicroserviceCommunicationException("Error de comunicación con el servicio de datos");
        }
    }
    
    public ProductoDTO obtenerProductoPorId(Long id) {
        try {
            return dataServiceClient.obtenerProductoPorId(id);
        } catch (FeignException.NotFound e) {
            throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + id);
        } catch (FeignException e) {
            log.error("Error al obtener producto del microservicio de datos", e);
            throw new MicroserviceCommunicationException("Error de comunicación con el servicio de datos");
        }
    }
    
    public ProductoDTO crearProducto(ProductoRequest request) {
        // Validaciones de negocio
        validarProducto(request);
        
        try {
            return dataServiceClient.crearProducto(request);
        } catch (FeignException e) {
            log.error("Error al crear producto en el microservicio de datos", e);
            throw new MicroserviceCommunicationException("Error de comunicación con el servicio de datos");
        }
    }
    
    private void validarProducto(ProductoRequest request) {
        if (request.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidacionNegocioException("El precio debe ser mayor a cero");
        }
        
        if (request.getStock() < 0) {
            throw new ValidacionNegocioException("El stock no puede ser negativo");
        }
    }
}
```

### Etapa 3: Controllers y Profiles

#### Objetivos
- Implementar controladores REST en ambos microservicios
- Configurar diferentes profiles para H2, MySQL y PostgreSQL
- Manejar excepciones HTTP
- Implementar validaciones

#### Tareas
1. Crear controladores REST:
   - `DataController` (en data-service)
   - `BusinessController` (en business-service)

2. Configurar profiles:
   - `dev` (H2 en memoria)
   - `mysql` (MySQL con Docker)
   - `postgres` (PostgreSQL con Docker)

3. Implementar endpoints:
   - Microservicio de datos: `/data/productos`, `/data/categorias`, `/data/inventario`
   - Microservicio de negocio: `/api/productos`, `/api/categorias`, `/api/reportes`

#### Ejemplo de Implementación
```java
// data-service/src/main/java/com/example/dataservice/controller/DataController.java
@RestController
@RequestMapping("/data")
@Validated
public class DataController {
    
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final InventarioService inventarioService;
    
    public DataController(ProductoService productoService,
                         CategoriaService categoriaService,
                         InventarioService inventarioService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.inventarioService = inventarioService;
    }
    
    @GetMapping("/productos")
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodos();
    }
    
    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }
    
    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(@Valid @RequestBody Producto producto) {
        return productoService.guardar(producto);
    }
    
    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }
    
    @DeleteMapping("/productos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
    }
    
    @GetMapping("/productos/categoria/{nombre}")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return productoService.buscarPorCategoria(nombre);
    }
    
    @GetMapping("/inventario/stock-bajo")
    public List<Inventario> obtenerProductosConStockBajo() {
        return inventarioService.obtenerProductosConStockBajo();
    }
}

// business-service/src/main/java/com/example/businessservice/controller/BusinessController.java
@RestController
@RequestMapping("/api")
@Validated
public class BusinessController {
    
    private final ProductoBusinessService productoBusinessService;
    private final CategoriaBusinessService categoriaBusinessService;
    
    public BusinessController(ProductoBusinessService productoBusinessService,
                             CategoriaBusinessService categoriaBusinessService) {
        this.productoBusinessService = productoBusinessService;
        this.categoriaBusinessService = categoriaBusinessService;
    }
    
    @GetMapping("/productos")
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoBusinessService.obtenerTodosLosProductos();
    }
    
    @GetMapping("/productos/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Long id) {
        return productoBusinessService.obtenerProductoPorId(id);
    }
    
    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDTO crearProducto(@Valid @RequestBody ProductoRequest request) {
        return productoBusinessService.crearProducto(request);
    }
    
    @GetMapping("/productos/categoria/{nombre}")
    public List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return productoBusinessService.obtenerProductosPorCategoria(nombre);
    }
    
    @GetMapping("/reportes/stock-bajo")
    public List<ProductoDTO> obtenerProductosConStockBajo() {
        return productoBusinessService.obtenerProductosConStockBajo();
    }
    
    @GetMapping("/reportes/valor-inventario")
    public BigDecimal obtenerValorTotalInventario() {
        return productoBusinessService.calcularValorTotalInventario();
    }
}
```

#### Configuración de Profiles
```yaml
# data-service/src/main/resources/application.yml
spring:
  profiles:
    active: dev

---
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8081

---
spring:
  config:
    activate:
      on-profile: mysql
  datasource:
    url: jdbc:mysql://localhost:3306/microservices_db?useSSL=false&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: microservices_user
    password: microservices_pass
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true

server:
  port: 8081

---
spring:
  config:
    activate:
      on-profile: postgres
  datasource:
    url: jdbc:postgresql://localhost:5432/microservices_db
    driver-class-name: org.postgresql.Driver
    username: microservices_user
    password: microservices_pass
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true

server:
  port: 8080
```

### Etapa 4: Docker, Testing y Documentación

#### Objetivos
- Configurar Docker Compose para las bases de datos
- Implementar tests unitarios y de integración
- Documentar la API y el código
- Asegurar la calidad del código

#### Tareas
1. Crear Docker Compose para MySQL y PostgreSQL
2. Implementar tests:
   - Tests unitarios para servicios
   - Tests de integración para controladores
   - Tests de comunicación entre microservicios
   - Tests de Feign Client

3. Documentar:
   - Documentar endpoints con comentarios
   - Actualizar README con instrucciones
   - Documentar configuración de Docker

#### Docker Compose
```yaml
# docker-compose.yml
services:
  mysql:
    image: mysql:8.4
    container_name: microservices_mysql
    environment:
      MYSQL_ROOT_PASSWORD: root_password
      MYSQL_DATABASE: microservices_db
      MYSQL_USER: microservices_user
      MYSQL_PASSWORD: microservices_pass
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - microservices_network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      timeout: 20s
      retries: 10

  postgres:
    image: postgres:16
    container_name: microservices_postgres
    environment:
      POSTGRES_DB: microservices_db
      POSTGRES_USER: microservices_user
      POSTGRES_PASSWORD: microservices_pass
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - microservices_network
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U microservices_user -d microservices_db"]
      timeout: 20s
      retries: 10

volumes:
  mysql_data:
  postgres_data:

networks:
  microservices_network:
    driver: bridge
```

#### Ejemplo de Test
```java
// business-service/src/test/java/com/example/businessservice/service/ProductoBusinessServiceTest.java
@ExtendWith(MockitoExtension.class)
class ProductoBusinessServiceTest {
    
    @Mock
    private DataServiceClient dataServiceClient;
    
    @InjectMocks
    private ProductoBusinessService productoBusinessService;
    
    @Test
    void cuandoObtenerTodosLosProductos_entoncesRetornaLista() {
        // Arrange
        List<ProductoDTO> productosEsperados = Arrays.asList(
            new ProductoDTO(1L, "Producto 1", "Descripción 1", BigDecimal.valueOf(100), "Categoría 1", 10, false),
            new ProductoDTO(2L, "Producto 2", "Descripción 2", BigDecimal.valueOf(200), "Categoría 2", 5, true)
        );
        
        when(dataServiceClient.obtenerTodosLosProductos()).thenReturn(productosEsperados);
        
        // Act
        List<ProductoDTO> resultado = productoBusinessService.obtenerTodosLosProductos();
        
        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Producto 1", resultado.get(0).getNombre());
        verify(dataServiceClient).obtenerTodosLosProductos();
    }
    
    @Test
    void cuandoCrearProductoConPrecioInvalido_entoncesLanzaExcepcion() {
        // Arrange
        ProductoRequest request = new ProductoRequest();
        request.setNombre("Producto Test");
        request.setPrecio(BigDecimal.valueOf(-10));
        request.setStock(5);
        
        // Act & Assert
        assertThrows(ValidacionNegocioException.class, () -> {
            productoBusinessService.crearProducto(request);
        });
        
        verify(dataServiceClient, never()).crearProducto(any());
    }
}

// data-service/src/test/java/com/example/dataservice/controller/DataControllerIntegrationTest.java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("test")
class DataControllerIntegrationTest {
    
    private final TestRestTemplate restTemplate;
    private final ProductoService productoService;
    
    public DataControllerIntegrationTest(TestRestTemplate restTemplate, 
                                       ProductoService productoService) {
        this.restTemplate = restTemplate;
        this.productoService = productoService;
    }
    
    @Test
    void cuandoCrearProducto_entoncesSePersisteCorrectamente() {
        // Arrange
        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción de prueba");
        producto.setPrecio(BigDecimal.valueOf(100.50));
        
        // Act
        ResponseEntity<Producto> response = restTemplate.postForEntity(
            "/data/productos", producto, Producto.class);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Producto Test", response.getBody().getNombre());
    }
    
    @Test
    void cuandoBuscarProductoInexistente_entoncesRetorna404() {
        // Act
        ResponseEntity<Producto> response = restTemplate.getForEntity(
            "/data/productos/999", Producto.class);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
   - Proteger la rama `main`
   - Configurar para trabajo individual

2. **Project Kanban**
   - `To Do`
   - `In Progress`
   - `Code Review` (auto-revisión)
   - `Done`

3. **Milestones**
   - Etapa 1: Configuración y Microservicio de Datos
   - Etapa 2: Microservicio de Negocio y Feign
   - Etapa 3: Controllers y Profiles
   - Etapa 4: Docker y Testing

4. **Issues y Pull Requests (Trabajo Individual)**
   - Crear Issues detallados para cada funcionalidad
   - Asociar cada Issue a un Milestone
   - Implementar en ramas feature
   - Auto-revisar código antes de merge
   - Los Pull Requests serán aprobados por el mismo estudiante

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Configuración de profiles para H2, MySQL y PostgreSQL
- ✅ Docker Compose funcional para MySQL y PostgreSQL
- ✅ Comunicación entre microservicios con Feign
- ✅ Código bien documentado
- ✅ Tests unitarios y de integración
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests auto-aprobados (trabajo individual)
- ✅ Project actualizado
- ✅ README.md completo con:
  - Instrucciones de instalación
  - Requisitos del sistema
  - Ejemplos de uso
  - Documentación de endpoints
  - Instrucciones para Docker
- ✅ **Documentación de Prompts**: Archivos MD que incluyan:
  - `prompts-desarrollo.md`: Prompts utilizados para el desarrollo del código
  - `prompts-testing.md`: Prompts utilizados para la implementación de tests
  - `prompts-docker.md`: Prompts utilizados para la configuración de Docker
  - `prompts-documentacion.md`: Prompts utilizados para la documentación
  - `prompts-microservicios.md`: Prompts utilizados para la configuración de microservicios
  - `prompts-feign.md`: Prompts utilizados para la configuración de Feign
  - Cada archivo debe incluir:
    - El prompt completo utilizado
    - La respuesta recibida
    - Modificaciones realizadas al código generado
    - Explicación de por qué se usó ese prompt
    - Aprendizajes obtenidos del uso de IA

## 🐳 Instrucciones para Docker

### Levantar Bases de Datos
```bash
# Levantar MySQL y PostgreSQL
docker compose up -d

# Verificar que los contenedores estén corriendo
docker compose ps

# Ver logs de los contenedores
docker compose logs -f
```

### Ejecutar Microservicios con Diferentes Profiles
```bash
# Con H2 (desarrollo)
# Terminal 1 - Data Service
cd data-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Terminal 2 - Business Service
cd business-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Con MySQL
# Terminal 1 - Data Service
cd data-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql

# Terminal 2 - Business Service
cd business-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql

# Con PostgreSQL
# Terminal 1 - Data Service
cd data-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres

# Terminal 2 - Business Service
cd business-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

### Detener Bases de Datos
```bash
# Detener contenedores
docker compose down

# Detener y eliminar volúmenes
docker compose down -v
```

## 📚 Recursos Adicionales

- [Documentación de Spring Cloud OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)
- [Spring Boot Microservices](https://spring.io/projects/spring-boot)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Microservices Best Practices](https://www.geeksforgeeks.org/java-spring-boot-microservices-example-step-by-step-guide/)
- [Spring Boot Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [MySQL Docker Hub](https://hub.docker.com/_/mysql)
- [PostgreSQL Docker Hub](https://hub.docker.com/_/postgres)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [Feign Best Practices](https://medium.com/ms-club-of-sliit/spring-boot-microservices-best-practices-and-coding-style-guidelines-d48aa371b75e)

## 📋 Guía de Testing con Microservicios

### 1. Testing de Microservicios Individuales
- Usar `@SpringBootTest` para pruebas de integración
- Configurar `@TestPropertySource` para usar H2 en tests
- Usar `@Transactional` para rollback automático
- Probar endpoints REST con `TestRestTemplate`

### 2. Testing de Comunicación entre Microservicios
- Mockear Feign Clients en tests unitarios
- Usar `@MockBean` para simular servicios externos
- Probar diferentes escenarios de fallo de comunicación
- Verificar timeouts y circuit breakers

### 3. Testing de Feign Client
- Usar `@FeignTest` para pruebas específicas de Feign
- Probar diferentes tipos de respuesta HTTP
- Verificar manejo de errores y excepciones
- Testear configuración de timeouts

### 4. Testing con Diferentes Bases de Datos
- Configurar profiles específicos para testing
- Usar `@ActiveProfiles` para activar el profile correcto
- Probar compatibilidad entre diferentes bases de datos
- Verificar que las consultas funcionen en todas las bases

### 5. Buenas Prácticas de Testing con Microservicios
- Usar `@DirtiesContext` cuando sea necesario
- Limpiar datos entre tests
- Usar `@Sql` para cargar datos de prueba
- Probar casos edge y validaciones
- Simular fallos de red y servicios no disponibles

## 📝 Consideraciones Éticas sobre el Uso de IA

El uso de Inteligencia Artificial (IA) en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
   - Documentar el uso de IA en el desarrollo
   - Explicar las modificaciones realizadas al código generado
   - Mantener un registro de las herramientas utilizadas
   - **Documentar todos los prompts utilizados en archivos MD separados**

2. **Aprendizaje**
   - La IA debe usarse como herramienta de aprendizaje
   - Comprender y ser capaz de explicar el código generado
   - Utilizar la IA para mejorar la comprensión de conceptos de microservicios
   - **Reflexionar sobre los aprendizajes obtenidos de cada prompt**

3. **Integridad Académica**
   - El trabajo final debe reflejar tu aprendizaje
   - No se permite la presentación de código sin comprensión
   - Debes poder explicar y defender cualquier parte del código
   - **Los prompts documentados deben mostrar el proceso de aprendizaje**

4. **Responsabilidad**
   - Verificar la corrección del código generado
   - Asegurar que el código cumple con los requisitos
   - Mantener la calidad y estándares de código
   - **Asumir responsabilidad por el código final, independientemente de su origen**

5. **Desarrollo Individual**
   - La IA puede usarse para facilitar el aprendizaje
   - Documentar el proceso de desarrollo
   - Mantener un registro del progreso
   - **Cada prompt debe incluir una reflexión personal sobre su utilidad**

### 📋 Documentación Obligatoria de Prompts

Como parte de la entrega, debes incluir los siguientes archivos:

#### `prompts-desarrollo.md`
- Prompts utilizados para crear entidades JPA
- Prompts para implementar repositories y services
- Prompts para configurar controllers
- Explicación de las decisiones de diseño tomadas

#### `prompts-testing.md`
- Prompts para crear tests unitarios
- Prompts para implementar tests de integración
- Prompts para configurar testing con diferentes bases de datos
- Reflexiones sobre la importancia del testing

#### `prompts-docker.md`
- Prompts para configurar Docker Compose
- Prompts para resolver problemas de containerización
- Prompts para optimizar la configuración
- Aprendizajes sobre DevOps y containerización

#### `prompts-documentacion.md`
- Prompts para generar documentación técnica
- Prompts para crear diagramas de secuencia
- Prompts para documentar casos de uso
- Reflexiones sobre la importancia de la documentación

#### `prompts-microservicios.md`
- Prompts para configurar la arquitectura de microservicios
- Prompts para implementar la separación de responsabilidades
- Prompts para configurar la comunicación entre servicios
- Aprendizajes sobre arquitectura de microservicios

#### `prompts-feign.md`
- Prompts para configurar Feign Client
- Prompts para manejar errores de comunicación
- Prompts para optimizar la configuración de Feign
- Aprendizajes sobre comunicación entre microservicios

### 📝 Formato de Documentación de Prompts

Cada archivo debe seguir este formato:

```markdown
# Prompts de [Categoría]

## Prompt 1: [Descripción breve]

### Prompt Utilizado:
```
[Prompt completo aquí]
```

### Respuesta Recibida:
```
[Respuesta completa aquí]
```

### Modificaciones Realizadas:
- [Lista de cambios realizados al código generado]
- [Explicación de por qué se modificó]

### Explicación del Prompt:
[Explicar por qué se usó este prompt específico]

### Aprendizajes Obtenidos:
- [Lista de aprendizajes]
- [Conceptos nuevos comprendidos]
- [Buenas prácticas identificadas]

---

## Prompt 2: [Descripción breve]
[Repetir estructura para cada prompt]
```

## 📝 Licencia

Este trabajo es parte del curso de Programación II de Ingeniería en Informática. Uso educativo únicamente.
