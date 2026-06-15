public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TEST DEL SISTEMA MUNDIAL ===\n");

        // 1. Instanciar la Gestión
        GestionDelegaciones gestion = new GestionDelegaciones();

        // 2. Crear Selecciones (Asumimos constructores vacíos o simples para Pais)
        Seleccion seleccionArg = new Seleccion("AFA", "Celeste y Blanca", "Azul", true, 1, null, null);
        gestion.registrarNuevaSeleccion(seleccionArg);

        Seleccion seleccionBra = new Seleccion("CBF", "Amarilla", "Azul", true, 2, null, null);
        gestion.registrarNuevaSeleccion(seleccionBra);

        // 3. Crear Jugadores
        Jugador messi = new Jugador("Lionel Messi", 19870624, 10, Posicion.DELANTERO, 72.5f, 1.70f, seleccionArg);
        Jugador dibu = new Jugador("Dibu Martinez", 19920902, 23, Posicion.ARQUERO, 88.0f, 1.95f, seleccionArg);

        // 4. Testear la Excepción de Inscripción
        System.out.println("--- TEST: INSCRIPCIÓN DE JUGADORES ---");
        try {
            gestion.inscribirJugador(seleccionArg, messi);
            gestion.inscribirJugador(seleccionArg, dibu);
            System.out.println("Jugadores de AFA inscriptos con éxito.");

            // Acá forzamos el error para probar tu nueva excepción
            System.out.println("Intentando inscribir a Messi en Brasil...");
            gestion.inscribirJugador(seleccionBra, messi);

        } catch (JugadorYaInscriptoException e) {
            // Esto es lo que debería imprimirse
            System.out.println("¡Excepción capturada correctamente! -> " + e.getMessage());
        }

        // 5. Crear Fase y Grupo
        System.out.println("\n--- TEST: ARMADO DE GRUPO Y PARTIDO ---");
        Fase faseGrupos = new Fase();
        // Asumiendo que Fase tiene un setNombreFase. Si no, ajustalo a tu clase.
        // faseGrupos.setNombreFase(NombreFase.GRUPOS);

        Grupo grupoA = new Grupo("A", "Grupo A", faseGrupos);
        grupoA.asociarSeleccion(seleccionArg);
        grupoA.asociarSeleccion(seleccionBra);

        // 6. Armar el Partido
        Partido clasico = new Partido();
        clasico.setCorrespondeFase(faseGrupos);

        // Armamos las participaciones (acá es donde entra en juego la clase clave)
        Participacion partArg = new Participacion(true, seleccionArg);
        partArg.asociarPartido(clasico);
        seleccionArg.agregarParticipacion(partArg);

        Participacion partBra = new Participacion(false, seleccionBra);
        partBra.asociarPartido(clasico);
        seleccionBra.agregarParticipacion(partBra);

        clasico.setEquipoLocal(partArg);
        clasico.setEquipoVisitante(partBra);

        // 7. Simular Eventos sin usar nulls (como acordamos)
        // Simulamos un gol de Messi
        Evento golMessi = new Evento(TipoEvento.GOL, 15);
        golMessi.setInvolucraJugador(messi);
        clasico.getEventos().add(golMessi);

        // Simulamos otro gol de Messi
        Evento golMessi2 = new Evento(TipoEvento.GOL, 40);
        golMessi2.setInvolucraJugador(messi);
        clasico.getEventos().add(golMessi2);

        // 8. Testear el cálculo automático de puntos
        System.out.println("\n--- RESULTADOS FASE DE GRUPOS ---");
        System.out.println("Goles Argentina en el partido: " + partArg.cantidadGoles()); // Debería ser 2
        System.out.println("Goles Brasil en el partido: " + partBra.cantidadGoles());    // Debería ser 0

        System.out.println("\nTabla de Puntos Grupo A:");
        System.out.println("Puntos Argentina: " + grupoA.obtenerPuntos(seleccionArg)); // Debería ser 3 (por victoria)
        System.out.println("Puntos Brasil: " + grupoA.obtenerPuntos(seleccionBra));    // Debería ser 0 (por derrota)
    }
}