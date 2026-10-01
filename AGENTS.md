# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de una API REST para gestión de cuentas bancarias**.

| | |
|---|---|
| Tema | Creación de una API REST en un entorno de banca y fintech |
| Nivel | junior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.3 |
| Patron arquitectonico | capas estándar con separación de dominio, aplicación e infraestructura |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.3.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.3.0
- org.springframework.boot:spring-boot-starter-security 3.3.0
- org.springframework.boot:spring-boot-starter-validation 3.3.0
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0
- io.jsonwebtoken:jjwt-api 0.12.3
- io.jsonwebtoken:jjwt-impl 0.12.3
- io.jsonwebtoken:jjwt-jackson 0.12.3
- com.h2database:h2 2.2.224
- org.projectlombok:lombok 1.18.30
- org.springframework.boot:spring-boot-starter-test 3.3.0
- org.springframework.security:spring-security-test 6.3.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición y modelado de la API**: Documentación de la API que incluye los endpoints, métodos HTTP, y descripciones de las operaciones.
- **Fase 2 — Implementación de la lógica de negocio**: Código fuente que implementa la lógica de negocio para las operaciones de la API.
- **Fase 3 — Integración y pruebas**: Código fuente integrado con el sistema de autenticación y documentación de las pruebas realizadas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/bankapi/infrastructure/security/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/bankapi/infrastructure/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (33)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bankapi/BankApiApplication.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.OpenAPIDefinition pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bankapi/infrastructure/persistence/AccountRepositoryImpl.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bankapi/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.findByIdempotencyKey`
      Se invoca `findByIdempotencyKey` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `Account.isPresent`
      Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `Account.get`
      Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.clientId`
      Se invoca `clientId` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.accountType`
      Se invoca `accountType` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.initialBalance`
      Se invoca `initialBalance` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.currency`
      Se invoca `currency` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRequestDTO.description`
      Se invoca `description` sobre `AccountRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `Account.setIdempotencyKey`
      Se invoca `setIdempotencyKey` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.save`
      Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.findById`
      Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/application/services/AccountServiceImpl.java` — `AccountRepository.findAll`
      Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `AccountService.deposit`
      Se invoca `deposit` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java` — `AccountService.withdraw`
      Se invoca `withdraw` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountRepository.save`
      Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.accountId`
      Se invoca `accountId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.clientId`
      Se invoca `clientId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.accountType`
      Se invoca `accountType` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.balance`
      Se invoca `balance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.currency`
      Se invoca `currency` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountRepository.existsByClientId`
      Se invoca `existsByClientId` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountRepository.findById`
      Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.isPresent`
      Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.get`
      Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.isEmpty`
      Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `Account.size`
      Se invoca `size` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bankapi/application/services/AccountServiceTest.java` — `AccountServiceImpl.updateAccountBalance`
      Se invoca `updateAccountBalance` sobre `AccountServiceImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/bankapi/BankApiApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bankapi/application/dto/AccountRequestDTO.java`
- `src/main/java/com/bankapi/application/dto/AccountResponseDTO.java`
- `src/main/java/com/bankapi/domain/models/Account.java`
- `src/main/java/com/bankapi/domain/models/Client.java`
- `src/main/java/com/bankapi/domain/repositories/AccountRepository.java`
- `src/main/java/com/bankapi/application/services/AccountService.java`
- `src/main/java/com/bankapi/application/services/AccountServiceImpl.java`
- `src/main/java/com/bankapi/infrastructure/controllers/AccountController.java`
- `src/main/java/com/bankapi/infrastructure/persistence/AccountRepositoryImpl.java`
- `src/main/java/com/bankapi/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/bankapi/infrastructure/security/SecurityConfig.java`
- `src/main/java/com/bankapi/infrastructure/security/JwtAuthenticationFilter.java`
- `src/main/java/com/bankapi/infrastructure/security/JwtTokenProvider.java`
- `src/test/java/com/bankapi/application/services/AccountServiceTest.java`
- `src/test/java/com/bankapi/infrastructure/controllers/AccountControllerTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bankapi`
- `src/main/java/com/bankapi/application`
- `src/main/java/com/bankapi/application/dto`
- `src/main/java/com/bankapi/application/services`
- `src/main/java/com/bankapi/domain`
- `src/main/java/com/bankapi/domain/models`
- `src/main/java/com/bankapi/domain/repositories`
- `src/main/java/com/bankapi/infrastructure`
- `src/main/java/com/bankapi/infrastructure/controllers`
- `src/main/java/com/bankapi/infrastructure/persistence`
- `src/main/java/com/bankapi/infrastructure/config`
- `src/main/java/com/bankapi/infrastructure/security`
- `src/test/java/com/bankapi`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar con separación de dominio, aplicación e infraestructura**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Crear una API REST con Spring Boot, JPA y documentación OpenAPI

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
