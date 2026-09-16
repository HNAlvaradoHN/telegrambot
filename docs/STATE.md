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
- CI: workflow Android agregado en PR #2; primer run pendiente de aparecer/verificarse.
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

Estado: **implementado en PR #2; pendiente de CI**.

Incluye:

- proyecto Android/Kotlin mínimo;
- `ShareReceiverActivity` para `ACTION_SEND` de texto, imagen, video y PDF;
- declaración `share-target` en `res/xml/shortcuts.xml`;
- GitHub Actions sin secretos para `:app:assembleDebug`;
- publicación de `app-debug.apk` como artefacto si el build pasa.

No incluye TDLib, login, destinos dinámicos, resolución `t.me/+...` ni envío Telegram. La actividad receptora muestra explícitamente que el transporte aún no está integrado.

## KNOWN ISSUES

- PR #2 aún no tiene un resultado CI verificado; no existe APK declarada válida todavía.
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

Leer el primer GitHub Actions run del PR #2. Si falla, identificar y corregir únicamente la causa real. Si queda verde, registrar el APK debug como artefacto verificable y cerrar Checkpoint 2 antes de implementar publicación dinámica/persistencia de destinos. TDLib sigue fuera de alcance hasta tener el bootstrap verde.
