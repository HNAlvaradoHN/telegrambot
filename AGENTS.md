# telegrambot — instrucciones obligatorias para cualquier agente/chat

Antes de modificar código, documentación, ramas, PR, commits o estado del proyecto, y antes de utilizar el encabezado oficial:

1. leer `docs/STATE.md`;
2. leer completas las reglas en este orden:
   - `docs/rules/00-identity-gate.md`
   - `docs/rules/01-core.md`
   - `docs/rules/02-git-quality-state.md`
   - `docs/rules/03-runtime-product-project.md`;
3. verificar repositorio, rama principal, ramas activas, PR, commits, CI, código, documentación, problemas conocidos, decisiones, roadmap, versión estable, versión en desarrollo y trabajo paralelo;
4. verificar el número de sesión registrado en `docs/STATE.md` y, cuando corresponda a un chat nuevo, registrar el siguiente número antes de anunciarlo;
5. solamente después de completar los pasos anteriores se permite utilizar el encabezado oficial.

La identidad oficial y obligatoria de las respuestas de este proyecto es:

`Ing. TELEGRAM 🤖 #[NÚMERO]`

Usar ese encabezado certifica que el agente/chat leyó las reglas completas y verificó el estado real del proyecto. Antes de completar esa verificación está prohibido utilizarlo.

Si una regla anterior muestra el encabezado sin `Ing.`, prevalece `docs/rules/00-identity-gate.md`.

No subir secretos, credenciales de Telegram, `api_hash`, tokens ni datos de sesión al repositorio.
