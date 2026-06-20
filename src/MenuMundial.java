import java.util.List;
import java.util.Scanner;
public class MenuMundial {
    private Scanner scanner;
    private GestionDelegaciones delegaciones;
    private GestoraInfraestructura infraestructura;
    private OrganizacionDeportiva orgDeportiva;
    private GeneradorInformes informes;
    private RegistroEvento registroEvento;

    // El constructor recibe las gestoras para poder usarlas
    public MenuMundial(GestionDelegaciones gd, GestoraInfraestructura gi ,OrganizacionDeportiva od,
                           GeneradorInformes inf, RegistroEvento re) {
        this.scanner = new Scanner(System.in);
        this.delegaciones = gd;
        this.infraestructura= gi;
        this.orgDeportiva = od;
        this.informes = inf;
        this.registroEvento = re;
    }

    // ==========================================================
    // 1. MENÚ PRINCIPAL
    // ==========================================================
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
    // ==========================================================
    // 2. SUBMENÚ: GESTIÓN DE DATOS
    // ==========================================================
    public void mostrarMenuGestion() {
       boolean continuar = true;
       while (continuar) {
           System.out.println("\n------------ GESTION DE DATOS ------------");
           System.out.println(" 1. Cargar datos de prueba (hardcodeados)");
           System.out.println(" 2. Inscribir jugador");
           System.out.println(" 3. Asignar arbitraje a un partido ");
           System.out.println(" 4. Registrar evento en un partido");
           System.out.println(" 0. Volver al menu principal");
           System.out.println("------------------------------------------");
           System.out.print("Ingrese una opcion: ");

           try {
               int opcion = Integer.parseInt(scanner.nextLine());
               switch (opcion) {
                   case 1:
                       System.out.println(">> Cargando datos iniciales...");
                       InicializadorDatos.cargarDatosDePrueba(delegaciones, infraestructura, orgDeportiva);
                       System.out.println("¡Datos cargados con éxito!");
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

                           System.out.print("Nombre de la Selección destino (Ej. Argentina): ");
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
                           // 1. Pedimos la Categoría y la pasamos a mayúsculas
                           System.out.print("Categoría del Árbitro (PRINCIPAL, ASISTENTE, VAR): ");
                           CategoriaArbitro categoria = CategoriaArbitro.valueOf(scanner.nextLine().toUpperCase());

                           // 2. Pedimos el nombre del árbitro para buscarlo en la Gestora de Delegaciones
                           System.out.print("Nombre exacto del Árbitro: ");
                           String nombreArbitro = scanner.nextLine();

                           // ⚠️ Asegúrate de que este método exista en GestionDelegaciones
                           Arbitro arbitroDestino = delegaciones.buscarArbitroPorNombre(nombreArbitro);

                           if (arbitroDestino == null) {
                               System.out.println("ERROR: No se encontró ningún árbitro con ese nombre en el sistema.");
                               break; // Salimos de este case
                           }

                           // 3. Pedimos la fecha para buscar el partido en la Organización Deportiva
                           System.out.print("Fecha del partido (Ej. 20260615): ");
                           int fechaPartido = Integer.parseInt(scanner.nextLine());

                           Partido partidoDestino = orgDeportiva.buscarPartidoPorFecha(fechaPartido);

                           if (partidoDestino == null) {
                               System.out.println("ERROR: No se encontró ningún partido planificado en esa fecha.");
                               break; // Salimos de este case
                           }

                           // 4. Lógica de negocio estricta: Validar que haya un PRINCIPAL antes de asignar ASISTENTES
                           if (categoria != CategoriaArbitro.PRINCIPAL) {
                               // Usamos el método que me pasaste en tu código
                               orgDeportiva.validarArbitroPrincipal(partidoDestino);
                           }

                           // 5. Si no saltó la excepción, instanciamos el arbitraje y lo agregamos
                           Arbitraje nuevoArbitraje = new Arbitraje(categoria, arbitroDestino, partidoDestino);
                           partidoDestino.agregarArbitraje(nuevoArbitraje);

                           System.out.println("¡Arbitraje (" + categoria + ") asignado al partido!");

                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: La fecha debe ser un número entero (Ej. 20260615).");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: La categoría ingresada no existe (Debe ser PRINCIPAL, ASISTENTE o VAR).");
                       } catch (PartidoSinArbitroPrincipalException e) {
                           // ¡Aquí atrapamos la excepción que definiste en la Gestora!
                           System.out.println("ERROR: " + e.getMessage());
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;
                   case 4:
                       System.out.println(">> Registrar Evento");
                       try {
                           // 1. Pedimos la fecha para buscar el Partido
                           System.out.print("Fecha del partido (Ej. 20260615): ");
                           int fechaPartido = Integer.parseInt(scanner.nextLine());
                           Partido partidoDestino = orgDeportiva.buscarPartidoPorFecha(fechaPartido);

                           if (partidoDestino == null) {
                               System.out.println("ERROR: No se encontró ningún partido planificado en esa fecha.");
                               break; // Salimos de este case
                           }

                           // 2. Pedimos el nombre para buscar al Jugador involucrado
                           System.out.print("Nombre del Jugador: ");
                           String nombreJugador = scanner.nextLine();


                           Jugador jugadorImplicado = delegaciones.buscarJugadorPorNombre(nombreJugador);

                           if (jugadorImplicado == null) {
                               System.out.println("ERROR: No se encontró ningún jugador con ese nombre.");
                               break; // Salimos de este case
                           }

                           // 3. Pedimos el tipo de evento y lo pasamos a Enum
                           System.out.print("Tipo de evento (GOL, TARJETA_AMARILLA, TARJETA_ROJA, CAMBIO, etc.): ");
                           TipoEvento tipo = TipoEvento.valueOf(scanner.nextLine().toUpperCase());

                           // 4. Pedimos el minuto exacto
                           System.out.print("Minuto del suceso (Ej. 45): ");
                           int minuto = Integer.parseInt(scanner.nextLine());

                           // 5. Instanciamos el Evento (asumo tu constructor de Evento)
                           Evento nuevoEvento = new Evento(tipo, minuto, jugadorImplicado);

                           // 6. ¡Magia del encapsulamiento!
                           // El método agregarEvento() en Partido es el que debe tener el 'throw' si el jugador no juega ese partido.
                           partidoDestino.agregarEvento(nuevoEvento);

                           System.out.println("El Evento de " + tipo + " en el minuto " + minuto + " fue registrado.");

                       } catch (NumberFormatException e) {
                           System.out.println("ERROR: Debes ingresar números enteros para la fecha y el minuto.");
                       } catch (IllegalArgumentException e) {
                           System.out.println("ERROR: El tipo de evento ingresado no existe.");
                       } catch (JugadorNoPerteneceAlPartidoException e) {
                           // ¡Capturamos tu excepción personalizada!
                           System.out.println("Error: " + e.getMessage());
                       } catch (Exception e) {
                           System.out.println("ERROR INESPERADO: " + e.getMessage());
                       }
                       break;

                   case 0:
                       continuar = false; // Rompe este bucle y vuelve al Main
                       break;

                   default:
                       System.out.println("⚠Opción inválida.");
               }
           } catch (NumberFormatException e) {
               System.out.println("Error: Debe ingresar un número.");
           }
       }
    }

    // ==========================================================
    // 3. SUBMENÚ: CONSULTAS Y REPORTES
    // ==========================================================
    public void mostrarMenuReportes() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n---------- CONSULTAS Y REPORTES ----------");
            System.out.println(" 1. Listar selecciones inscriptas");
            System.out.println(" 2. Tabla de posiciones por grupo");
            System.out.println(" 3. Resultados por seleccion");
            System.out.println(" 4. Ranking de goleadores");
            System.out.println(" 5. Informe disciplinario por seleccion");
            System.out.println(" 6. Ficha tecnica de partido");
            System.out.println(" 7. Estadisticas de sedes");
            System.out.println(" 0. Volver al menu principal");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    System.out.println("\n--- SELECCIONES INSCRIPTAS ---");

                    // Guardamos la lista en una variable temporal para validarla
                    List<Seleccion> listaSelecciones = delegaciones.getSeleccionesInscriptas();

                    if (listaSelecciones.isEmpty()) {
                        System.out.println("No hay selecciones inscriptas por el momento.");
                    } else {
                        // Recorremos la lista de la gestora e imprimimos
                        for(Seleccion s : listaSelecciones){
                            System.out.println("- " + s.getNombreFederacion());
                        }
                    }
                    break;

                    case 2:
                        System.out.println("\n>> Tabla de posiciones por grupo");
                        System.out.print("Ingrese el nombre del grupo (Ej. 'A'): ");
                        String nombreGrupo = scanner.nextLine().trim(); // .trim() quita espacios accidentales

                        // 1. Buscamos el objeto Grupo en la gestora deportiva
                        // (Recorremos la lista usando el método que ya vi que tienes en GeneradorInformes)
                        Grupo grupoEncontrado = null;
                        for (Grupo g : orgDeportiva.getGruposMundial()) {
                            // Asumo que tu clase Grupo tiene un getNombre(), ajusta si se llama distinto
                            if (g.getIdentificacion().equalsIgnoreCase(nombreGrupo)) {
                                grupoEncontrado = g;
                                break;
                            }
                        }

                        // 2. Verificamos si el grupo existe
                        if (grupoEncontrado == null) {
                            System.out.println("ERROR: No se encontró ningún grupo con la letra '" + nombreGrupo + "'.");
                        } else {
                            // 3. Llamamos al generador de informes y guardamos la tabla resultante
                            List<RegistroPosicion> tabla = informes.tablaPosicionesPorGrupo(grupoEncontrado);

                            System.out.println("\n--- POSICIONES GRUPO " + nombreGrupo.toUpperCase() + " ---");

                            // Validamos visualmente por si el grupo está vacío
                            if (tabla.isEmpty()) {
                                System.out.println("Aún no hay selecciones asignadas a este grupo.");
                            } else {
                                // 4. Recorremos la tabla e imprimimos los datos
                                int posicion = 1;
                                for (RegistroPosicion rp : tabla) {
                                    // Ajusta getSeleccion() y getNombreFederacion() si se llaman distinto
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

                        // 1. Buscamos la selección
                        Seleccion seleccionEncontrada = null;
                        for (Seleccion s : delegaciones.getSeleccionesInscriptas()) {
                            if (s.getNombreFederacion().equalsIgnoreCase(nombreSeleccion)) {
                                seleccionEncontrada = s;
                                break;
                            }
                        }

                        // 2. Verificamos si existe
                        if (seleccionEncontrada == null) {
                            System.out.println("ERROR: No se encontró ninguna selección con el nombre '" +
                                    nombreSeleccion + "'.");
                        } else {
                            System.out.println("\n--- RESULTADOS DE " +
                                    seleccionEncontrada.getNombreFederacion().toUpperCase() + " ---");

                            // 3. ¡Le pedimos la información al Generador de Informes!
                            List<String> listaResultados = informes.puntajeTotalSeleccion(seleccionEncontrada);

                            // 4. Mostramos los resultados
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
                        System.out.println("\nInforme disciplinario por seleccion");
                    case 6:
                        System.out.println(">> Ficha técnica de partido");
                        //informes.fichaTecnicaPartido(partido);
                        break;
                    case 7:
                        System.out.println("\n");
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

