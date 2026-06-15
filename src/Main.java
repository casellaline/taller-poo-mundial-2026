import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    // ══════════════════════════════ UTILIDADES ══════════════════════════════

    static void titulo(String t) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf( "║  %-48s║%n", t);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    static void seccion(String t) { System.out.println("\n  ── " + t + " ──"); }
    static void ok(String t)      { System.out.println("  [OK]    " + t); }
    static void fallo(String t)   { System.out.println("  [FALLO] " + t); }

    // ══════════════════════════════ VALIDACIONES ════════════════════════════

    /** Verifica que el jugador pertenezca a alguno de los equipos del partido. */
    static boolean jugadorParticipa(Partido partido, Jugador jugador) {
        if (partido.getEquipoLocal() == null || partido.getEquipoVisitante() == null) return false;
        Seleccion local     = partido.getEquipoLocal().getSeleccion();
        Seleccion visitante = partido.getEquipoVisitante().getSeleccion();
        return jugador.getSeleccion() == local || jugador.getSeleccion() == visitante;
    }

    /** Registra un evento solo si el jugador participa en el partido. */
    static void registrarEvento(Partido partido, TipoEvento tipo, int minuto, Jugador jugador) {
        if (!jugadorParticipa(partido, jugador)) {
            fallo("'" + jugador.getNombre() + "' no participa en este partido — evento rechazado.");
            return;
        }
        partido.agregarEvento(tipo, minuto, jugador);
        ok("Evento: " + tipo + " min." + minuto + "  →  " + jugador.getNombre());
    }

    /** Devuelve true si el partido tiene al menos un árbitro asignado. */
    static boolean tieneArbitrajeValido(Partido partido) {
        return partido.getArbitrajes() != null && !partido.getArbitrajes().isEmpty();
    }

    // ══════════════════════════════ INFORMES ════════════════════════════════

    /** Informe 1 — Tabla de posiciones de un grupo, ordenada por puntos. */
    static void tablaPosicionesGrupo(Grupo grupo) {
        titulo("TABLA DE POSICIONES — GRUPO " + grupo.getIdentificacion());
        List<Seleccion> sels = new ArrayList<>(grupo.getSelecciones());
        sels.sort((a, b) -> grupo.obtenerPuntos(b) - grupo.obtenerPuntos(a));
        System.out.printf("  %-4s %-22s %s%n", "Pos.", "Selección", "Puntos");
        System.out.println("  " + "─".repeat(36));
        int pos = 1;
        for (Seleccion s : sels) {
            System.out.printf("  %-4d %-22s %d%n", pos++, s.getNombreFederacion(), grupo.obtenerPuntos(s));
        }
    }

    /** Informe 2 — Resultados e instancias alcanzadas por una selección. */
    static void resultadosPorSeleccion(Seleccion seleccion) {
        titulo("RESULTADOS — " + seleccion.getNombreFederacion());
        int victorias = 0, empates = 0, derrotas = 0, gf = 0, gc = 0;
        for (Participacion part : seleccion.getParticipaciones()) {
            Partido p = part.getPartido();
            if (p == null) continue;
            Participacion rival = (p.getEquipoLocal() == part)
                    ? p.getEquipoVisitante() : p.getEquipoLocal();
            int gP = part.cantidadGoles();
            int gR = rival.cantidadGoles();
            gf += gP; gc += gR;
            String res;
            if      (gP > gR) { res = "VICTORIA"; victorias++; }
            else if (gP == gR) { res = "EMPATE";   empates++;   }
            else               { res = "DERROTA";  derrotas++;  }
            String cond = part.isEsLocal() ? "LOCAL" : "VISITANTE";
            String fase = p.getCorrespondeFase() != null ? p.getCorrespondeFase().getNombre().toString() : "?";
            System.out.printf("  [%s / %s]  %s %d – %d %s  →  %s%n",
                    cond, fase,
                    seleccion.getNombreFederacion(), gP, gR,
                    rival.getSeleccion().getNombreFederacion(), res);
        }
        System.out.println("  " + "─".repeat(50));
        System.out.printf("  V: %d  E: %d  D: %d   GF: %d  GC: %d  Dif: %+d%n",
                victorias, empates, derrotas, gf, gc, gf - gc);
    }

    /** Informe 3 — Ranking de goleadores del torneo. */
    static void rankingGoleadores(List<Partido> partidos) {
        titulo("RANKING DE GOLEADORES");
        Map<Jugador, Integer> goles = new LinkedHashMap<>();
        for (Partido p : partidos) {
            for (Evento e : p.getEventos()) {
                if (e.getTipo() == TipoEvento.GOL && e.getInvolucraJugador() != null)
                    goles.merge(e.getInvolucraJugador(), 1, Integer::sum);
            }
        }
        if (goles.isEmpty()) { System.out.println("  (Sin goles registrados)"); return; }
        List<Map.Entry<Jugador, Integer>> lista = new ArrayList<>(goles.entrySet());
        lista.sort((a, b) -> b.getValue() - a.getValue());
        System.out.printf("  %-4s %-24s %-6s %-10s%n", "Pos.", "Jugador", "Goles", "Selección");
        System.out.println("  " + "─".repeat(48));
        int pos = 1;
        for (Map.Entry<Jugador, Integer> e : lista) {
            String sel = e.getKey().getSeleccion() != null
                    ? e.getKey().getSeleccion().getNombreFederacion() : "—";
            System.out.printf("  %-4d %-24s %-6d %-10s%n",
                    pos++, e.getKey().getNombre(), e.getValue(), sel);
        }
    }

    /** Informe 4 — Tarjetas por jugador y selección. */
    static void informeDisciplinario(List<Partido> partidos) {
        titulo("INFORME DISCIPLINARIO");
        Map<Jugador, int[]> stats = new LinkedHashMap<>();
        for (Partido p : partidos) {
            for (Evento e : p.getEventos()) {
                Jugador j = e.getInvolucraJugador();
                if (j == null) continue;
                TipoEvento tipo = e.getTipo();
                if (tipo == TipoEvento.TARJETA_AMARILLA || tipo == TipoEvento.DOBLE_AMARILLA
                        || tipo == TipoEvento.TARJETA_ROJA) {
                    int[] t = stats.computeIfAbsent(j, k -> new int[2]);
                    if (tipo == TipoEvento.TARJETA_AMARILLA || tipo == TipoEvento.DOBLE_AMARILLA) t[0]++;
                    if (tipo == TipoEvento.TARJETA_ROJA) t[1]++;
                }
            }
        }
        if (stats.isEmpty()) { System.out.println("  (Sin sanciones)"); return; }
        System.out.printf("  %-24s %10s %7s  %-10s%n", "Jugador", "Amarillas", "Rojas", "Selección");
        System.out.println("  " + "─".repeat(56));
        for (Map.Entry<Jugador, int[]> entry : stats.entrySet()) {
            Jugador j = entry.getKey();
            String sel = j.getSeleccion() != null ? j.getSeleccion().getNombreFederacion() : "—";
            System.out.printf("  %-24s %10d %7d  %-10s%n",
                    j.getNombre(), entry.getValue()[0], entry.getValue()[1], sel);
        }
    }

    /** Informe 5 — Ficha técnica completa de un partido. */
    static void fichaTecnicaPartido(Partido partido) {
        titulo("FICHA TÉCNICA DEL PARTIDO");
        if (partido.getEquipoLocal() == null || partido.getEquipoVisitante() == null) {
            System.out.println("  Partido sin participaciones."); return;
        }
        Participacion loc = partido.getEquipoLocal();
        Participacion vis = partido.getEquipoVisitante();
        System.out.printf("%n  %s  %d – %d  %s%n",
                loc.getSeleccion().getNombreFederacion(), loc.cantidadGoles(),
                vis.cantidadGoles(), vis.getSeleccion().getNombreFederacion());
        if (partido.getSeDesarrolla() != null)
            System.out.println("  Estadio: " + partido.getSeDesarrolla().getNombre()
                    + "  (cap. " + partido.getSeDesarrolla().getCapacidad() + ")");
        if (partido.getCorrespondeFase() != null)
            System.out.println("  Fase: " + partido.getCorrespondeFase().getNombre());

        seccion("Árbitros");
        if (partido.getArbitrajes().isEmpty()) {
            System.out.println("  (ninguno asignado)");
        } else {
            for (Arbitraje a : partido.getArbitrajes())
                System.out.println("  " + a.getRol() + ": " + a.getArbitro().getNombre());
        }

        seccion("Alineación LOCAL — " + loc.getSeleccion().getNombreFederacion());
        for (Jugador j : loc.getSeleccion().getJugadores())
            System.out.printf("  #%-3d %-24s %s%n", j.getDorsal(), j.getNombre(), j.getPosicion());

        seccion("Alineación VISITANTE — " + vis.getSeleccion().getNombreFederacion());
        for (Jugador j : vis.getSeleccion().getJugadores())
            System.out.printf("  #%-3d %-24s %s%n", j.getDorsal(), j.getNombre(), j.getPosicion());

        seccion("Eventos");
        if (partido.getEventos().isEmpty()) {
            System.out.println("  (sin eventos)");
        } else {
            List<Evento> evs = new ArrayList<>(partido.getEventos());
            evs.sort(Comparator.comparingInt(Evento::getMinuto));
            for (Evento e : evs) {
                String jug = e.getInvolucraJugador() != null ? e.getInvolucraJugador().getNombre() : "—";
                System.out.printf("  min.%3d  %-22s  %s%n", e.getMinuto(), e.getTipo(), jug);
            }
        }
    }

    /** Informe 6 — Estadísticas de un estadio/sede. */
    static void estadisticasSede(Estadio estadio) {
        titulo("ESTADÍSTICAS — " + estadio.getNombre());
        String ciudad = estadio.getSede() != null ? estadio.getSede().getCiudad() : "—";
        System.out.println("  Ciudad: " + ciudad);
        System.out.println("  Capacidad: " + estadio.getCapacidad());
        System.out.println("  Partidos jugados: " + estadio.getPartidos().size());
        for (Partido p : estadio.getPartidos()) {
            if (p.getEquipoLocal() == null || p.getEquipoVisitante() == null) continue;
            System.out.printf("    →  %s  %d – %d  %s%n",
                    p.getEquipoLocal().getSeleccion().getNombreFederacion(),
                    p.getEquipoLocal().cantidadGoles(),
                    p.getEquipoVisitante().cantidadGoles(),
                    p.getEquipoVisitante().getSeleccion().getNombreFederacion());
        }
    }

    // ══════════════════════════════════ MAIN ════════════════════════════════

    public static void main(String[] args) {

        titulo("SISTEMA MUNDIAL 2026 — TEST COMPLETO");

        // ── 1. INFRAESTRUCTURA ───────────────────────────────────────────────
        titulo("1. INFRAESTRUCTURA: PAÍSES, SEDES Y ESTADIOS");

        Pais paisArg = new Pais("Argentina", "Celeste y Blanco");
        Pais paisBra = new Pais("Brasil",    "Verde y Amarilla");
        ok("Países: " + paisArg.getNombre() + ", " + paisBra.getNombre());

        Sede sedeBsAs = new Sede("Buenos Aires",    25.0f, "Templado", "GMT-3", paisArg);
        Sede sedeRio  = new Sede("Rio de Janeiro",  10.0f, "Tropical", "GMT-3", paisBra);
        paisArg.getSedes().add(sedeBsAs);
        paisBra.getSedes().add(sedeRio);
        ok("Sedes: " + sedeBsAs.getCiudad() + ", " + sedeRio.getCiudad());

        Estadio monumental = new Estadio("Monumental", 84000, sedeBsAs);
        Estadio maracana   = new Estadio("Maracaná",   78000, sedeRio);
        sedeBsAs.getEstadios().add(monumental);
        sedeRio.getEstadios().add(maracana);
        ok("Estadios: " + monumental.getNombre() + " (cap." + monumental.getCapacidad() + "), "
                + maracana.getNombre() + " (cap." + maracana.getCapacidad() + ")");

        // ── 2. DELEGACIONES ──────────────────────────────────────────────────
        titulo("2. DELEGACIONES: SELECCIONES, CUERPOS TÉCNICOS Y JUGADORES");

        GestionDelegaciones gestion = new GestionDelegaciones();

        Seleccion selArg = new Seleccion("AFA", "Celeste y Blanca", "Azul",    true, 1, paisArg, null);
        Seleccion selBra = new Seleccion("CBF", "Amarilla",         "Verde",   true, 3, paisBra, null);
        gestion.registrarPais(paisArg);
        gestion.registrarPais(paisBra);
        gestion.registrarNuevaSeleccion(selArg);
        gestion.registrarNuevaSeleccion(selBra);
        ok("Selecciones inscriptas: AFA, CBF");

        selArg.agregarDirectorTecnico(new DirectorTecnico("Lionel Scaloni",  19780516, 20180601));
        selBra.agregarDirectorTecnico(new DirectorTecnico("Dorival Junior",  19600603, 20240101));
        selArg.agregarCuerpoTecnico(new CuerpoTecnico("Walter Samuel",   19740223, Rol.AYUDANTE_CAMPO));
        selArg.agregarCuerpoTecnico(new CuerpoTecnico("Luis Martín",     19750101, Rol.PREPARADOR_FISICO));
        selBra.agregarCuerpoTecnico(new CuerpoTecnico("Cléber Xavier",   19700512, Rol.AYUDANTE_CAMPO));
        ok("DTs y cuerpos técnicos asignados");

        // Jugadores Argentina
        Jugador messi   = new Jugador("Lionel Messi",    19870624, 10, Posicion.DELANTERO,     72.5f, 1.70f, null);
        Jugador dimaria = new Jugador("Ángel Di María",  19880214, 11, Posicion.DELANTERO,     70.0f, 1.78f, null);
        Jugador depaul  = new Jugador("Rodrigo De Paul", 19940524,  7, Posicion.MEDIOCAMPISTA, 75.0f, 1.80f, null);
        Jugador romero  = new Jugador("Cristian Romero", 19980327, 13, Posicion.DEFENSOR,      84.0f, 1.85f, null);
        Jugador dibu    = new Jugador("Dibu Martínez",   19920902, 23, Posicion.ARQUERO,       88.0f, 1.95f, null);

        // Jugadores Brasil
        Jugador neymar   = new Jugador("Neymar Jr.",      19920205, 10, Posicion.DELANTERO,     68.0f, 1.75f, null);
        Jugador vinicius = new Jugador("Vinícius Jr.",    20000712,  7, Posicion.DELANTERO,     73.0f, 1.76f, null);
        Jugador casemiro = new Jugador("Casemiro",        19920223,  5, Posicion.MEDIOCAMPISTA, 84.0f, 1.85f, null);
        Jugador thiago   = new Jugador("Thiago Silva",    19840922,  3, Posicion.DEFENSOR,      79.0f, 1.83f, null);
        Jugador alisson  = new Jugador("Alisson Becker",  19920102,  1, Posicion.ARQUERO,       91.0f, 1.93f, null);

        seccion("Inscripción de jugadores");
        for (Jugador j : new Jugador[]{messi, dimaria, depaul, romero, dibu})
            { gestion.inscribirJugador(selArg, j); ok(j.getNombre() + " → AFA"); }
        for (Jugador j : new Jugador[]{neymar, vinicius, casemiro, thiago, alisson})
            { gestion.inscribirJugador(selBra, j); ok(j.getNombre() + " → CBF"); }

        seccion("Control: jugador no puede estar en dos selecciones");
        try {
            gestion.inscribirJugador(selBra, messi);
            fallo("Debería haber lanzado JugadorYaInscriptoException");
        } catch (JugadorYaInscriptoException e) {
            ok("Excepción capturada: " + e.getMessage());
        }

        seccion("Búsqueda de selección por nombre");
        Seleccion encontrada = gestion.buscarSeleccionPorNombre("AFA");
        if (encontrada != null) ok("Encontrada: " + encontrada.getNombreFederacion()
                + " (" + encontrada.getJugadores().size() + " jugadores)");
        else fallo("Selección 'AFA' no encontrada");

        // ── 3. ORGANIZACIÓN DEPORTIVA ────────────────────────────────────────
        titulo("3. ORGANIZACIÓN: FASES, GRUPOS Y PARTIDOS");

        Fase faseGrupos = new Fase(NombreFase.GRUPOS);
        Grupo grupoA    = new Grupo("A", "Grupo A", faseGrupos);
        grupoA.asociarSeleccion(selArg);
        grupoA.asociarSeleccion(selBra);
        faseGrupos.getGrupos().add(grupoA);
        ok("Fase de Grupos creada — Grupo A: AFA y CBF");

        Arbitro arbPrincipal = new Arbitro("Szymon Marciniak",    19810105, 15, paisArg);
        Arbitro arbAsist1    = new Arbitro("Tomasz Listkiewicz",  19850210, 10, paisBra);
        Arbitro arbVAR       = new Arbitro("Marco Di Bello",      19800303, 12, paisArg);
        ok("Árbitros creados: " + arbPrincipal.getNombre() + ", " + arbAsist1.getNombre() + ", " + arbVAR.getNombre());

        Partido clasico = new Partido();
        faseGrupos.asociarPartido(clasico);
        clasico.setFecha(20260615);
        clasico.setHorario(2000);
        clasico.setDuracion(90);
        clasico.setSeDesarrolla(monumental);
        monumental.getPartidos().add(clasico);

        clasico.agregarArbitraje(new Arbitraje(CategoriaArbitro.PRINCIPAL,    arbPrincipal, clasico));
        clasico.agregarArbitraje(new Arbitraje(CategoriaArbitro.ASISTENTE1,   arbAsist1,    clasico));
        clasico.agregarArbitraje(new Arbitraje(CategoriaArbitro.VAR_PRINCIPAL, arbVAR,       clasico));

        seccion("Control: partido debe tener árbitros asignados");
        if (tieneArbitrajeValido(clasico))
            ok("Árbitros asignados: " + clasico.getArbitrajes().size());
        else
            fallo("Partido sin árbitros válidos");

        Participacion partArg = new Participacion(true,  selArg);
        Participacion partBra = new Participacion(false, selBra);
        partArg.asociarPartido(clasico);
        partBra.asociarPartido(clasico);
        selArg.agregarParticipacion(partArg);
        selBra.agregarParticipacion(partBra);
        clasico.setEquipoLocal(partArg);
        clasico.setEquipoVisitante(partBra);
        ok("Partido: AFA (local) vs CBF (visitante)  |  Estadio: " + monumental.getNombre());

        // ── 4. REGISTRO DE EVENTOS ───────────────────────────────────────────
        titulo("4. REGISTRO DE EVENTOS DE CAMPO");

        seccion("Eventos válidos");
        registrarEvento(clasico, TipoEvento.GOL,              15, messi);
        registrarEvento(clasico, TipoEvento.GOL,              32, dimaria);
        registrarEvento(clasico, TipoEvento.TARJETA_AMARILLA, 45, casemiro);
        registrarEvento(clasico, TipoEvento.GOL,              60, neymar);
        registrarEvento(clasico, TipoEvento.TARJETA_ROJA,     75, vinicius);
        registrarEvento(clasico, TipoEvento.GOL,              88, messi);

        seccion("Control: evento con jugador ajeno al partido (debe rechazarse)");
        Jugador externo = new Jugador("Mbappé", 19981220, 10, Posicion.DELANTERO, 75.0f, 1.78f, null);
        registrarEvento(clasico, TipoEvento.GOL, 80, externo);

        seccion("Control: vínculo bidireccional jugador ↔ evento");
        ok("Eventos en lista de Messi: " + messi.getEventos().size()
                + "  (esperado: 2 goles via agregarEvento)");
        ok("Eventos totales en el partido: " + clasico.getEventos().size());

        // ── 5. INFORMES ──────────────────────────────────────────────────────
        titulo("5. INFORMES FINALES");

        List<Partido> todosPartidos = new ArrayList<>();
        todosPartidos.add(clasico);

        tablaPosicionesGrupo(grupoA);
        resultadosPorSeleccion(selArg);
        resultadosPorSeleccion(selBra);
        rankingGoleadores(todosPartidos);
        informeDisciplinario(todosPartidos);
        fichaTecnicaPartido(clasico);
        estadisticasSede(monumental);

        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  Test completo finalizado.");
        System.out.println("══════════════════════════════════════════════════");
    }
}
