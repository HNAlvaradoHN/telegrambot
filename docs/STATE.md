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
- Checkpoint 1: integrado en `main` mediante PR #1, commit `11ac8ff`.
- Rama de trabajo: `feat/android-bootstrap`
- Pull Request activo: #2 `feat(android): bootstrap share receiver app`
- Trabajo paralelo detectado: rama documental anterior de Checkpoint 1; su contenido ya fue integrado y no debe usarse para cambios nuevos.
- CI: Android CI run `35061184858` terminó en failure antes de ejecutar ningún step; job `104681649052` muestra `steps: []`, `runner_id: 0` y runner vacío. No hay evidencia de fallo de Gradle/código.
- Código de aplicación: bootstrap Android/Kotlin existente en PR #2.
- Versión estable: ninguna.
- Versión en desarrollo: `0.1.0-dev`, bootstrap no validado todavía por CI ni dispositivo.
- Despliegue estable: ninguno.
- Despliegue experimental: ninguno.

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

Estado: **integrado en main**.

Arquitectura documentada en `docs/architecture.md`: Android nativo/Kotlin + Sharing Shortcuts para destinos + TDLib para autenticación/envío como cuenta de usuario. Los secretos se suministrarán fuera de Git.

### Checkpoint 2 — bootstrap Android

Estado: **implementado en PR #2; bloqueado en validación CI antes de iniciar steps**.

Incluye:

- proyecto Android/Kotlin mínimo;
- `ShareReceiverActivity` para `ACTION_SEND` de texto, imagen, video y PDF;
- declaración `share-target` en `res/xml/shortcuts.xml`;
- GitHub Actions sin secretos para `:app:assembleDebug`;
- publicación de `app-debug.apk` como artefacto si el build pasa.

No incluye TDLib, login, destinos dinámicos, resolución `t.me/+...` ni envío Telegram. La actividad receptora muestra explícitamente que el transporte aún no está integrado.

### Evidencia CI actual

Run `35061184858` (PR #2) finalizó en aproximadamente dos segundos. Su único job `104681649052` no recibió runner (`runner_id: 0`, nombre vacío) y reporta cero steps ejecutados. Los logs del job no están disponibles. Por ello, el fallo ocurre antes del checkout/build y **no debe corregirse modificando Gradle o código sin nueva evidencia**.

## KNOWN ISSUES

- **CI runner no inicia (prioridad alta para Checkpoint 2):** run `35061184858`, job `104681649052`; `steps: []`, `runner_id: 0`. Causa exacta externa al workflow todavía desconocida (posible disponibilidad/configuración/límite de Actions, no confirmada). Impacto: no se puede declarar el bootstrap compilable ni producir APK verificable. Acción: revalidar un run posterior; si repite sin runner, revisar estado/configuración de GitHub Actions sin alterar código a ciegas.
- La publicación dinámica de Sharing Shortcuts todavía no está implementada; solo existe la declaración de share-target necesaria para el bootstrap.
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

Revalidar CI del PR #2. No modificar Gradle/código basándose en el failure actual porque el job no ejecutó ningún step. Si un run posterior obtiene runner y falla dentro de un step, leer ese error y corregir únicamente su causa. Si queda verde, registrar el APK debug como artefacto verificable y cerrar Checkpoint 2 antes de implementar publicación dinámica/persistencia de destinos. TDLib sigue fuera de alcance hasta tener el bootstrap verde.
