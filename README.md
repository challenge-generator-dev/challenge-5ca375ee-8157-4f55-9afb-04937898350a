# Implementación de una API REST para gestión de cuentas bancarias

El sistema debe permitir la gestión de cuentas bancarias en un entorno de banca y fintech. Los actores involucrados son el cliente, el sistema de autenticación, el core bancario y el sistema de auditoría. Las operaciones clave son la creación, consulta y actualización de cuentas. La API debe ser idempotente en la creación de cuentas y tolerante a fallos en la consulta de cuentas. Se espera un throughput de 1 500 solicitudes/segundo en hora pico y una latencia máxima de 200ms para la consulta de cuentas.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Creación de una API REST en un entorno de banca y fintech |
| **Nivel** | junior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición y modelado de la API

**Objetivo:** Definir y modelar la API REST para la gestión de cuentas bancarias.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las operaciones CRUD necesarias para la gestión de cuentas.
- Modelar las entidades involucradas (cuenta, cliente, transacción) y sus relaciones.
- Definir los endpoints de la API y sus métodos HTTP correspondientes.

**Entregable:** Documentación de la API que incluye los endpoints, métodos HTTP, y descripciones de las operaciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los atributos necesarios para cada entidad y sus relaciones.
- Piensa en cómo asegurar la idempotencia en la creación de cuentas.

</details>

### Fase 2: Implementación de la lógica de negocio

**Objetivo:** Implementar la lógica de negocio para las operaciones de la API.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar la lógica para la creación, consulta y actualización de cuentas.
- Asegurar la idempotencia en la creación de cuentas utilizando una clave única.
- Manejar errores y excepciones de manera adecuada.

**Entregable:** Código fuente que implementa la lógica de negocio para las operaciones de la API.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo manejar los casos de error y excepción en cada operación.
- Piensa en cómo asegurar que la creación de cuentas sea idempotente.

</details>

### Fase 3: Integración y pruebas

**Objetivo:** Integrar la API con el sistema de autenticación y realizar pruebas.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Integrar la API con el sistema de autenticación para asegurar que solo los usuarios autorizados puedan realizar operaciones.
- Realizar pruebas unitarias y de integración para asegurar la funcionalidad y la idempotencia de la API.
- Documentar los casos de prueba y los resultados obtenidos.

**Entregable:** Código fuente integrado con el sistema de autenticación y documentación de las pruebas realizadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo asegurar que solo los usuarios autorizados puedan realizar operaciones.
- Piensa en cómo documentar los casos de prueba y los resultados obtenidos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una API REST y cuáles son sus componentes principales?
- **paraQueSirve**: ¿Para qué sirve la idempotencia en la creación de cuentas y cómo se implementa?
- **comoSeUsa**: ¿Cómo se utiliza la documentación OpenAPI para describir una API REST?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar una API REST y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la integración de la API con el sistema de autenticación?

## Criterios de Evaluacion

- Definición y modelado de la API REST para la gestión de cuentas bancarias.
- Implementación de la lógica de negocio para las operaciones de la API.
- Integración de la API con el sistema de autenticación y realización de pruebas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
