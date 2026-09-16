# PROJECT STATE — telegrambot

## Identidad de sesión

- Encabezado oficial obligatorio: `Ing. TELEGRAM 🤖 #[NÚMERO]`
- Condición para usarlo: haber leído completas las reglas y completado la verificación inicial del repositorio/estado.
- Sesión actual registrada: `#1`
- Fecha de inicio del proyecto: 2026-09-15

## Estado verificado

- Repositorio oficial: `HNAlvaradoHN/telegrambot`
- Rama principal: `main`
- Visibilidad: privada
- Rama de trabajo: `docs/checkpoint-1-architecture`
- Pull Request activo: pendiente de abrir para Checkpoint 1
- Trabajo paralelo detectado antes de iniciar: ninguno
- CI: todavía no configurado
- Código de aplicación: todavía no existe
- Versión estable: ninguna
- Versión en desarrollo: arquitectura validada documentalmente; sin versión funcional
- Despliegue estable: ninguno
- Despliegue experimental: ninguno

## Objetivo actual

Crear una aplicación Android mínima que permita:

1. iniciar sesión en Telegram una sola vez;
2. agregar un destino con nombre + enlace privado `t.me/+...`;
3. registrar ese destino para compartir desde Android;
4. compartir un archivo directamente al chat asociado sin tener que buscarlo manualmente;
5. eliminar el destino cuando ya no se necesite.

## Restricciones clave

- Mantener el alcance mínimo.
- No subir `api_hash`, tokens, sesiones, credenciales ni datos privados.
- No activar servicios facturables sin aprobación.
- No declarar el envío directo como resuelto hasta validarlo en un dispositivo Android real.
- Preferir integración oficial de Telegram y APIs oficiales de Android cuando sea viable.

## Checkpoints

### Checkpoint 1 — arquitectura mínima

Estado: **validado documentalmente; pendiente de revisión/merge**.

Decisión propuesta y documentada en `docs/architecture.md`:

- Android nativo/Kotlin.
- Sharing Shortcuts (`ShortcutManagerCompat`) para publicar destinos individuales en Sharesheet.
- TDLib para autenticación y envío como cuenta de usuario.
- `api_id`/`api_hash` propios suministrados fuera de Git.
- Persistencia local mínima del destino (`id`, nombre, `chatId`, enlace opcional y estado).
- No marcar un enlace como enviable si requiere aprobación o el usuario no puede escribir.

Riesgos principales: credenciales API obligatorias, permisos de escritura del chat, complejidad/binarios nativos de TDLib, ranking OEM del Sharesheet y manejo de URI `content://`.

## KNOWN ISSUES

- No hay build Android todavía; por tanto no existe APK verificable.
- Integración exacta de TDLib/ABI/tamaño todavía no fue probada en Gradle.
- El comportamiento de Sharing Shortcuts debe validarse en REDMAGIC; Android decide ranking/posición.
- No se dispone aún de credenciales Telegram API para una prueba real; nunca deben subirse al repositorio.

## Reglas del proyecto

Leer antes de modificar cualquier cosa y antes de usar el encabezado oficial:

- `docs/rules/00-identity-gate.md`
- `docs/rules/01-core.md`
- `docs/rules/02-git-quality-state.md`
- `docs/rules/03-runtime-product-project.md`

`docs/rules/00-identity-gate.md` tiene prioridad sobre formatos de encabezado anteriores.

## Siguiente paso real

Checkpoint 2: después de integrar/revisar Checkpoint 1, crear un esqueleto Android mínimo en una rama `feat/*`, configurar build reproducible y CI sin secretos, declarar una actividad receptora + Sharing Shortcuts y demostrar que una APK base compila. Integrar TDLib solo después de tener ese build verde para aislar fallos.
