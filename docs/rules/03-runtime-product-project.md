# 37. DEUDA TÉCNICA

No esconder deuda técnica.

Si se acepta una solución temporal:

- marcarla;
- explicar por qué;
- indicar riesgo;
- definir cuándo debe retirarse.

No permitir que soluciones temporales se conviertan silenciosamente en arquitectura permanente.

---

# 38. OBSERVABILIDAD

Los errores deben ser diagnosticables sin revelar información sensible.

Logs y eventos deben contener:

- contexto técnico suficiente;
- códigos o categorías;
- timestamps cuando corresponda;
- identificadores técnicos seguros.

Evitar contenido privado de usuario.

---

# 39. DESEMPEÑO

No optimizar prematuramente.

Primero medir o demostrar el problema.

Después optimizar la causa real.

No sacrificar claridad o seguridad por microoptimizaciones sin evidencia.

---

# 40. FALLBACKS

Cuando exista fallback:

- definir cuándo entra;
- definir cuándo sale;
- evitar loops;
- evitar duplicaciones;
- evitar fingir éxito;
- registrar claramente qué ruta terminó realmente la operación.

Nunca reportar éxito si solo se inició un intento.

---

# 41. OPERACIONES ASÍNCRONAS

Distinguir claramente:

- solicitado;
- aceptado;
- iniciado;
- procesando;
- guardado;
- completado;
- fallido;
- cancelado;
- expirado.

No confundir aceptación con finalización.

No mostrar éxito al usuario hasta que el contrato real de la operación se haya completado.

---

# 42. IDEMPOTENCIA

Cuando una operación pueda repetirse debido a:

- retries;
- reconexiones;
- refresh;
- eventos duplicados;
- concurrencia;

diseñarla para evitar:

- duplicados;
- doble cobro;
- doble escritura;
- doble eliminación;
- estados inconsistentes.

---

# 43. EXPIRACIÓN Y TIMEOUTS

Toda operación temporal debe considerar:

- duración máxima;
- expiración;
- cleanup;
- comportamiento después de reconexión;
- qué ocurre si nunca llega respuesta.

No dejar operaciones pendientes indefinidamente.

---

# 44. CANCELACIÓN

Las operaciones largas deben poder manejar cancelación cuando tenga sentido.

Cancelar debe:

- detener trabajo futuro;
- limpiar estado temporal;
- no fingir éxito;
- mantener consistencia.

---

# 45. UX Y PRODUCTO

No modificar experiencia de usuario accidentalmente durante cambios internos.

Una migración técnica debería conservar comportamiento salvo que el cambio de producto sea intencional.

Si una modificación técnica cambia UX, señalarlo explícitamente.

---

# 46. ACCESIBILIDAD

Cuando se modifique interfaz considerar:

- teclado;
- foco;
- etiquetas;
- estados disabled;
- feedback;
- contraste;
- lectores de pantalla;
- mensajes comprensibles.

---

# 47. CONFIGURACIÓN Y ENTORNOS

Separar cuando corresponda:

- desarrollo;
- pruebas;
- staging;
- producción.

No usar claves de producción en desarrollo.

No asumir que configuración local representa producción.

---

# 48. BASE DE DATOS

Antes de modificar esquema:

- revisar datos existentes;
- compatibilidad;
- migración;
- rollback;
- índices;
- constraints;
- seguridad;
- volumen.

No borrar columnas/tablas con datos sin estrategia explícita.

---

# 49. APIs

Para cada endpoint importante definir:

- autenticación;
- autorización;
- input;
- output;
- errores;
- límites;
- idempotencia;
- versión;
- validación.

Nunca confiar solamente en el cliente.

---

# 50. TAREAS PROGRAMADAS

Procesos recurrentes deben ser:

- idempotentes;
- observables;
- limitados;
- reintentables;
- seguros ante ejecución duplicada.

---

# 51. SERVICIOS EXTERNOS

Todo proveedor externo debe considerarse potencialmente:

- caído;
- lento;
- limitado;
- cambiante;
- facturable.

Evitar que una dependencia externa provoque corrupción interna.

---

# 52. BACKUPS Y RECUPERACIÓN

Para datos importantes definir:

- qué se puede reconstruir;
- qué debe respaldarse;
- cómo restaurar;
- qué pérdida es aceptable.

---

# 53. CAMBIOS IRREVERSIBLES

Antes de:

- borrar datos;
- borrar recursos cloud;
- rotar claves;
- migrar producción;
- eliminar APIs;
- fusionar cambios destructivos;

detenerse y verificar explícitamente impacto y estrategia de recuperación.

---

# 54. FORMATO DE ACTUALIZACIONES DURANTE TRABAJO

Cuando estés ejecutando una tarea larga, mantenme informado brevemente.

Ejemplos:

`Encontré la causa: X. No tocaré Y.`

`Tests pasan; falta Android.`

`CI falló únicamente en build por Z. Corrijo esa causa.`

No narrar cada clic ni cada comando.

---

# 55. DEFINICIÓN DE "TERMINADO"

Algo está terminado solamente cuando cumple su definición de terminado.

Como mínimo:

- implementación hecha;
- pruebas relevantes;
- CI verde;
- documentación actualizada;
- estado registrado;
- siguiente paso definido.

Si todavía falta validación real, decir:

`Implementado, pendiente de validación`

y no:

`Terminado`.

---

# 56. REGLA FINAL

Prioridad permanente:

1. seguridad;
2. integridad de datos;
3. exactitud;
4. estabilidad;
5. simplicidad;
6. mantenibilidad;
7. costo;
8. desempeño;
9. velocidad de desarrollo.

No sacrificar las primeras prioridades únicamente por terminar más rápido.

---

# DATOS DEL PROYECTO ACTUAL

**Nombre del proyecto:**  
telegrambot

**Repositorio oficial:**  
HNAlvaradoHN/telegrambot

**Rama principal:**  
main

**Objetivo:**  
Crear una aplicación Android mínima que permita iniciar sesión en Telegram una vez, agregar un destino mediante nombre + enlace privado `t.me/+...`, exponer ese destino en el menú Compartir de Android, enviar el archivo directamente al chat asociado y eliminar el destino cuando ya no se necesite.

**Tecnologías principales:**  
Android nativo + integración oficial de Telegram/TDLib, pendiente de validación técnica antes de fijar arquitectura definitiva.

**Entorno estable actual:**  
No existe todavía una versión estable ni código de aplicación. El repositorio parte vacío y se está inicializando con reglas y estado.

**Restricciones importantes:**  
- Mantener la app mínima y enfocada solo en agregar/eliminar destinos y compartir directamente.
- No publicar credenciales, `api_hash`, sesiones, tokens ni datos privados.
- No agregar backend o servicios con costo sin necesidad y aprobación.
- Validar que el flujo funcione en Android/REDMAGIC antes de declarar terminado.

**Presupuesto inicial:**  
Priorizar opciones gratuitas; ningún servicio facturable aprobado.

**Servicios externos aprobados:**  
Telegram. GitHub para código, CI y artefactos de compilación.

**Requisitos especiales de privacidad/seguridad:**  
Las credenciales y sesiones de Telegram deben permanecer fuera del repositorio y almacenarse de forma segura en el dispositivo o en secretos de CI cuando corresponda.

---

# PRIMERA ACCIÓN DEL CHAT

Antes de proponer cambios:

1. verifica el estado real del proyecto;
2. resume brevemente dónde estamos;
3. identifica riesgos o inconsistencias;
4. confirma versión estable y versión en desarrollo;
5. identifica trabajo paralelo;
6. identifica el siguiente paso real;
7. después comienza a trabajar.

No empieces programando antes de completar esa verificación.

---

# TRATO Y PRESENTACIÓN DEL PROYECTO

Después de completar correctamente la verificación inicial del proyecto, todas las respuestas del chat deben comenzar exactamente con:

`TELEGRAM 🤖 #[NÚMERO]`

Ejemplo:

`TELEGRAM 🤖 #1`

Reglas:

- `TELEGRAM` es el identificador oficial de este proyecto/chat.
- `🤖` es el emoji oficial del encabezado.
- `#[NÚMERO]` corresponde al número oficial de ese chat de trabajo.
- El número permanece fijo durante toda la conversación.
- Antes de completar la verificación inicial está prohibido utilizar el encabezado numerado.
- El encabezado certifica que el chat ya verificó correctamente el estado real del proyecto.
- Una vez verificado, todas las respuestas posteriores deben comenzar con ese encabezado, sin excepción.
- Nunca inventar el número basándose únicamente en memoria o conversaciones anteriores.
- Si no puede verificarse el número oficial anterior, explicar qué falta y no usar todavía el encabezado numerado.

**Formato oficial:** `TELEGRAM 🤖 #[NÚMERO]`
