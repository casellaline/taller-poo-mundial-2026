import java.util.List;
import java.util.Scanner;
/**
 * Menu interactivo de consola del sistema. Ofrece la gestion de datos
 * y el acceso a los distintos informes del Mundial.
 */
public class MenuMundial {
    private Scanner scanner;
    private GestionDelegaciones delegaciones;
    private GestoraInfraestructura infraestructura;
    private OrganizacionDeportiva orgDeportiva;
    private GeneradorInformes informes;
    private RegistroEvento registroEvento;

    // El constructor recibe las gestoras para poder usarlas
    /**
     * Crea una instancia de {@code MenuMundial} con los datos indicados.
     *
     * @param gd gd
     * @param gi gi
     * @param od od
     * @param inf inf
     * @param re re
     */
    public MenuMundial(GestionDelegaciones gd, GestoraInfraestructura gi ,OrganizacionDeportiva od,
                           GeneradorInformes inf, RegistroEvento re) {
        this.scanner = new Scanner(System.in);
        this.delegaciones = gd;
        this.infraestructura= gi;
        this.orgDeportiva = od;
        this.informes = inf;
        this.registroEvento = re;
    }

    //MENÚ PRINCIPAL
    /**
     * Inicia el bucle del menu principal hasta que el usuario decide salir.
     */
     public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--------------- MENU PRINCIPAL ---------------");
            System.out.println(" 1. Gestion de datos");
            System.out.println(" 2. Consultas y reportes");
            System.out.println(" 0. Salir");
            System.out.println("----------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        mostrarMenuGestion(); // Salta al submenú de gestión
                        break;
                    case 2:
                        mostrarMenuReportes(); // Salta al submenú de reportes
                        break;
                    case 0:
                        System.out.println("Cerrando el sistema.");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                    }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número.");
            }
        }
    }
    // SUBMENÚ: GESTIÓN DE DATOS
    /**
     * Muestra el submenu de gestion de datos.
     */
    public void mostrarMenuGestion() {
       boolean continuar = true;
       while (continuar) {
           System.out.println("\n------------ GESTION DE DATOS ------------");
           System.out.println(" 1. Cargar datos de prueba (hardcodeados)");
           System.out.println(" 2. Inscribir jugador");
           System.out.println(" 3. Asignar arbitraje a un partido ");
           System.out.println(" 4. Registrar evento en un partido");
           System.out.println(" 5. Registrar sede y estadio");
           System.out.println(" 6. Registrar pais");
           System.out.println(" 7. Registrar seleccion");
           System.out.println(" 8. Registrar arbitro");
           System.out.println(" 9. Planificar partido");
           System.out.println(" 10. Asignar director tecnico a una seleccion");
           System.out.println(" 11. Asignar cuerpo tecnico a una seleccion");
           System.out.println(" 12. Configurar grupo");
           System.out.println(" 0. Volver al menu principal");
           System.out.println("------------------------------------------");
           System.out.print("Ingrese una opcion: ");

           try {
               int opcion = Integer.parseInt(scanner.nextLine());
               switch (opcion) {
                   case 1:
                       System.out.println(">> Datos iniciales cargados con éxito.");
                       InicializadorDatos.cargarDatosDePrueba(delegaciones, infraestructura, orgDeportiva);
                            break;
                   case 2:
                       System.out.println(">> Inscribir Jugador");
                       try {
                           System.out.print("Nombre del jugador: ");
                           String nombre = scanner.nextLine();

                           System.out.print("Año de nacimiento: ");
                           int anioNac = Integer.parseInt(scanner.nextLine());

                           System.out.print("Número de dorsal: ");
                           int dorsal = Integer.parseInt(scanner.nextLine());

                           System.out.print("Peso: ");
                           float peso = Float.parseFloat(scanner.nextLine());

                           System.out.print("Altura: ");
                           float altura = Float.parseFloat(scanner.nextLine());

                           System.out.print("Posición (ARQUERO, DEFENSOR, MEDIOCAMPISTA, DELANTERO): ");
                           Posicion posicion = Posicion.valueOf(scanner.nextLine().toUpperCase());

                           System.out.print("Nombre de la Selección destino: ");
                           String nombreSeleccion = scanner.nextLine();

                           Seleccion seleccionDestino = delegaciones.buscarSeleccionPorNombre(nombreSeleccion);

                           if (seleccionDestino == null) {
                               System.out.println("ERROR: No se encontró ninguna selección llamada '" + nombreSeleccion + "'.");
                           } else {

                               delegaciones.inscribirJugador(nombre, anioNac, dorsal, posicion, peso, altura, seleccionDestino);
                               System.out.println("Se inscribió al jugador con éxito en " + seleccionDestino.getNombreFederacion());
                           }
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: Ingresaste texto donde se esperaba un número.");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: La posición ingresada no existe.");
                       } catch (JugadorYaInscriptoException e) {
                           System.out.println("ERROR: " + e.getMessage());
                       }
                       break;
                   case 3:
                       System.out.println(">> Asignar Arbitraje");
                       try {

                           System.out.print("Categoría del Árbitro (PRINCIPAL, ASISTENTE, VAR): ");
                           CategoriaArbitro categoria = CategoriaArbitro.valueOf(scanner.nextLine().toUpperCase());


                           System.out.print("Nombre exacto del Árbitro: ");
                           String nombreArbitro = scanner.nextLine();

                           Arbitro arbitroDestino = delegaciones.buscarArbitroPorNombre(nombreArbitro);

                           if (arbitroDestino == null) {
                               System.out.println("ERROR: No se encontró ningún árbitro con ese nombre en el sistema.");
                               break; // Salimos de este case
                           }

                           System.out.print("Fecha del partido (Ej. 20260615): ");
                           int fechaPartido = Integer.parseInt(scanner.nextLine());

                           Partido partidoDestino = orgDeportiva.buscarPartidoPorFecha(fechaPartido);

                           if (partidoDestino == null) {
                               System.out.println("ERROR: No se encontró ningún partido planificado en esa fecha.");
                               break; // Salimos de este case
                           }

                           orgDeportiva.asignarArbitraje(categoria, arbitroDestino, partidoDestino);

                           System.out.println("¡Arbitraje (" + categoria + ") asignado al partido!");

                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: La fecha debe ser un número entero (Ej. 20260615).");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: La categoría ingresada no existe (Debe ser PRINCIPAL, ASISTENTE o VAR).");
                       } catch (PartidoSinArbitroPrincipalException e) {
                           System.out.println("ERROR: " + e.getMessage());
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;
                   case 4:
                       System.out.println(">> Registrar Evento");
                       try {
                           System.out.print("Fecha del partido (Ej. 20260615): ");
                           int fechaPartido = Integer.parseInt(scanner.nextLine());
                           Partido partidoDestino = orgDeportiva.buscarPartidoPorFecha(fechaPartido);

                           if (partidoDestino == null) {
                               System.out.println("ERROR: No se encontró ningún partido planificado en esa fecha.");
                               break; // Salimos de este case
                           }

                           System.out.print("Nombre del Jugador: ");
                           String nombreJugador = scanner.nextLine();


                           Jugador jugadorImplicado = delegaciones.buscarJugadorPorNombre(nombreJugador);

                           if (jugadorImplicado == null) {
                               System.out.println("ERROR: No se encontró ningún jugador con ese nombre.");
                               break;
                           }

                           System.out.print("Tipo de evento (GOL, TARJETA_AMARILLA, TARJETA_ROJA, CAMBIO, etc.): ");
                           TipoEvento tipo = TipoEvento.valueOf(scanner.nextLine().toUpperCase());

                           System.out.print("Minuto del suceso (Ej. 45): ");
                           int minuto = Integer.parseInt(scanner.nextLine());

                           // La gestora crea el Evento y valida que el jugador
                           // pertenezca al partido
                           orgDeportiva.registrarEvento(partidoDestino, tipo, minuto, jugadorImplicado);

                           System.out.println("El Evento de " + tipo + " en el minuto " + minuto + " fue registrado.");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: Debes ingresar números enteros para la fecha y el minuto.");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: El tipo de evento ingresado no existe.");
                       } catch (JugadorNoPerteneceAlPartidoException e) {
                           System.out.println("Error: " + e.getMessage());
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 5:
                       System.out.println(">> Registrar Sede y Estadio");
                       try {
                           // 1. Datos de la sede (ciudad)
                           System.out.print("Ciudad de la sede: ");
                           String ciudad = scanner.nextLine();

                           System.out.print("Altura sobre el nivel del mar (Ej. 25.0): ");
                           float altura = Float.parseFloat(scanner.nextLine());

                           System.out.print("Clima: ");
                           String clima = scanner.nextLine();

                           System.out.print("Zona horaria (Ej. UTC-3): ");
                           String zonaHoraria = scanner.nextLine();

                           Sede nuevaSede = infraestructura.registrarSede(ciudad, altura, clima, zonaHoraria);

                           // Datos del estadio asociado a esa sede
                           System.out.print("Nombre del estadio: ");
                           String nombreEstadio = scanner.nextLine();

                           System.out.print("Capacidad del estadio: ");
                           int capacidad = Integer.parseInt(scanner.nextLine());

                           infraestructura.registrarEstadio(nombreEstadio, capacidad, nuevaSede);

                           System.out.println("Se registro la sede '" + ciudad +
                                   "' con el estadio '" + nombreEstadio + "'.");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: La altura debe ser decimal y la capacidad un numero entero.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 6:
                       System.out.println(">> Registrar Pais");
                       try {
                           System.out.print("Nombre del pais (Ej. Argentina): ");
                           String nombrePais = scanner.nextLine();

                           System.out.print("Bandera / descripcion (Ej. Celeste y Blanca): ");
                           String bandera = scanner.nextLine();

                           delegaciones.registrarPais(nombrePais, bandera);

                           System.out.println("Se registro el pais '" + nombrePais + "'.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 7:
                       System.out.println(">> Registrar Seleccion");
                       try {
                           // La seleccion se asocia a un pais ya registrado
                           System.out.print("Nombre del pais al que pertenece: ");
                           String nombrePaisSel = scanner.nextLine();

                           Pais paisDestino = null;
                           for (Pais p : delegaciones.getPaisesRegistrados()) {
                               if (p.getNombre().equalsIgnoreCase(nombrePaisSel)) {
                                   paisDestino = p;
                                   break;
                               }
                           }

                           if (paisDestino == null) {
                               System.out.println("ERROR: No se encontro ningun pais llamado '" +
                                       nombrePaisSel + "'. Registrelo primero (opcion 6).");
                               break;
                           }

                           System.out.print("Federacion (Ej. AFA): ");
                           String federacion = scanner.nextLine();

                           System.out.print("Camiseta principal (Ej. Celeste y Blanca): ");
                           String camPrin = scanner.nextLine();

                           System.out.print("Camiseta suplente (Ej. Azul): ");
                           String camSec = scanner.nextLine();

                           System.out.print("Es cabeza de serie? (true/false): ");
                           boolean cabeza = Boolean.parseBoolean(scanner.nextLine().trim());

                           System.out.print("Ranking FIFA (Ej. 1): ");
                           int ranking = Integer.parseInt(scanner.nextLine());

                           delegaciones.registrarSeleccion(federacion, camPrin, camSec, cabeza, ranking, paisDestino);

                           System.out.println("Se registro la seleccion '" + federacion +
                                   "' para el pais '" + paisDestino.getNombre() + "'.");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: El ranking debe ser un numero entero.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 8:
                       System.out.println(">> Registrar Arbitro");
                       try {
                           // El arbitro se asocia a un pais ya registrado
                           System.out.print("Nombre del pais al que pertenece: ");
                           String nombrePaisArb = scanner.nextLine();

                           Pais paisArbitro = null;
                           for (Pais p : delegaciones.getPaisesRegistrados()) {
                               if (p.getNombre().equalsIgnoreCase(nombrePaisArb)) {
                                   paisArbitro = p;
                                   break;
                               }
                           }

                           if (paisArbitro == null) {
                               System.out.println("ERROR: No se encontro ningun pais llamado '" +
                                       nombrePaisArb + "'. Registrelo primero (opcion 6).");
                               break;
                           }

                           System.out.print("Nombre del arbitro: ");
                           String nombreArbitro = scanner.nextLine();

                           System.out.print("Año de nacimiento: ");
                           int anioNac = Integer.parseInt(scanner.nextLine());

                           System.out.print("Años de experiencia: ");
                           int experiencia = Integer.parseInt(scanner.nextLine());

                           Arbitro nuevoArbitro = delegaciones.crearArbitro(nombreArbitro, anioNac, experiencia, paisArbitro);

                           System.out.println("Se registro al arbitro '" + nombreArbitro + "'.");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: El anio y los anios de experiencia deben ser numeros enteros.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;
                   case 9:
                       System.out.println(">> Planificar Partido");
                       try {
                           System.out.print("Fecha del partido (Ej. 20260615): ");
                           int fecha = Integer.parseInt(scanner.nextLine().trim());

                           System.out.print("Horario (Ej. 1600): ");
                           int horario = Integer.parseInt(scanner.nextLine().trim());

                           System.out.print("Duración en minutos (Ej. 90): ");
                           int duracion = Integer.parseInt(scanner.nextLine().trim());

                           System.out.print("Tiempo adicional en minutos (Ej. 5): ");
                           int tiempoAdicional = Integer.parseInt(scanner.nextLine().trim());

                           System.out.print("Nombre del estadio: ");
                           String nombreEstadio = scanner.nextLine().trim();

                           // Buscar el estadio recorriendo las sedes
                           Estadio estadioDestino = null;
                           for (Sede sede : infraestructura.getSedes()) {
                               for (Estadio est : sede.getEstadios()) {
                                   if (est.getNombre().equalsIgnoreCase(nombreEstadio)) {
                                       estadioDestino = est;
                                       break;
                                   }
                               }
                               if (estadioDestino != null) break;
                           }

                           if (estadioDestino == null) {
                               System.out.println("ERROR: No se encontró ningún estadio con ese nombre.");
                               break;
                           }

                           System.out.print("Fase (Ej. GRUPOS, OCTAVOS, CUARTOS, SEMIFINAL, FINAL): ");
                           String nombreFase = scanner.nextLine().trim().toUpperCase();
                           Fase fasePartido = new Fase(NombreFase.valueOf(nombreFase));

                           System.out.print("Nombre de la Selección Local (Ej. Argentina): ");
                           Seleccion selLocal = delegaciones.buscarSeleccionPorNombre(scanner.nextLine().trim());
                           if (selLocal == null) {
                               System.out.println("ERROR: No se encontró la selección local.");
                               break;
                           }

                           System.out.print("Nombre de la Selección Visitante (Ej. Brasil): ");
                           Seleccion selVisita = delegaciones.buscarSeleccionPorNombre(scanner.nextLine().trim());
                           if (selVisita == null) {
                               System.out.println("ERROR: No se encontró la selección visitante.");
                               break;
                           }

                           // 1. Crear las participaciones (la gestora las crea y las vincula con su seleccion)
                           Participacion partLocal = orgDeportiva.crearParticipacion(true, selLocal);
                           Participacion partVisita = orgDeportiva.crearParticipacion(false, selVisita);

                           // 2. Planificar el partido en la gestora
                           Partido nuevoPartido = orgDeportiva.planificarPartido(fecha, horario, duracion, tiempoAdicional, estadioDestino, fasePartido, partLocal, partVisita);

                           // 3. Vincular el partido al estadio (vital para estadísticas de sede)
                           estadioDestino.agregarPartido(nuevoPartido);

                           System.out.println("¡Partido planificado con éxito entre " + selLocal.getNombreFederacion() + " y " + selVisita.getNombreFederacion() + "!");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: Los campos de fecha, horario, duración y tiempo adicional deben ser numéricos.");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: La fase ingresada no existe (Asegurate de escribirla igual que en el Enum NombreFase).");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 10:
                       System.out.println(">> Asignar Director Tecnico");
                       try {
                           System.out.print("Nombre de la Seleccion (Ej. AFA): ");
                           Seleccion selDt = delegaciones.buscarSeleccionPorNombre(scanner.nextLine().trim());
                           if (selDt == null) {
                               System.out.println("ERROR: No se encontro la seleccion indicada.");
                               break;
                           }

                           System.out.print("Nombre del director tecnico: ");
                           String nombreDt = scanner.nextLine();

                           System.out.print("Anio de nacimiento (Ej. 1978): ");
                           int anioNacDt = Integer.parseInt(scanner.nextLine());

                           System.out.print("Anio de nombramiento (Ej. 2018): ");
                           int anioNombramiento = Integer.parseInt(scanner.nextLine());

                           delegaciones.asignarDirectorTecnico(nombreDt, anioNacDt, anioNombramiento, selDt);
                           System.out.println("Se asigno el director tecnico '" + nombreDt +
                                   "' a " + selDt.getNombreFederacion() + ".");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: Los anios deben ser numeros enteros.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 11:
                       System.out.println(">> Asignar Cuerpo Tecnico");
                       try {
                           System.out.print("Nombre de la Seleccion (Ej. AFA): ");
                           Seleccion selCt = delegaciones.buscarSeleccionPorNombre(scanner.nextLine().trim());
                           if (selCt == null) {
                               System.out.println("ERROR: No se encontro la seleccion indicada.");
                               break;
                           }

                           System.out.print("Nombre del integrante: ");
                           String nombreCt = scanner.nextLine();

                           System.out.print("Anio de nacimiento (Ej. 1980): ");
                           int anioNacCt = Integer.parseInt(scanner.nextLine());

                           System.out.print("Rol (MEDICO, PREPARADOR_FISICO, KINESIOLOGO, NUTRICIONISTA, PSICOLOGO, etc.): ");
                           Rol rol = Rol.valueOf(scanner.nextLine().trim().toUpperCase());

                           delegaciones.asignarCuerpoTecnico(nombreCt, anioNacCt, rol, selCt);
                           System.out.println("Se asigno '" + nombreCt + "' (" + rol +
                                   ") al cuerpo tecnico de " + selCt.getNombreFederacion() + ".");
                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: El anio de nacimiento debe ser un numero entero.");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: El rol ingresado no existe.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 12:
                       System.out.println(">> Configurar Grupo");
                       try {
                           System.out.print("Identificacion del grupo (Ej. A): ");
                           String idGrupo = scanner.nextLine().trim();

                           System.out.print("Descripcion (Ej. Grupo A - Primera Fase): ");
                           String descGrupo = scanner.nextLine();

                           // El grupo pertenece a la fase de grupos
                           Fase faseGrupo = orgDeportiva.crearFase(NombreFase.GRUPOS);
                           Grupo nuevoGrupo = orgDeportiva.configurarGrupo(idGrupo, descGrupo, faseGrupo);

                           // Asociar selecciones al grupo (opcional, hasta que el usuario escriba 'fin')
                           System.out.println("Ingrese los nombres de las selecciones del grupo (escriba 'fin' para terminar):");
                           while (true) {
                               System.out.print("  Seleccion (o 'fin'): ");
                               String nombreSel = scanner.nextLine().trim();
                               if (nombreSel.equalsIgnoreCase("fin")) {
                                   break;
                               }
                               Seleccion sel = delegaciones.buscarSeleccionPorNombre(nombreSel);
                               if (sel == null) {
                                   System.out.println("  ERROR: No se encontro esa seleccion, intente de nuevo.");
                               } else {
                                   nuevoGrupo.asociarSeleccion(sel);
                                   System.out.println("  Agregada: " + sel.getNombreFederacion());
                               }
                           }
                           System.out.println("Se configuro el grupo '" + idGrupo + "'.");
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 0:
                       continuar = false;
                       break;

                   default:
                       System.out.println("Opción inválida.");
               }
           } catch (NumberFormatException e) {
               System.out.println("Error: Debe ingresar un número.");
           }
       }
    }

   //  SUBMENÚ: CONSULTAS Y REPORTES
    /**
     * Muestra el submenu de consultas y reportes.
     */

    public void mostrarMenuReportes() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n---------- CONSULTAS Y REPORTES ----------");
            System.out.println(" 1. Listar selecciones inscriptas");
            System.out.println(" 2. Tabla de posiciones por grupo");
            System.out.println(" 3. Resultados por seleccion");
            System.out.println(" 4. Ranking de goleadores");
            System.out.println(" 5. Informe disciplinario por seleccion o jugador");
            System.out.println(" 6. Ficha tecnica de partido");
            System.out.println(" 7. Estadisticas de sedes");
            System.out.println(" 0. Volver al menu principal");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- SELECCIONES INSCRIPTAS ---");
                        // Guardamos la lista en una variable temporal para validarla
                        List<Seleccion> listaSelecciones = delegaciones.getSeleccionesInscriptas();


                        if (listaSelecciones.isEmpty()) {
                        System.out.println("No hay selecciones inscriptas por el momento.");

                        } else {

                            for(Seleccion s : listaSelecciones){
                                System.out.println("- " + s.getPais().getNombre());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n>> Tabla de posiciones por grupo");
                        System.out.print("Ingrese el nombre del grupo (Ej. 'A'): ");
                        String nombreGrupo = scanner.nextLine().trim(); // .trim() quita espacios accidentales

                        Grupo grupoEncontrado = null;
                        for (Grupo g : orgDeportiva.getGruposMundial()) {
                            if (g.getIdentificacion().equalsIgnoreCase(nombreGrupo)) {
                                grupoEncontrado = g;
                                break;
                            }
                        }

                        if (grupoEncontrado == null) {
                            System.out.println("ERROR: No se encontró ningún grupo con la letra '" + nombreGrupo + "'.");
                        } else {
                            List<RegistroPosicion> tabla = informes.tablaPosicionesPorGrupo(grupoEncontrado);

                            System.out.println("\n--- POSICIONES GRUPO " + nombreGrupo.toUpperCase() + " ---");

                            if (tabla.isEmpty()) {
                                System.out.println("Aún no hay selecciones asignadas a este grupo.");
                            } else {
                                int posicion = 1;
                                for (RegistroPosicion rp : tabla) {
                                    System.out.println(posicion + "° | " + rp.getSeleccion().getNombreFederacion() + " | Puntos: " + rp.getPuntos());
                                    posicion++;
                                }
                            }
                        }
                        break;
                    case 3:
                        System.out.println("\n>> Resultados por selección");
                        System.out.print("Ingrese el nombre de la selección (Ej. 'Argentina'): ");
                        String nombreSeleccion = scanner.nextLine().trim();

                        Seleccion seleccionEncontrada = null;
                        for (Seleccion s : delegaciones.getSeleccionesInscriptas()) {
                            if (s.getPais().getNombre().equalsIgnoreCase(nombreSeleccion)) {
                                seleccionEncontrada = s;
                                break;
                            }
                        }

                        if (seleccionEncontrada == null) {
                            System.out.println("ERROR: No se encontró ninguna selección con el nombre '" +
                                    nombreSeleccion + "'.");
                        } else {
                            System.out.println("\n--- RESULTADOS DE " +
                                    seleccionEncontrada.getNombreFederacion().toUpperCase() + " ---");

                            List<String> listaResultados = informes.resultadosPorSeleccion(seleccionEncontrada);

                            if (listaResultados.isEmpty()) {
                                System.out.println("Esta selección aún no ha disputado ningún partido.");
                            } else {
                                for (String resultado : listaResultados) {
                                    System.out.println(resultado);
                                }
                            }
                        }
                        break;
                    case 4:
                        System.out.println("\n--- RANKING DE GOLEADORES ---");
                        for(RegistroGoleador rg : informes.rankingGoleadores()) {
                            System.out.println(rg.getJugador().getNombre() + " - Goles: " + rg.getGoles());
                        }
                        break;
                    case 5:
                        System.out.println("\n>> Informe disciplinario");
                        System.out.println("  1. Por seleccion");
                        System.out.println("  2. Por jugador");
                        System.out.print("Elija una opcion: ");
                        String subOpcionDisc = scanner.nextLine().trim();

                        if (subOpcionDisc.equals("1")) {
                            System.out.print("Ingrese el nombre de la seleccion (Ej. 'Argentina'): ");
                            String nombreSelDisc = scanner.nextLine().trim();

                            Seleccion selDisciplina = null;
                            for (Seleccion s : delegaciones.getSeleccionesInscriptas()) {
                                if (s.getPais().getNombre().equalsIgnoreCase(nombreSelDisc)) {
                                    selDisciplina = s;
                                    break;
                                }
                            }

                            if (selDisciplina == null) {
                                System.out.println("ERROR: No se encontro ninguna seleccion con el nombre '" +
                                        nombreSelDisc + "'.");
                            } else {
                                System.out.println("\n" + informes.informeDisciplinarioSeleccion(selDisciplina));
                            }
                        } else if (subOpcionDisc.equals("2")) {
                            System.out.print("Ingrese el nombre del jugador (Ej. 'Lionel Messi'): ");
                            String nombreJugDisc = scanner.nextLine().trim();

                            Jugador jugadorDisc = delegaciones.buscarJugadorPorNombre(nombreJugDisc);

                            if (jugadorDisc == null) {
                                System.out.println("ERROR: No se encontro ningun jugador con el nombre '" +
                                        nombreJugDisc + "'.");
                            } else {
                                System.out.println("\n" + informes.informeDisciplinarioJugador(jugadorDisc));
                            }
                        } else {
                            System.out.println("ERROR: Opcion invalida.");
                        }
                        break;
                    case 6:
                        System.out.println("\n>> Ficha tecnica de partido");
                        System.out.print("Fecha del partido (Ej. 20260615): ");
                        try {
                            int fechaFicha = Integer.parseInt(scanner.nextLine());
                            Partido partidoFicha = orgDeportiva.buscarPartidoPorFecha(fechaFicha);
                            if (partidoFicha == null) {
                                System.out.println("ERROR: No se encontro ningun partido en esa fecha.");
                            } else {
                                System.out.println("\n" + informes.fichaTecnicaPartido(partidoFicha));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: La fecha debe ser un numero entero (Ej. 20260615).");
                        }
                        break;
                    case 7:
                        System.out.println("\n>> Estadisticas de sedes");
                        System.out.print("Ingrese el nombre de la ciudad (Ej. 'Buenos Aires'): ");
                        String nombreCiudad = scanner.nextLine().trim();

                        Sede sedeEncontrada = null;
                        for (Sede sede : infraestructura.getSedes()) {
                            if (sede.getCiudad().equalsIgnoreCase(nombreCiudad)) {
                                sedeEncontrada = sede;
                                break;
                            }
                        }

                        if (sedeEncontrada == null) {
                            System.out.println("ERROR: No se encontro ninguna sede en la ciudad '" +
                                    nombreCiudad + "'.");
                        } else {
                            System.out.println("\n--- ESTADISTICAS DE " + nombreCiudad.toUpperCase() + " ---");
                            System.out.println("Partidos jugados en la ciudad: " +
                                    informes.partidosEnCiudad(sedeEncontrada));
                            for (Estadio estadio : sedeEncontrada.getEstadios()) {
                                System.out.println("  - " + estadio.getNombre() + ": " +
                                        informes.partidosEnEstadio(estadio) + " partido(s)");
                            }
                        }
                        break;
                    case 0:
                        continuar = false; // Vuelve al menú principal
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número.");
            }
        }
    }
}

