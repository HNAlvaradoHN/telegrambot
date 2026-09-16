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
- Rama activa: `main`
- Pull Request activo: ninguno
- Ramas paralelas: ninguna verificada al inicio
- CI: todavía no configurado
- Código de aplicación: todavía no existe
- Versión estable: ninguna
- Versión en desarrollo: bootstrap inicial, sin versión funcional
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

## Reglas del proyecto

Leer antes de modificar cualquier cosa y antes de usar el encabezado oficial:

- `docs/rules/00-identity-gate.md`
- `docs/rules/01-core.md`
- `docs/rules/02-git-quality-state.md`
- `docs/rules/03-runtime-product-project.md`

`docs/rules/00-identity-gate.md` tiene prioridad sobre formatos de encabezado anteriores.

## Siguiente paso real

Checkpoint 1: validar técnicamente la arquitectura mínima para autenticación de Telegram, resolución de enlaces privados `t.me/+...`, persistencia segura del destino y publicación como destino de compartir en Android; documentar riesgos antes de escribir la implementación.
