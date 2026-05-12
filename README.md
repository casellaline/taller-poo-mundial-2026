# taller-poo-mundial-2026
Trabajo final integrador para promocionar POO (UNER). Consiste en un sistema desarrollado en Java para la gestión integral de un Mundial de fútbol.

---

## Trabajo Final: Sistema de Gestión del Mundial

* **Institución:** Universidad Nacional de Entre Ríos (UNER)
* **Materia:** Programación II (POO)
* **Año Académico:** 2026
* **Fecha de Entrega:** 24/06/2026

### Grupo de Trabajo
* **Aliné Sol Casella** - casellasol03@gmail.com
* **Luis Ortiz** - 

---

## Estructura del Repositorio y Archivos a Entregar

De acuerdo a los requerimientos de la cátedra, este repositorio contiene los siguientes elementos:

* `src/` **Software:** Contiene todo el código fuente del sistema desarrollado en lenguaje Java.
* `docs/` **Documentación:** Incluye la documentación técnica generada automáticamente por JavaDoc, exportada en formato PDF.
* `Diagrama/`**Diagrama:** Referencia al diagrama de clases UML brindado en la propuesta que guió el desarrollo.

---

## Descripción del Proyecto y Funcionalidades

El sistema es una plataforma centralizada que resuelve la logística y la información deportiva de un torneo Mundial, implementando el paradigma de Orientación a Objetos. 

Las características principales incluyen:
* **Gestión de Infraestructura:** Registro de Sedes y Estadios con sus capacidades.
* **Administración de Delegaciones:** ABM de Países, Selecciones, Cuerpos Técnicos y Jugadores.
* **Organización Deportiva:** Configuración de Fases, Grupos y Partidos.
* **Eventos en Tiempo Real:** Registro de incidencias de campo (minuto de juego, tipo de evento y jugador involucrado).

### Informes y Estadísticas
El sistema procesa la información para generar:
1. **Tabla de Posiciones por Grupo:** Refleja la clasificación sumando 3 puntos por victoria, 1 por empate y 0 por derrota.
2. **Tabla de Resultados por Selección:** Puntajes e instancias alcanzadas.
3. **Ranking de Goleadores:** Jugadores ordenados por tantos convertidos.
4. **Informe Disciplinario:** Listado histórico de tarjetas por equipo o jugador.
5. **Ficha Técnica de Partido:** Resumen con alineaciones, eventos y el marcador final.
6. **Estadísticas de Sedes:** Cantidad de partidos albergados por cada estadio/ciudad.

### Condiciones
* Un jugador pertenece a una única selección nacional.
* Un partido requiere la asignación de un equipo de Arbitraje válido.
* Los eventos de campo solo pueden adjudicarse a jugadores activos en el partido correspondiente.
* La carga del resultado de un partido impacta de forma automática en las estadísticas de los grupos.

  
