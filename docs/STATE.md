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
- Pull Request activo: #2 `feat(android): bootstrap share receiver app`; abierto, mergeable, no fusionado.
- Trabajo paralelo detectado: rama documental anterior de Checkpoint 1; su contenido ya fue integrado y no debe usarse para cambios nuevos.
- CI: cuatro runs de Android CI han fallado antes de ejecutar steps. El más reciente, run `35070353743` sobre commit `d19dbbe`, terminó en failure; job `104710178896` reporta `steps: []`, `runner_id: 0`, `runner_name: ""`. El check run contiene exactamente 1 anotación, pero el conector disponible no permite leer el endpoint de anotaciones. Esto confirma que el bloqueo persiste antes del checkout/build y no aporta evidencia de fallo de Gradle/código.
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

- Run `35061184858`: failure antes de steps; job `104681649052` sin runner/steps.
- Run `35065397108`: failure antes de steps; job `104694430559` con `steps: []`; descarga de logs no disponible.
- Run `35070353743`: failure en ~4 segundos sobre `d19dbbe`; job `104710178896` con `steps: []`, `runner_id: 0`, `runner_name: ""`, labels `["ubuntu-latest"]`.
- Check run `104710178896`: `annotations_count: 1`; el endpoint concreto de anotaciones no es accesible mediante el conector actual, por lo que no se inventa su contenido.
- El workflow declarado sigue usando `runs-on: ubuntu-latest` y steps estándar (`checkout`, Java 17, Android setup, Gradle 8.10.2, `:app:assembleDebug`). No existe un error de step que justifique cambiarlo todavía.

La repetición confirma el síntoma pre-runner/pre-step. La causa exacta sigue sin poder determinarse desde las superficies accesibles. Posibles causas de cuenta/configuración/límites de Actions siguen siendo hipótesis, no hechos.

## KNOWN ISSUES

- **CI no inicia steps (prioridad alta para Checkpoint 2):** cuatro runs terminan antes del checkout; el último job `104710178896` tiene `steps: []` y `runner_id: 0`. El check asociado sí registra una anotación, pero su texto no es accesible con el conector actual. Causa exacta desconocida. Impacto: no se puede declarar el bootstrap compilable ni producir APK verificable. Acción: revisar en GitHub la anotación visible del job/check y la configuración/estado/límites de Actions, o ejecutar `:app:assembleDebug` en un entorno Android local. No alterar Gradle/código para intentar resolver un fallo que ocurre antes de ejecutarlos.
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

Checkpoint 2 permanece bloqueado por infraestructura de CI. Antes de cualquier cambio de código, leer la única anotación del check/job fallido desde la interfaz de GitHub o una API que permita ese endpoint, y revisar configuración/estado/límites de GitHub Actions del repositorio/cuenta. Alternativamente, disponer de un entorno Android local que pueda ejecutar `:app:assembleDebug`. Si el build llega a verde, registrar el APK debug como artefacto verificable y cerrar Checkpoint 2 antes de implementar publicación dinámica/persistencia de destinos. TDLib sigue fuera de alcance hasta tener el bootstrap verde.
