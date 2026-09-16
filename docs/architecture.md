# Arquitectura mínima — Checkpoint 1

Estado: validada a nivel documental; todavía no implementada ni probada en Android real.

## Objetivo

Permitir que el usuario inicie sesión con su cuenta de Telegram, registre un destino con `nombre + enlace privado t.me/+...`, lo vea como destino individual en el Sharesheet de Android, envíe directamente el contenido compartido a ese chat y pueda eliminar el destino.

## Decisión

### Android

- Aplicación Android nativa en Kotlin.
- Publicar cada destino mediante Android Sharing Shortcuts (`ShortcutManagerCompat`) y declarar el `share-target` correspondiente en `res/xml/shortcuts.xml`.
- Una actividad dedicada recibe `ACTION_SEND` y `ACTION_SEND_MULTIPLE`, obtiene `Intent.EXTRA_SHORTCUT_ID` y enruta el contenido al destino persistido.
- Al eliminar un destino, retirar también su shortcut mediante la API de shortcuts de Android.

Motivo: desde Android 11 los Direct Share targets se publican mediante Sharing Shortcuts; AndroidX ofrece la ruta compatible recomendada.

### Telegram

- Usar TDLib, cliente oficial de Telegram, no Bot API.
- La app requiere `api_id` y `api_hash` propios para autorizar una cuenta de usuario. No se guardarán en Git ni se incluirán valores reales en ejemplos.
- TDLib mantiene red, sesión y almacenamiento local; su directorio debe permanecer en almacenamiento privado de la aplicación.
- Tras `authorizationStateReady`, la app puede resolver/importar el enlace privado y enviar mensajes/archivos al `chat_id` resultante.
- El envío usa `sendMessage(chat_id, ...)` con contenido apropiado; para archivos locales TDLib admite `inputFileLocal`.

Motivo: el requisito es enviar como la cuenta del usuario. Un bot no equivale a la cuenta del usuario y no cumple el contrato del producto.

## Resolución de `t.me/+...`

Telegram reconoce enlaces de invitación `t.me/+<hash>` (y el formato legado `t.me/joinchat/<hash>`). El flujo debe ser:

1. validar sintaxis localmente y extraer el hash/enlace;
2. pedir a TDLib información del enlace;
3. si la cuenta ya pertenece al chat, obtener/resolver el chat y conservar su `chat_id`;
4. si no pertenece, importar/unirse mediante el enlace cuando sea posible;
5. si el enlace requiere aprobación de administrador, registrar estado `PENDING_APPROVAL` y NO crear un destino enviable hasta que la cuenta sea miembro y pueda escribir;
6. comprobar capacidad de escritura antes de marcar el destino como listo.

No asumir que todo enlace válido permite escribir: puede ser canal de solo lectura, enlace expirado/revocado, solicitud de ingreso pendiente o chat sin permiso de publicación.

## Modelo local mínimo

```text
ShareDestination
- id: UUID local estable (también shortcutId)
- displayName: String
- chatId: Long
- inviteLink: String opcional
- state: READY | PENDING_APPROVAL | INVALID
```

Persistir solo lo necesario. El contenido compartido no se conserva después de completar/fallar el envío salvo lo que TDLib necesite internamente.

## Seguridad y secretos

- Nunca versionar `api_hash`, códigos de acceso, contraseña 2FA ni directorios/sesiones de TDLib.
- Mantener datos/sesión de TDLib en almacenamiento privado de la app.
- No registrar números de teléfono, códigos, contraseñas, enlaces privados completos ni contenido enviado en logs.
- El `api_hash` es configuración sensible de la aplicación; el repositorio usará únicamente placeholders y documentación para suministrarlo fuera de Git.

## Contrato de envío

Tocar un Direct Share target debe identificar un único destino sin mostrar otro selector de chats. La actividad receptora puede mostrar únicamente estado/progreso/error mínimo si es necesario.

Estados mínimos de operación:

`RECEIVED -> SENDING -> SENT | FAILED`

No mostrar `SENT` hasta recibir confirmación de TDLib de que la solicitud de envío fue aceptada correctamente; la validación final del comportamiento real queda pendiente de dispositivo.

## Tipos de contenido MVP

Primera implementación: `text/plain`, `image/*`, `video/*` y `application/pdf`, uno por envío. `ACTION_SEND_MULTIPLE` se deja para un checkpoint posterior salvo que resulte trivial después del flujo individual.

## Riesgos conocidos

1. **Credenciales de API obligatorias.** Telegram exige `api_id`/`api_hash` propios para clientes de usuario; los valores de ejemplo no sirven para distribución real.
2. **Invitación no significa permiso de escritura.** Un enlace puede requerir aprobación o apuntar a un canal donde el usuario no puede publicar.
3. **TDLib añade binarios nativos y complejidad de build.** Antes de fijar la dependencia hay que comprobar el método de integración Android vigente, ABI y tamaño del APK.
4. **Sharesheet depende del sistema/OEM.** Publicar un shortcut lo hace elegible, pero Android controla ranking/posición; no se puede prometer una posición fija. Debe probarse específicamente en REDMAGIC.
5. **URI compartida.** La actividad debe respetar permisos temporales `content://` y preparar el archivo para TDLib sin copiar contenido innecesariamente.

## Fuentes primarias verificadas (2026-09-15)

- Telegram TDLib: https://core.telegram.org/tdlib
- Getting started TDLib: https://core.telegram.org/tdlib/getting-started
- Telegram API ID: https://core.telegram.org/api/obtaining_api_id
- Telegram invite links: https://core.telegram.org/api/invites
- Telegram deep links: https://core.telegram.org/api/links
- Android Direct Share / Sharing Shortcuts: https://developer.android.com/develop/ui/compose/sharing/direct-share-targets

## Checkpoint 1 — resultado

Arquitectura viable en principio con Android Sharing Shortcuts + TDLib. No existe evidencia todavía de build Android, autenticación, resolución del enlace ni envío real; por tanto no se declara ninguna funcionalidad terminada.

## Siguiente checkpoint

Checkpoint 2: crear el esqueleto Android mínimo en una rama `feat/*`, configurar build reproducible/CI sin secretos y demostrar que una APK vacía con la actividad receptora de Share Targets compila. TDLib se integra después de que el esqueleto esté verde, para aislar fallos de build de fallos de Telegram.
