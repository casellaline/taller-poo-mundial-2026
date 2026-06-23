/**
 * Clase utilitaria que carga un conjunto de datos de prueba
 * (hardcodeados) en las gestoras para poder probar el sistema.
 */
public class InicializadorDatos {
    /**
     * Método estático que se encarga de cargar datos de prueba al sistema.
     * Con el objetivo de aislar la carga de datos de la lógica.
     */

    /**
     * Carga en las gestoras un conjunto de datos de prueba (paises,
     * selecciones, jugadores, sedes, grupos y partidos).
     *
     * @param gestoraDelegaciones gestoraDelegaciones
     * @param gestoraInfraestructura gestoraInfraestructura
     * @param gestoraDeportiva gestoraDeportiva
     */
    public static void cargarDatosDePrueba(GestionDelegaciones gestoraDelegaciones,
                                           GestoraInfraestructura gestoraInfraestructura,
                                           OrganizacionDeportiva gestoraDeportiva) {

        try {
            // 1. INFRAESTRUCTURA: Mundial, Sedes y Estadios

            gestoraInfraestructura.configurarMundial(2026, "Striker", 20260611, 20260719);

            Sede sedeBA = gestoraInfraestructura.registrarSede("Buenos Aires", 25.0f, "Templado", "UTC-3");
            Estadio monumental = gestoraInfraestructura.registrarEstadio("Mas Monumental", 84000, sedeBA);

            Sede sedeSP = gestoraInfraestructura.registrarSede("San Pablo", 760.0f, "Tropical", "UTC-3");
            Estadio morumbi = gestoraInfraestructura.registrarEstadio("Morumbi", 72000, sedeSP);


            // 2. DELEGACIONES: Países, Selecciones y Jugadores

            // --- ARGENTINA ---
            Pais paisArg = gestoraDelegaciones.registrarPais("Argentina", "Celeste y Blanca");
            Seleccion selArg = gestoraDelegaciones.registrarSeleccion("AFA", "Celeste y Blanca", "Azul", true, 1, paisArg);

            gestoraDelegaciones.inscribirJugador("Lionel Messi", 1987, 10, Posicion.DELANTERO, 72.0f, 1.70f, selArg);
            gestoraDelegaciones.inscribirJugador("Emiliano Martinez", 1992, 23, Posicion.ARQUERO, 88.0f, 1.95f, selArg);

            DirectorTecnico dtArg = new DirectorTecnico("Lionel Scaloni", 1978, 2018);
            selArg.agregarDirectorTecnico(dtArg);

            // BRASIL
            Pais paisBra = gestoraDelegaciones.registrarPais("Brasil", "Verde y Amarilla");
            Seleccion selBra = gestoraDelegaciones.registrarSeleccion("CBF", "Amarilla", "Azul", true, 5, paisBra);

            gestoraDelegaciones.inscribirJugador("Vinicius Jr", 2000, 7, Posicion.DELANTERO, 73.0f, 1.76f, selBra);
            gestoraDelegaciones.inscribirJugador("Alisson Becker", 1992, 1, Posicion.ARQUERO, 91.0f, 1.91f, selBra);


            // 3. ORGANIZACIÓN DEPORTIVA: Fases y Grupos

            Fase faseGrupos = new Fase(NombreFase.GRUPOS);

            Grupo grupoA = gestoraDeportiva.configurarGrupo("A", "Grupo A - Primera Fase", faseGrupos);
            grupoA.asociarSeleccion(selArg);
            grupoA.asociarSeleccion(selBra);


            // 4. PARTIDOS Y PARTICIPACIONES

            Participacion partLocalArg = new Participacion(true, selArg);
            Participacion partVisitaBra = new Participacion(false, selBra);

            // Vinculamos cada participacion con su seleccion (necesario para la
            // tabla de posiciones y los resultados por seleccion)
            selArg.agregarParticipacion(partLocalArg);
            selBra.agregarParticipacion(partVisitaBra);

            // Se planifica el partido (Argentina vs Brasil)
            Partido partidoClasico = gestoraDeportiva.planificarPartido(
                    20260615, 1600, 90, 5,
                    monumental, faseGrupos, partLocalArg, partVisitaBra);

            // Se vincula el partido con su estadio (necesario para las
            // estadisticas de sedes)
            monumental.agregarPartido(partidoClasico);

            // 5. ÁRBITROS

            Arbitro arbitroPrin = new Arbitro("Pierluigi Collina", 1960, 20, paisArg);
            Arbitraje arbitraje = new Arbitraje(CategoriaArbitro.PRINCIPAL, arbitroPrin, partidoClasico);
            partidoClasico.agregarArbitraje(arbitraje);


            // 6. EVENTOS

            // Obtenemos a Messi
            Jugador messi = selArg.getJugadores().get(0);
            Evento golMessi = new Evento(TipoEvento.GOL, 23, messi);

            partidoClasico.agregarEvento(golMessi);
            messi.agregarEvento(golMessi);

        } catch (JugadorYaInscriptoException e) {
            System.out.println("Error durante la carga forzada: Hubo un jugador duplicado. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general en la carga inicial de datos: " + e.getMessage());
        }
    }
}