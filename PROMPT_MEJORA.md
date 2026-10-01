# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/bankapi/infrastructure/security/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/bankapi/infrastructure/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bankapi/BankApiApplication.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.OpenAPIDefinition pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bankapi/infrastructure/persistence/AccountRepositoryImpl.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bankapi/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.findByIdempotencyKey`: Se invoca `findByIdempotencyKey` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `Account.isPresent`: Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `Account.get`: Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.clientId`: Se invoca `clientId` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.accountType`: Se invoca `accountType` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.initialBalance`: Se invoca `initialBalance` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.currency`: Se invoca `currency` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.description`: Se invoca `description` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `Account.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.save`: Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.findById`: Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.findAll`: Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `AccountService.deposit`: Se invoca `deposit` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `AccountService.withdraw`: Se invoca `withdraw` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountRepository.save`: Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.accountId`: Se invoca `accountId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.clientId`: Se invoca `clientId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.accountType`: Se invoca `accountType` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.balance`: Se invoca `balance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.currency`: Se invoca `currency` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountRepository.existsByClientId`: Se invoca `existsByClientId` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountRepository.findById`: Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.isPresent`: Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.get`: Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.isEmpty`: Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.size`: Se invoca `size` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountServiceImpl.updateAccountBalance`: Se invoca `updateAccountBalance` sobre `AccountServiceImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Crear una API REST con Spring Boot, JPA y documentación OpenAPI

### Reto
- Tema: Creación de una API REST en un entorno de banca y fintech
- Seniority: junior-l2
- Tipo: practical
- Título: Implementación de una API REST para gestión de cuentas bancarias
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición y modelado de la API — objetivo: Definir y modelar la API REST para la gestión de cuentas bancarias. — entregable (NO resolver): Documentación de la API que incluye los endpoints, métodos HTTP, y descripciones de las operaciones.
- Fase 2: Implementación de la lógica de negocio — objetivo: Implementar la lógica de negocio para las operaciones de la API. — entregable (NO resolver): Código fuente que implementa la lógica de negocio para las operaciones de la API.
- Fase 3: Integración y pruebas — objetivo: Integrar la API con el sistema de autenticación y realizar pruebas. — entregable (NO resolver): Código fuente integrado con el sistema de autenticación y documentación de las pruebas realizadas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/>
    </parent>

    <groupId>com.bankapi</groupId>
    <artifactId>bank-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>bank-api</name>
    <description>API REST para gestión de cuentas bancarias</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi-ui.version>2.5.0</springdoc-openapi-ui.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi-ui.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <version>6.3.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

// === ARCHIVO: src/main/java/com/bankapi/BankApiApplication.java ===
package com.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableConfigurationProperties
@OpenAPIDefinition(info = @Info(title = "Bank API", version = "1.0", description = "API para gestión de cuentas bancarias"))
public class BankApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public String applicationStartupMessage() {
        System.out.println("\n" +
                "============================================\n" +
                "Bank API iniciada correctamente\n" +
                "Accede a la documentación OpenAPI en: http://localhost:8080/swagger-ui.html\n" +
                "============================================\n");
        return "Bank API iniciada";
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: bank-api
  datasource:
    url: jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        jdbc:
          time_zone: UTC
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true
  security:
    user:
      name: admin
      password: admin
      roles: ADMIN

server:
  port: 8080
  servlet:
    context-path: /api
  error:
    include-message: always
    include-binding-errors: always

logging:
  level:
    root: INFO
    org.springframework.web: DEBUG
    org.hibernate: ERROR
    com.bankapi: DEBUG

app:
  jwt:
    secret: ${JWT_SECRET:bankapi-secret-key-1234567890}
    expiration-ms: 86400000
    issuer: bank-api
  idempotency:
    key-header: Idempotency-Key
    ttl-seconds: 3600

// === ARCHIVO: src/main/java/com/bankapi/application/dto/AccountRequestDTO.java ===
package com.bankapi.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

public record AccountRequestDTO(
    @NotBlank(message = "El ID del cliente es obligatorio")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
             message = "El ID del cliente debe ser un UUID válido")
    String clientId,

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Size(min = 3, max = 20, message = "El tipo de cuenta debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^(CORRIENTE|AHORROS|INVERSION)$", message = "Tipo de cuenta no válido")
    String accountType,

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.00", message = "El saldo inicial debe ser mayor o igual a 0")
    BigDecimal initialBalance,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres")
    @Pattern(regexp = "^[A-Z]{3}$", message = "La moneda debe ser un código ISO válido (ej: USD, EUR)")
    String currency,

    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres")
    String description
) {
    // Constructor canónico generado automáticamente por record
    // Getters, equals, hashCode y toString también generados automáticamente

    /**
     * Valida que el saldo inicial sea cero para cuentas de tipo INVERSION.
     * @return true si la validación pasa, false en caso contrario.
     */
    public boolean isValidInitialBalanceForAccountType() {
        if ("INVERSION".equals(accountType) && initialBalance.compareTo(BigDecimal.ZERO) != 0) {
            return false;
        }
        return true;
    }
}

// === ARCHIVO: src/main/java/com/bankapi/application/dto/AccountResponseDTO.java ===
package com.bankapi.application.dto;


import com.bankapi.domain.models.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponseDTO(
    UUID accountId,
    UUID clientId,
    String accountType,
    BigDecimal balance,
    String currency,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String description
) {
    // Constructor canónico generado automáticamente por record
    // Getters, equals, hashCode y toString también generados automáticamente

    /**
     * Crea un DTO de respuesta a partir de una entidad Account.
     * @param account La entidad Account de dominio.
     * @return Un AccountResponseDTO con los datos de la cuenta.
     */
    public static AccountResponseDTO fromAccount(com.bankapi.domain.models.Account account) {
        return new AccountResponseDTO(
            account.getAccountId(),
            account.getClientId(),
            account.getAccountType(),
            account.getBalance(),
            account.getCurrency(),
            account.getStatus(),
            account.getCreatedAt(),
            account.getUpdatedAt(),
            account.getDescription()
        );
    }
}

// === ARCHIVO: src/main/java/com/bankapi/domain/models/Account.java ===
package com.bankapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "account_type", "currency"})
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", updatable = false, nullable = false)
    private UUID accountId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AccountStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "description", length = 200)
    private String description;

    public enum AccountStatus {
        ACTIVE,
        BLOCKED,
        CLOSED
    }

    // Constructor sin argumentos requerido por JPA
    protected Account() {
    }

    /**
     * Constructor para crear una nueva cuenta.
     * @param clientId ID del cliente propietario de la cuenta.
     * @param accountType Tipo de cuenta (CORRIENTE, AHORROS, INVERSION).
     * @param initialBalance Saldo inicial de la cuenta.
     * @param currency Moneda de la cuenta.
     * @param description Descripción opcional de la cuenta.
     */
    public Account(UUID clientId, String accountType, BigDecimal initialBalance, String currency, String description) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.currency = currency;
        this.status = AccountStatus.ACTIVE;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters y setters
    public UUID getAccountId() {
        return accountId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Actualiza el saldo de la cuenta.
     * @param amount Monto a depositar (positivo) o retirar (negativo).
     * @throws IllegalArgumentException si el monto es negativo y supera el saldo actual.
     */
    public void updateBalance(BigDecimal amount) {
        BigDecimal newBalance = this.balance.add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la operación");
        }
        this.balance = newBalance;
    }

    /**
     * Bloquea la cuenta.
     */
    public void blockAccount() {
        this.status = AccountStatus.BLOCKED;
    }

    /**
     * Cierra la cuenta si el saldo es cero.
     * @throws IllegalStateException si el saldo no es cero.
     */
    public void closeAccount() {
        if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("No se puede cerrar una cuenta con saldo diferente de cero");
        }
        this.status = AccountStatus.CLOSED;
    }

    /**
     * Reactiva la cuenta si estaba bloqueada.
     * @throws IllegalStateException si la cuenta no está bloqueada.
     */
    public void activateAccount() {
        if (this.status != AccountStatus.BLOCKED) {
            throw new IllegalStateException("Solo se puede activar una cuenta bloqueada");
        }
        this.status = AccountStatus.ACTIVE;
    }
}

// === ARCHIVO: src/main/java/com/bankapi/domain/models/Client.java ===
package com.bankapi.domain.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Column(name = "identification_number", nullable = false, unique = true, length = 50)
    private String identificationNumber;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ClientStatus status;

    public enum ClientStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        PENDING_VERIFICATION
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = ClientStatus.PENDING_VERIFICATION;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        return status == ClientStatus.ACTIVE;
    }

    public boolean canOperate() {
        return status == ClientStatus.ACTIVE || status == ClientStatus.SUSPENDED;
    }

    public void activate() {
        this.status = ClientStatus.ACTIVE;
    }

    public void suspend() {
        this.status = ClientStatus.SUSPENDED;
    }

    public void deactivate() {
        this.status = ClientStatus.INACTIVE;
    }
}

// === ARCHIVO: src/main/java/com/bankapi/domain/repositories/AccountRepository.java ===
package com.bankapi.domain.repositories;


import com.bankapi.domain.models.AccountStatus;
import com.bankapi.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByClientId(UUID clientId);

    List<Account> findByAccountType(String accountType);

    List<Account> findByStatus(Account.AccountStatus status);

    @Query("SELECT a FROM Account a WHERE a.clientId = :clientId AND a.status = :status")
    List<Account> findByClientIdAndStatus(@Param("clientId") UUID clientId, @Param("status") Account.AccountStatus status);

    @Query("SELECT a FROM Account a WHERE a.accountType = :accountType AND a.status = :status")
    List<Account> findByAccountTypeAndStatus(@Param("accountType") String accountType, @Param("status") Account.AccountStatus status);

    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.clientId = :clientId")
    BigDecimal getTotalBalanceByClientId(@Param("clientId") UUID clientId);

    @Query("SELECT a FROM Account a WHERE a.createdAt BETWEEN :startDate AND :endDate")
    List<Account> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    boolean existsByClientIdAndAccountType(UUID clientId, String accountType);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.clientId = :clientId")
    int countByClientId(@Param("clientId") UUID clientId);
}

// === ARCHIVO: src/main/java/com/bankapi/application/services/AccountService.java ===
package com.bankapi.application.services;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    AccountResponseDTO createAccount(AccountRequestDTO requestDTO, String idempotencyKey);

    Optional<AccountResponseDTO> getAccountById(UUID accountId);

    List<AccountResponseDTO> getAccountsByClientId(UUID clientId);

    List<AccountResponseDTO> getAllAccounts();

    Optional<AccountResponseDTO> updateAccount(UUID accountId, AccountRequestDTO requestDTO);

    boolean deleteAccount(UUID accountId);

    Optional<AccountResponseDTO> activateAccount(UUID accountId);

    Optional<AccountResponseDTO> blockAccount(UUID accountId);

    Optional<AccountResponseDTO> closeAccount(UUID accountId);

    boolean existsByClientIdAndAccountType(UUID clientId, String accountType);

    int countAccountsByClientId(UUID clientId);
}

// === ARCHIVO: src/main/java/com/bankapi/application/services/AccountServiceImpl.java ===
package com.bankapi.application.services;


import com.bankapi.domain.models.AccountStatus;
import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private static final String IDEMPOTENCY_HEADER = "Idempotency-Key";

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request, String idempotencyKey) {
        logger.info("Creando cuenta con clave de idempotencia: {}", idempotencyKey);

        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            Optional<Account> existingAccount = accountRepository.findByIdempotencyKey(idempotencyKey);
            if (existingAccount.isPresent()) {
                logger.info("Cuenta encontrada para clave de idempotencia: {}", idempotencyKey);
                return AccountResponseDTO.fromAccount(existingAccount.get());
            }
        }

        if (!request.isValidInitialBalanceForAccountType()) {
            throw new IllegalArgumentException(
                "El saldo inicial no es válido para el tipo de cuenta especificado");
        }

        Account account = new Account(
            request.clientId(),
            request.accountType(),
            request.initialBalance(),
            request.currency(),
            request.description()
        );

        account.setIdempotencyKey(idempotencyKey);
        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.getAccountId());

        return AccountResponseDTO.fromAccount(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountResponseDTO> getAccountById(UUID accountId) {
        logger.debug("Consultando cuenta con ID: {}", accountId);
        return accountRepository.findById(accountId)
            .map(AccountResponseDTO::fromAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAccountsByClientId(UUID clientId) {
        logger.debug("Consultando cuentas para cliente: {}", clientId);
        return accountRepository.findByClientId(clientId)
            .stream()
            .map(AccountResponseDTO::fromAccount)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAllAccounts() {
        logger.debug("Consultando todas las cuentas");
        return accountRepository.findAll()
            .stream()
            .map(AccountResponseDTO::fromAccount)
            .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO request) {
        logger.info("Actualizando cuenta con ID: {}", accountId);

        Account existingAccount = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (request.accountType() != null && !request.accountType().isBlank()) {
            existingAccount.setAccountType(request.accountType());
        }

        if (request.currency() != null && !request.currency().isBlank()) {
            existingAccount.setCurrency(request.currency());
        }

        if (request.description() != null) {
            existingAccount.setDescription(request.description());
        }

        existingAccount.onUpdate();
        Account updatedAccount = accountRepository.save(existingAccount);
        logger.info("Cuenta actualizada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} en cuenta: {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (account.getStatus() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException(
                "No se puede depositar en una cuenta que no está activa");
        }

        account.updateBalance(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.getBalance());

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta: {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (account.getStatus() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException(
                "No se puede retirar de una cuenta que no está activa");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException(
                "Saldo insuficiente para realizar el retiro");
        }

        account.updateBalance(amount.negate());
        Account updatedAccount = accountRepository.save(account);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.getBalance());

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO blockAccount(UUID accountId) {
        logger.info("Bloqueando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        account.blockAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta bloqueada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException(
                "No se puede cerrar una cuenta con saldo diferente a cero");
        }

        account.closeAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta cerrada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO activateAccount(UUID accountId) {
        logger.info("Activando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        account.activateAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta activada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }
}

// === ARCHIVO: src/main/java/com/bankapi/infrastructure/controllers/AccountController.java ===
package com.bankapi.infrastructure.controllers;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.application.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Gestión de Cuentas", description = "API para la gestión de cuentas bancarias")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cuenta", 
               description = "Crea una nueva cuenta bancaria. Soporta idempotencia mediante el header Idempotency-Key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Conflicto - cuenta ya existe con la misma clave de idempotencia"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> createAccount(
            @Parameter(description = "Datos para crear la cuenta") @RequestBody AccountRequestDTO request,
            @Parameter(description = "Clave de idempotencia para evitar duplicados") 
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        logger.info("Solicitud de creación de cuenta recibida");
        AccountResponseDTO createdAccount = accountService.createAccount(request, idempotencyKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Obtener cuenta por ID", 
               description = "Recupera los detalles de una cuenta bancaria específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta encontrada",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> getAccountById(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de consulta de cuenta: {}", accountId);
        return accountService.getAccountById(accountId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Obtener cuentas por cliente", 
               description = "Recupera todas las cuentas asociadas a un cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas encontradas",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByClientId(
            @Parameter(description = "ID del cliente") @PathVariable UUID clientId) {
        logger.info("Solicitud de consulta de cuentas para cliente: {}", clientId);
        List<AccountResponseDTO> accounts = accountService.getAccountsByClientId(clientId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las cuentas", 
               description = "Recupera todas las cuentas bancarias del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas encontradas",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        logger.info("Solicitud de consulta de todas las cuentas");
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{accountId}")
    @Operation(summary = "Actualizar cuenta", 
               description = "Actualiza la información de una cuenta bancaria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId,
            @Parameter(description = "Datos actualizados de la cuenta") @RequestBody AccountRequestDTO request) {
        logger.info("Solicitud de actualización de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.updateAccount(accountId, request);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/deposit")
    @Operation(summary = "Depositar fondos", 
               description = "Realiza un depósito en una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Depósito exitoso",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Monto inválido"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "409", description = "Cuenta no está activa"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> deposit(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId,
            @Parameter(description = "Monto a depositar") @RequestParam BigDecimal amount) {
        logger.info("Solicitud de depósito de {} en cuenta: {}", amount, accountId);
        AccountResponseDTO updatedAccount = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/withdraw")
    @Operation(summary = "Retirar fondos", 
               description = "Realiza un retiro de una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retiro exitoso",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Monto inválido"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "409", description = "Saldo insuficiente o cuenta no activa"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> withdraw(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId,
            @Parameter(description = "Monto a retirar") @RequestParam BigDecimal amount) {
        logger.info("Solicitud de retiro de {} de cuenta: {}", amount, accountId);
        AccountResponseDTO updatedAccount = accountService.withdraw(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/block")
    @Operation(summary = "Bloquear cuenta", 
               description = "Bloquea una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta bloqueada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> blockAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de bloqueo de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.blockAccount(accountId);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/close")
    @Operation(summary = "Cerrar cuenta", 
               description = "Cierra una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta cerrada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "La cuenta tiene saldo pendiente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> closeAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de cierre de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.closeAccount(accountId);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/activate")
    @Operation(summary = "Activar cuenta", 
               description = "Activa una cuenta bancaria previamente bloqueada o cerrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta activada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> activateAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de activación de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.activateAccount(accountId);
        return ResponseEntity.ok(updatedAccount);
    }
}

// === ARCHIVO: src/main/java/com/bankapi/infrastructure/persistence/AccountRepositoryImpl.java ===
package com.bankapi.infrastructure.persistence;


import com.bankapi.domain.models.AccountStatus;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.repositories.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        if (account.getAccountId() == null) {
            entityManager.persist(account);
            logger.debug("Cuenta persistida con ID: {}", account.getAccountId());
            return account;
        } else {
            Account merged = entityManager.merge(account);
            logger.debug("Cuenta actualizada con ID: {}", merged.getAccountId());
            return merged;
        }
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        logger.debug("Buscando cuenta por ID: {}", accountId);
        Account account = entityManager.find(Account.class, accountId);
        return Optional.ofNullable(account);
    }

    @Override
    public List<Account> findAll() {
        logger.debug("Consultando todas las cuentas");
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }

    @Override
    public List<Account> findByClientId(UUID clientId) {
        logger.debug("Buscando cuentas para cliente: {}", clientId);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.clientId = :clientId", Account.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class);
        query.setParameter("accountNumber", accountNumber);
        List<Account> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Account> findByStatus(Account.AccountStatus status) {
        logger.debug("Buscando cuentas con estado: {}", status);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.status = :status", Account.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<Account> findByCurrency(String currency) {
        logger.debug("Buscando cuentas en moneda: {}", currency);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.currency = :currency", Account.class);
        query.setParameter("currency", currency);
        return query.getResultList();
    }

    @Override
    public Optional<Account> findByIdempotencyKey(String idempotencyKey) {
        logger.debug("Buscando cuenta por clave de idempotencia: {}", idempotencyKey);
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.idempotencyKey = :idempotencyKey", Account.class);
        query.setParameter("idempotencyKey", idempotencyKey);
        List<Account> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public void delete(Account account) {
        logger.debug("Eliminando cuenta: {}", account.getAccountId());
        entityManager.remove(account);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber", Long.class);
        query.setParameter("accountNumber", accountNumber);
        return query.getSingleResult() > 0;
    }

    @Override
    public List<Account> findAccountsWithBalanceGreaterThan(BigDecimal amount) {
        logger.debug("Buscando cuentas con saldo mayor a: {}", amount);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.balance > :amount", Account.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public List<Account> findAccountsWithBalanceLessThan(BigDecimal amount) {
        logger.debug("Buscando cuentas con saldo menor a: {}", amount);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.balance < :amount", Account.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public List<Account> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Buscando cuentas creadas entre {} y {}", startDate, endDate);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.createdAt BETWEEN :startDate AND :endDate", Account.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}

// === ARCHIVO: src/main/java/com/bankapi/infrastructure/config/OpenApiConfig.java ===
package com.bankapi.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";
    private static final String API_TITLE = "Bank API - Gestión de Cuentas Bancarias";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = """
            API REST para la gestión de cuentas bancarias en un entorno de banca y fintech.
            
            Esta API permite realizar las siguientes operaciones:
            - Creación de nuevas cuentas bancarias
            - Consulta de información de cuentas
            - Actualización de estado y saldo de cuentas
            - Consulta de clientes asociados
            
            La API implementa idempotencia en la creación de cuentas mediante headers X-Idempotency-Key.
            """;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@bankapi.com")
                                .url("https://www.bankapi.com/soporte"))
                        .license(new License()
                                .name("Licencia Proprietaria")
                                .url("https://www.bankapi.com/licencia")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("""
                                        Token JWT obtenido mediante el endpoint de autenticación.
                                        El token debe ser enviado en el header Authorization con el formato:
                                        Bearer <token_jwt>
                                        """)));
    }
}

// === ARCHIVO: src/main/java/com/bankapi/infrastructure/security/SecurityConfig.java ===
package com.bankapi.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/actuator/health").permitAll()
                .anyRequest().authenticated()
            );
        // TODO: Implementar filtro JWT para validar tokens en cada request
        // TODO: Configurar proveedor de autenticación con usuarios de la base de datos
        // TODO: Implementar manejo de autorización por roles (ROLE_ADMIN, ROLE_USER)
        return http.build();
    }
}

// === ARCHIVO: src/main/java/com/bankapi/infrastructure/security/JwtAuthenticationFilter.java ===
package com.bankapi.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, 
                                    UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        // TODO: Extraer token del header Authorization
        // TODO: Validar token usando jwtTokenProvider
        // TODO: Cargar usuario desde UserDetailsService
        // TODO: Establecer autenticación en SecurityContextHolder
        // TODO: Continuar con el filterChain
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

// === ARCHIVO: src/main/java/com/bankapi/infrastructure/security/JwtTokenProvider.java ===
package com.bankapi.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

// === ARCHIVO: src/test/java/com/bankapi/application/services/AccountServiceTest.java ===
package com.bankapi.application.services;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.models.Account.AccountStatus;
import com.bankapi.domain.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountService - Pruebas Unitarias")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account testAccount;
    private AccountRequestDTO validRequest;
    private UUID testClientId;

    @BeforeEach
    void setUp() {
        testClientId = UUID.randomUUID();
        testAccount = new Account(
            testClientId,
            "SAVINGS",
            new BigDecimal("1000.00"),
            "USD",
            "Cuenta de ahorro personal"
        );
        validRequest = new AccountRequestDTO("SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta de ahorro personal");
    }

    @Nested
    @DisplayName("Creación de Cuentas")
    class CreateAccountTests {

        @Test
        @DisplayName("Crear cuenta exitosamente con datos válidos")
        void createAccount_WithValidData_ReturnsAccountResponse() {
            when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

            AccountResponseDTO result = accountService.createAccount(testClientId, validRequest);

            assertNotNull(result);
            assertEquals(testAccount.getAccountId(), result.accountId());
            assertEquals(testClientId, result.clientId());
            assertEquals("SAVINGS", result.accountType());
            assertEquals(new BigDecimal("1000.00"), result.balance());
            assertEquals("USD", result.currency());
            verify(accountRepository, times(1)).save(any(Account.class));
        }

        @Test
        @DisplayName("Crear cuenta falla con saldo inicial negativo")
        void createAccount_WithNegativeBalance_ThrowsException() {
            AccountRequestDTO invalidRequest = new AccountRequestDTO(
                "SAVINGS",
                new BigDecimal("-100.00"),
                "USD",
                "Cuenta inválida"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, invalidRequest)
            );
            verify(accountRepository, never()).save(any(Account.class));
        }

        @Test
        @DisplayName("Crear cuenta de tipo CHECKING con saldo cero es válido")
        void createAccount_CheckingWithZeroBalance_IsValid() {
            AccountRequestDTO checkingRequest = new AccountRequestDTO(
                "CHECKING",
                BigDecimal.ZERO,
                "USD",
                "Cuenta corriente"
            );
            Account checkingAccount = new Account(testClientId, "CHECKING", BigDecimal.ZERO, "USD", "Cuenta corriente");
            when(accountRepository.save(any(Account.class))).thenReturn(checkingAccount);

            AccountResponseDTO result = accountService.createAccount(testClientId, checkingRequest);

            assertNotNull(result);
            assertEquals("CHECKING", result.accountType());
            assertEquals(BigDecimal.ZERO, result.balance());
        }

        @Test
        @DisplayName("Crear cuenta falla con tipo de cuenta inválido")
        void createAccount_WithInvalidAccountType_ThrowsException() {
            AccountRequestDTO invalidTypeRequest = new AccountRequestDTO(
                "INVALID_TYPE",
                new BigDecimal("500.00"),
                "USD",
                "Tipo inválido"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, invalidTypeRequest)
            );
        }

        @Test
        @DisplayName("Crear cuenta falla si el cliente no existe")
        void createAccount_WithNonExistentClient_ThrowsException() {
            UUID nonExistentClientId = UUID.randomUUID();
            when(accountRepository.existsByClientId(nonExistentClientId)).thenReturn(false);
            when(accountRepository.save(any(Account.class))).thenThrow(new IllegalStateException("Cliente no encontrado"));

            assertThrows(IllegalStateException.class, () ->
                accountService.createAccount(nonExistentClientId, validRequest)
            );
        }
    }

    @Nested
    @DisplayName("Consulta de Cuentas")
    class GetAccountTests {

        @Test
        @DisplayName("Obtener cuenta por ID exitosamente")
        void getAccountById_WhenExists_ReturnsAccount() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

            Optional<Account> result = accountService.getAccountById(accountId);

            assertTrue(result.isPresent());
            assertEquals(accountId, result.get().getAccountId());
            verify(accountRepository, times(1)).findById(accountId);
        }

        @Test
        @DisplayName("Obtener cuenta por ID cuando no existe retorna vacío")
        void getAccountById_WhenNotExists_ReturnsEmpty() {
            UUID nonExistentId = UUID.randomUUID();
            when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            Optional<Account> result = accountService.getAccountById(nonExistentId);

            assertTrue(result.isEmpty());
            verify(accountRepository, times(1)).findById(nonExistentId);
        }

        @Test
        @DisplayName("Obtener cuentas por cliente exitosamente")
        void getAccountsByClientId_ReturnsAccountList() {
            when(accountRepository.findByClientId(testClientId)).thenReturn(java.util.List.of(testAccount));

            var result = accountService.getAccountsByClientId(testClientId);

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            assertEquals(testClientId, result.get(0).getClientId());
        }

        @Test
        @DisplayName("Obtener cuentas por cliente sin cuentas retorna lista vacía")
        void getAccountsByClientId_WhenNoAccounts_ReturnsEmptyList() {
            when(accountRepository.findByClientId(testClientId)).thenReturn(java.util.List.of());

            var result = accountService.getAccountsByClientId(testClientId);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Actualización de Cuentas")
    class UpdateAccountTests {

        @Test
        @DisplayName("Actualizar balance de cuenta exitosamente")
        void updateAccountBalance_WithValidAmount_UpdatesBalance() {
            UUID accountId = testAccount.getAccountId();
            BigDecimal newBalance = new BigDecimal("500.00");
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.updateAccountBalance(accountId, newBalance);

            assertNotNull(result);
            assertEquals(newBalance, result.getBalance());
            verify(accountRepository, times(1)).save(testAccount);
        }

        @Test
        @DisplayName("Actualizar cuenta que no existe lanza excepción")
        void updateAccountBalance_WhenNotExists_ThrowsException() {
            UUID nonExistentId = UUID.randomUUID();
            when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () ->
                accountService.updateAccountBalance(nonExistentId, new BigDecimal("100.00"))
            );
        }

        @Test
        @DisplayName("Bloquear cuenta exitosamente")
        void blockAccount_WhenActive_ChangesStatusToBlocked() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.blockAccount(accountId);

            assertEquals(AccountStatus.BLOCKED, result.getStatus());
            verify(accountRepository, times(1)).save(testAccount);
        }

        @Test
        @DisplayName("Cerrar cuenta exitosamente")
        void closeAccount_WhenActive_ChangesStatusToClosed() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.closeAccount(accountId);

            assertEquals(AccountStatus.CLOSED, result.getStatus());
        }

        @Test
        @DisplayName("Activar cuenta bloqueada exitosamente")
        void activateAccount_WhenBlocked_ChangesStatusToActive() {
            testAccount.blockAccount();
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.activateAccount(accountId);

            assertEquals(AccountStatus.ACTIVE, result.getStatus());
        }
    }

    @Nested
    @DisplayName("Excepciones y Casos Edge")
    class ExceptionHandlingTests {

        @Test
        @DisplayName("Crear cuenta con currency inválido lanza excepción")
        void createAccount_WithInvalidCurrency_ThrowsException() {
            AccountRequestDTO invalidCurrencyRequest = new AccountRequestDTO(
                "SAVINGS",
                new BigDecimal("100.00"),
                "INVALID",
                "Currency inválida"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, invalidCurrencyRequest)
            );
        }

        @Test
        @DisplayName("Crear cuenta con saldo muy grande lanza excepción")
        void createAccount_WithExcessiveBalance_ThrowsException() {
            AccountRequestDTO excessiveRequest = new AccountRequestDTO(
                "SAVINGS",
                new BigDecimal("100000000.00"),
                "USD",
                "Monto excesivo"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, excessiveRequest)
            );
        }

        @Test
        @DisplayName("Actualizar balance con monto negativo lanza excepción")
        void updateAccountBalance_WithNegativeAmount_ThrowsException() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

            assertThrows(IllegalArgumentException.class, () ->
                accountService.updateAccountBalance(accountId, new BigDecimal("-50.00"))
            );
        }

        @Test
        @DisplayName("Cerrar cuenta que ya está cerrada lanza excepción")
        void closeAccount_WhenAlreadyClosed_ThrowsException() {
            testAccount.closeAccount();
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

            assertThrows(IllegalStateException.class, () ->
                accountService.closeAccount(accountId)
            );
        }
    }
}

// === ARCHIVO: src/test/java/com/bankapi/infrastructure/controllers/AccountControllerTest.java ===
package com.bankapi.infrastructure.controllers;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.application.services.AccountService;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.models.Account.AccountStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@DisplayName("AccountController - Pruebas de Integración")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private UUID testClientId;
    private UUID testAccountId;
    private Account testAccount;
    private AccountRequestDTO validRequest;
    private AccountResponseDTO validResponse;

    @BeforeEach
    void setUp() {
        testClientId = UUID.randomUUID();
        testAccountId = UUID.randomUUID();
        testAccount = new Account(testClientId, "SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta de prueba");
        validRequest = new AccountRequestDTO("SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta de prueba");
        validResponse = AccountResponseDTO.fromAccount(testAccount);
    }

    @Nested
    @DisplayName("POST /api/accounts - Crear Cuenta")
    class CreateAccountTests {

        @Test
        @DisplayName("Crear cuenta retorna 201 Created con datos correctos")
        @WithMockUser(roles = "USER")
        void createAccount_Returns201Created() throws Exception {
            when(accountService.createAccount(eq(testClientId), any(AccountRequestDTO.class)))
                .thenReturn(validResponse);

            mockMvc.perform(post("/api/accounts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.accountId").value(testAccount.getAccountId().toString()))
                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
                .andExpect(jsonPath("$.balance").value(1000.00));
        }

        @Test
        @DisplayName("Crear cuenta sin autenticación retorna 401 Unauthorized")
        void createAccount_WithoutAuth_Returns401() throws Exception {
            mockMvc.perform(post("/api/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Crear cuenta con datos inválidos retorna 400 Bad Request")
        @WithMockUser(roles = "USER")
        void createAccount_WithInvalidData_Returns400() throws Exception {
            AccountRequestDTO invalidRequest = new AccountRequestDTO(
                "INVALID_TYPE",
                new BigDecimal("-100.00"),
                "USD",
                "Datos inválidos"
            );

            mockMvc.perform(post("/api/accounts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Crear cuenta conCSRF inválido retorna 403 Forbidden")
        @WithMockUser(roles = "USER")
        void createAccount_WithInvalidCsrf_Returns403() throws Exception {
            mockMvc.perform(post("/api/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("GET /api/accounts/{id} - Obtener Cuenta por ID")
    class GetAccountByIdTests {

        @Test
        @DisplayName("Obtener cuenta existente retorna 200 OK")
        @WithMockUser(roles = "USER")
        void getAccountById_WhenExists_Returns200() throws Exception {
            when(accountService.getAccountById(testAccountId))
                .thenReturn(Optional.of(testAccount));

            mockMvc.perform(get("/api/accounts/{id}", testAccountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(testAccountId.toString()))
                .andExpect(jsonPath("$.clientId").value(testClientId.toString()))
                .andExpect(jsonPath("$.accountType").value("SAVINGS"));
        }

        @Test
        @DisplayName("Obtener cuenta inexistente retorna 404 Not Found")
        @WithMockUser(roles = "USER")
        void getAccountById_WhenNotExists_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId))
                .thenReturn(Optional.empty());

            mockMvc.perform(get("/api/accounts/{id}", nonExistentId))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Obtener cuenta sin autenticación retorna 401 Unauthorized")
        void getAccountById_WithoutAuth_Returns401() throws Exception {
            mockMvc.perform(get("/api/accounts/{id}", testAccountId))
                .andExpect(status().isUnauthorized());
        }
    }

    @Nested
n    @DisplayName("GET /api/accounts/client/{clientId} - Obtener Cuentas por Cliente")
    class GetAccountsByClientTests {

        @Test
        @DisplayName("Obtener cuentas de cliente retorna 200 OK con lista")
        @WithMockUser(roles = "USER")
        void getAccountsByClientId_Returns200WithList() throws Exception {
            when(accountService.getAccountsByClientId(testClientId))
                .thenReturn(List.of(testAccount));

            mockMvc.perform(get("/api/accounts/client/{clientId}", testClientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].clientId").value(testClientId.toString()));
        }

        @Test
        @DisplayName("Obtener cuentas de cliente sin cuentas retorna 200 OK con lista vacía")
        @WithMockUser(roles = "USER")
        void getAccountsByClientId_WhenNoAccounts_ReturnsEmptyList() throws Exception {
            when(accountService.getAccountsByClientId(testClientId))
                .thenReturn(List.of());

            mockMvc.perform(get("/api/accounts/client/{clientId}", testClientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        }
    }

    @Nested
    @DisplayName("PUT /api/accounts/{id}/block - Bloquear Cuenta")
    class BlockAccountTests {

        @Test
        @DisplayName("Bloquear cuenta retorna 200 OK")
        @WithMockUser(roles = "ADMIN")
        void blockAccount_Returns200() throws Exception {
            Account blockedAccount = new Account(testClientId, "SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta bloqueada");
            blockedAccount.blockAccount();
            when(accountService.blockAccount(testAccountId))
                .thenReturn(blockedAccount);

            mockMvc.perform(put("/api/accounts/{id}/block", testAccountId)
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("BLOCKED"));
        }

        @Test
        @DisplayName("Bloquear cuenta sin rol admin retorna 403 Forbidden")
        @WithMockUser(roles = "USER")
        void blockAccount_WithoutAdminRole_Returns403() throws Exception {
            mockMvc.perform(put("/api/accounts/{id}/block", testAccountId)
                    .with(csrf()))
                .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("PUT /api/accounts/{id}/close - Cerrar Cuenta")
    class CloseAccountTests {

        @Test
        @DisplayName("Cerrar cuenta retorna 200 OK")
        @WithMockUser(roles = "ADMIN")
        void closeAccount_Returns200() throws Exception {
            Account closedAccount = new Account(testClientId, "SAVINGS", BigDecimal.ZERO, "USD", "Cuenta cerrada");
            closedAccount.closeAccount();
            when(accountService.closeAccount(testAccountId))
                .thenReturn(closedAccount);

            mockMvc.perform(put("/api/accounts/{id}/close", testAccountId)
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CLOSED"));
        }
    }

    @Nested
    @DisplayName("PUT /api/accounts/{id}/activate - Activar Cuenta")
    class ActivateAccountTests {

        @Test
        @DisplayName("Activar cuenta retorna 200 OK")
        @WithMockUser(roles = "ADMIN")
        void activateAccount_Returns200() throws Exception {
            Account activeAccount = new Account(testClientId, "SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta activa");
            when(accountService.activateAccount(testAccountId))
                .thenReturn(activeAccount);

            mockMvc.perform(put("/api/accounts/{id}/activate", testAccountId)
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
        }
    }

    @Nested
    @DisplayName("Manejo de Errores Global")
    class GlobalErrorHandlingTests {

        @Test
        @DisplayName("Error de validación retorna 400 Bad Request con detalles")
        @WithMockUser(roles = "USER")
        void validationError_Returns400WithDetails() throws Exception {
            AccountRequestDTO invalidRequest = new AccountRequestDTO(
                "SAVINGS",
                null,
                "USD",
                "Falta balance"
            );

            mockMvc.perform(post("/api/accounts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Path variable inválida retorna 400 Bad Request")
        @WithMockUser(roles = "USER")
        void invalidPathVariable_Returns400() throws Exception {
            mockMvc.perform(get("/api/accounts/invalid-uuid"))
                .andExpect(status().isBadRequest());
        }
    }
}

// === ARCHIVO: README.md ===
# Bank API - Gestión de Cuentas Bancarias

API REST para gestión de cuentas bancarias en entorno de banca y fintech.

## Requisitos Previos

- Java 21
- Maven 3.9+
- Docker (opcional, para ejecutar con Docker)

## Configuración del Entorno

### Instalación de Java 21

```bash
# Verificar instalación
java -version

# Si no está instalado, usar SDKMAN
curl -s "https://get.sdkman.io" | bash
source ~/.sdkman/bin/sdkman-init.sh
sdk install java 21.0.2-tem
```

### Instalación de Maven

```bash
# Verificar instalación
mvn -version

# Si no está instalado
brew install maven  # macOS
apt-get install maven  # Ubuntu/Debian
```

## Ejecución del Proyecto

### Compilación

```bash
mvn clean compile
```

### Ejecución Local

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

### Ejecución de Tests

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar solo tests unitarios
mvn test -Dtest=*ServiceTest

# Ejecutar solo tests de integración
mvn test -Dtest=*ControllerTest
```

### Construcción del JAR

```bash
mvn package -DskipTests
java -jar target/bank-api-0.0.1-SNAPSHOT.jar
```

## Documentación API

Swagger UI disponible en: `http://localhost:8080/swagger-ui/index.html`

OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints Principales

| Método | Endpoint | Descripción | Rol Requerido |
|--------|----------|-------------|---------------|
| POST | `/api/accounts` | Crear cuenta bancaria | USER |
| GET | `/api/accounts/{id}` | Obtener cuenta por ID | USER |
| GET | `/api/accounts/client/{clientId}` | Obtener cuentas de cliente | USER |
| PUT | `/api/accounts/{id}/block` | Bloquear cuenta | ADMIN |
| PUT | `/api/accounts/{id}/close` | Cerrar cuenta | ADMIN |
| PUT | `/api/accounts/{id}/activate` | Activar cuenta | ADMIN |

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/bankapi/
│   │   ├── BankApiApplication.java
│   │   ├── application/
│   │   │   ├── dto/
│   │   │   └── services/
│   │   ├── domain/
│   │   │   ├── models/
│   │   │   └── repositories/
│   │   └── infrastructure/
│   │       ├── config/
│   │       ├── controllers/
│   │       ├── persistence/
│   │       └── security/
│   └── resources/
│       └── application.yml
└── test/
    └── java/com/bankapi/
        ├── application/services/
        └── infrastructure/controllers/
```

## Configuración

La configuración se encuentra en `src/main/resources/application.yml`:

- Puerto: 8080
- Base de datos: H2 en memoria (desarrollo)
- Swagger: habilitado
- Seguridad: JWT con autenticación

## Roles de Usuario

- **USER**: Puede crear y consultar cuentas
- **ADMIN**: Todas las operaciones incluyendo bloqueo y cierre

## Monitoreo y Salud

- Endpoint de salud: `http://localhost:8080/actuator/health`
```
