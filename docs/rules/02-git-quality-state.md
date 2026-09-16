# 17. GIT

Mantener historial entendible.

Los commits deben:

- representar cambios coherentes;
- tener mensajes descriptivos;
- evitar mezclar tareas diferentes;
- evitar commits vacíos salvo necesidad técnica excepcional;
- evitar basura temporal.

Ejemplos:

`feat(auth): add device session validation`

`fix(sync): prevent duplicate reconciliation`

`test(transfer): cover reconnect state`

`docs(project): update checkpoint status`

---

# 18. RAMAS

Antes de crear una rama:

- comprobar que no exista trabajo equivalente;
- comprobar ramas activas;
- comprobar PR abiertos.

Eliminar ramas temporales cuando ya no sean necesarias.

No acumular ramas abandonadas sin motivo.

La rama principal debe mantenerse estable.

---

# 19. PULL REQUESTS

Cada PR debe tener claro:

- objetivo;
- alcance;
- implementación;
- riesgos;
- pruebas;
- dependencias;
- estado;
- siguiente paso.

Si un PR depende de otro, documentarlo explícitamente.

No fusionar un PR dependiente antes de resolver correctamente su dependencia.

No marcar listo un PR con CI fallando.

---

# 20. CI Y VALIDACIONES

Las validaciones deben ejecutarse según el tipo de proyecto.

Ejemplos:

- tests;
- lint;
- build;
- typecheck;
- Android build;
- iOS build;
- backend tests;
- migrations;
- seguridad;
- auditoría de dependencias.

Una etapa significativa no está cerrada hasta que sus verificaciones relevantes pasen.

Si una validación tarda, no hacer cambios innecesarios mientras está ejecutándose porque eso puede cancelar o mezclar resultados.

---

# 21. PRUEBAS

Priorizar pruebas que validen comportamiento real.

Incluir cuando corresponda:

- happy path;
- errores;
- límites;
- datos inválidos;
- concurrencia;
- reconexión;
- expiración;
- permisos;
- aislamiento;
- regresiones;
- persistencia;
- limpieza.

No escribir pruebas únicamente para aumentar cobertura.

Una prueba debe proteger un contrato o comportamiento importante.

---

# 22. MIGRACIONES

Cuando se reemplace una arquitectura o sistema antiguo:

No reemplazar todo de golpe.

Usar migración progresiva:

1. crear nueva capa;
2. probarla aislada;
3. crear adaptadores;
4. crear frontera de producto;
5. migrar una función;
6. validar;
7. migrar otra;
8. retirar legacy solamente cuando exista reemplazo probado.

Nunca eliminar una ruta estable antes de demostrar que su sustituto funciona.

---

# 23. COMPATIBILIDAD

Antes de modificar contratos usados por otras partes revisar:

- API pública;
- esquemas;
- formatos;
- almacenamiento;
- clientes existentes;
- versiones anteriores;
- migraciones necesarias.

Evitar romper compatibilidad sin necesidad.

Si debe romperse, documentar claramente la transición.

---

# 24. ESTADO DURABLE

Cuando exista estado persistente:

- definir qué se guarda;
- definir por qué;
- definir cuándo expira;
- definir cómo se recupera;
- definir qué ocurre si se corrompe;
- definir cómo se limpia.

No guardar datos innecesarios "por si acaso".

---

# 25. LIMPIEZA

Revisar periódicamente:

- datos vencidos;
- cachés;
- logs;
- archivos;
- imágenes;
- sesiones;
- registros;
- ramas;
- dependencias;
- código muerto;
- recursos cloud.

El sistema debe poder limpiar lo temporal automáticamente cuando sea razonable.

---

# 26. CONCURRENCIA Y TRABAJO PARALELO

Antes de empezar una nueva modificación importante:

- comprobar si otra rama toca los mismos archivos;
- comprobar si existe PR relacionado;
- comparar ramas;
- evitar sobrescribir trabajo ajeno;
- integrar o coordinar cuando sea necesario.

Si detectas trabajo paralelo nuevo, detener cualquier cambio conflictivo y reevaluar el siguiente paso real.

---

# 27. NO INVENTAR ESTADO

Distinguir siempre entre:

### Hecho verificado
Comprobado directamente.

### Conclusión razonable
Derivada de evidencia.

### Suposición
No comprobada.

### Desconocido
Información que todavía no está disponible.

Nunca presentar una suposición como hecho.

---

# 28. COMUNICACIÓN CONMIGO

Responder de forma:

- breve;
- directa;
- clara;
- comprensible;
- visualmente fácil de leer.

Evitar bloques enormes de texto cuando pueda resumirse.

Explicar principalmente:

**Qué significa → Qué recomiendo → Por qué → Riesgo importante si existe.**

No saturar con jerga.

Cuando haya muchos cambios, usar pocos puntos claros.

No preguntarme cosas que ya están respondidas en el repositorio o conversación.

---

# 29. AUTONOMÍA

Si el siguiente paso es evidente y seguro, continúa sin pedirme confirmación constantemente.

Preguntar solamente cuando haya una decisión que realmente requiera mi criterio, por ejemplo:

- costo;
- cambio importante de arquitectura;
- pérdida de datos;
- operación irreversible;
- decisión de producto ambigua;
- acceso externo sensible.

No detener trabajo por preguntas triviales.

---

# 30. NO HACER TRABAJO INNECESARIO

No implementar características futuras solo porque eventualmente podrían necesitarse.

Aplicar:

**necesidad actual > diseño limpio > extensibilidad razonable > especulación futura.**

Evitar sobreingeniería.

---

# 31. DOCUMENTACIÓN MÍNIMA OBLIGATORIA

El proyecto debe poder entenderse sin depender de ChatGPT.

Mantener documentación suficiente para conocer:

- qué es el proyecto;
- arquitectura;
- estructura;
- cómo ejecutarlo;
- cómo probarlo;
- decisiones importantes;
- seguridad;
- problemas conocidos;
- estado actual;
- roadmap;
- ramas/PR relevantes;
- versión estable;
- versión en desarrollo;
- siguiente paso exacto.

---

# 32. REGISTRO DE DECISIONES

Cuando se tome una decisión importante registrar:

- decisión;
- contexto;
- alternativas;
- motivo;
- consecuencias.

Ejemplo:

`DEC-012 — Usar almacenamiento local para X`

Motivo:
...

Alternativas descartadas:
...

Impacto:
...

Esto evita discutir la misma decisión repetidamente en chats futuros.

---

# 33. PROBLEMAS CONOCIDOS

Mantener una sección explícita:

`KNOWN ISSUES`

Cada problema debe indicar cuando sea posible:

- síntoma;
- causa conocida o desconocida;
- impacto;
- workaround;
- prioridad;
- estado.

Eliminar problemas que ya fueron resueltos.

---

# 34. ROADMAP

El roadmap debe reflejar el orden real de implementación, no una lista ideal antigua.

Cuando cambia la prioridad, actualizarlo.

Cada próximo paso debería ser suficientemente concreto para que otro chat pueda empezar sin adivinar.

Mal:

`Continuar backend`

Bien:

`Checkpoint 5: validar sesión en servidor antes de permitir escritura y añadir tests de sesión expirada.`

---

# 35. CIERRE DE UNA ETAPA SIGNIFICATIVA

Antes de declarar una etapa terminada:

1. comprobar funcionamiento;
2. ejecutar pruebas;
3. comprobar CI;
4. revisar efectos relacionados;
5. eliminar basura generada;
6. registrar commits;
7. actualizar documentación;
8. actualizar decisiones si corresponde;
9. actualizar problemas conocidos;
10. actualizar roadmap;
11. registrar ramas/PR activos;
12. registrar versión estable;
13. registrar versión en desarrollo;
14. registrar último punto estable;
15. registrar despliegue verificado si existe;
16. dejar el siguiente paso exacto.

---

# 36. HANDOFF PARA OTRO CHAT

Al cerrar una sesión de trabajo, el repositorio debe contener suficiente contexto para que un chat nuevo pueda continuar sin depender de la conversación anterior.

El nuevo chat debería poder responder leyendo el repositorio:

- dónde estamos;
- qué está terminado;
- qué está en desarrollo;
- qué falló;
- qué decisiones existen;
- qué ramas están activas;
- qué PR están abiertos;
- qué CI está verde;
- qué no debe tocarse;
- cuál es el siguiente paso.

Si esto no es posible, el cierre está incompleto.
