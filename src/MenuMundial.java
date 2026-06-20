import java.util.Scanner;
public class MenuMundial {
    private Scanner scanner;
    private GestionDelegaciones gestoraDelegaciones;
    private OrganizacionDeportiva gestoraDeportiva;
    private GeneradorInformes generadorInformes;
    private RegistroEvento registroEvento;

    // El constructor recibe las gestoras para poder usarlas
    public MenuMundial(GestionDelegaciones gd, OrganizacionDeportiva od,
                           GeneradorInformes gi, RegistroEvento re) {
        this.scanner = new Scanner(System.in);
        this.gestoraDelegaciones = gd;
        this.gestoraDeportiva = od;
        this.generadorInformes = gi;
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
                        System.out.println("Cerrando el sistema... ¡Éxitos en la entrega!");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                    }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Debe ingresar un número.");
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
           System.out.println(" 2. Inscribir jugador (con validacion de unicidad)");
           System.out.println(" 3. Asignar arbitraje a un partido (con validacion)");
           System.out.println(" 4. Registrar evento en un partido");
           System.out.println(" 0. Volver al menu principal");
           System.out.println("------------------------------------------");
           System.out.print("Ingrese una opcion: ");

           try {
               int opcion = Integer.parseInt(scanner.nextLine());
               switch (opcion) {
                   case 1:
                       System.out.println(">> Cargando datos iniciales...");
                       // Acá llamarías a tu InicializadorDatos.cargar(...)
                       System.out.println("¡Datos cargados con éxito!");
                            break;
                   case 2:
                       System.out.println(">> Inscribir Jugador");
                       // EJEMPLO DE CÓMO ATRAPAR TU EXCEPCIÓN:
                       try {
                           // Acá pedirías los datos con scanner: nombre, dorsal, etc.
                           // y luego llamas a la gestora:
                           // gestoraDelegaciones.inscribirJugador(nombre, ..., seleccion);
                           System.out.println("Funcionalidad en construcción...");
                       } catch (Exception e) { // Cambiar a JugadorYaInscriptoException
                           System.out.println("❌ ERROR: " + e.getMessage());
                       }
                       break;

                   case 3:
                       System.out.println(">> Asignar Arbitraje (En construcción...)");
                       break;

                   case 4:
                       System.out.println(">> Registrar Evento");
                       try {
                           // Llamarías a: registroEvento.registrarEvento(...)
                           System.out.println("Funcionalidad en construcción...");
                       } catch (Exception e) { // Cambiar a JugadorNoPerteneceAlPartidoException
                           System.out.println("❌ ERROR: " + e.getMessage());
                       }
                       break;

                   case 0:
                       continuar = false; // Rompe este bucle y vuelve al Main
                       break;

                   default:
                       System.out.println("⚠️ Opción inválida.");
               }
           } catch (NumberFormatException e) {
               System.out.println("⚠️ Error: Debe ingresar un número.");
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
                    case 1:
                        System.out.println("\n--- SELECCIONES INSCRIPTAS ---");
                        // Recorremos la lista de la gestora e imprimimos
                        for(Seleccion s : gestoraDelegaciones.getSeleccionesInscriptas()){
                            System.out.println("- " + s.getNombreFederacion());
                        }
                        break;

                    case 2:
                        System.out.println(">> Tabla de posiciones (En construcción...)");
                        // Pedir string del grupo, buscar el objeto Grupo y llamar a generadorInformes
                        break;

                    case 4:
                        System.out.println("\n--- RANKING DE GOLEADORES ---");
                        for(RegistroGoleador rg : generadorInformes.rankingGoleadores()) {
                            System.out.println(rg.getJugador().getNombre() + " - Goles: " + rg.getGoles());
                        }
                        break;

                    case 6:
                        System.out.println(">> Ficha técnica de partido (En construcción...)");
                        // generadorInformes.fichaTecnicaPartido(partido);
                        break;

                        // ... (completa el resto de los cases con los métodos de GeneradorInformes) ...

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

