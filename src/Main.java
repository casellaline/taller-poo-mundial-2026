public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando Sistema de Gestión del Mundial 2026...");
        System.out.println("Cargando módulos de memoria...\n");

        // ==========================================================
        // PASO 1: CREAR LA ÚNICA FUENTE DE VERDAD (El Repositorio)
        // ==========================================================
        // Aquí adentro nacen todas las List<> por única vez.
        RepoMundial repositorio= new RepoMundial();

        // ==========================================================
        // PASO 2: INYECTAR DEPENDENCIAS (Crear las Gestoras)
        // ==========================================================
        // Nacen las gestoras y les "enchufamos" las listas del repositorio

        GestionDelegaciones delegaciones = new GestionDelegaciones(
                repositorio.getPaises(),
                repositorio.getSelecciones()
        );

        OrganizacionDeportiva organizacionDeportiva = new OrganizacionDeportiva(
                repositorio.getPartidos(),
                repositorio.getGruposMundial());

        GestoraInfraestructura gestoraInfraestructura = new GestoraInfraestructura(
                repositorio.getSedes()
        );

        // ==========================================================
        // PASO 3: CREAR LOS SERVICIOS AUXILIARES
        // ==========================================================
        // RegistroEvento maneja los eventos en vivo
        RegistroEvento eventoNuevo = new RegistroEvento();

        // El Generador de Informes recibe a las gestoras para poder leer sus datos
        GeneradorInformes generadorInformes = new GeneradorInformes(
                delegaciones,
                organizacionDeportiva);

        // ==========================================================
        // PASO 4: CARGA DE DATOS INICIALES (Hardcodeados)
        // ==========================================================
        // Cumpliendo la consigna de la profesora: la carga se aísla en un método aparte.
        // InicializadorDatos.cargarDatosDePrueba(gestoraDelegaciones, gestoraInfraestructura, gestoraDeportiva);

        // ==========================================================
        // PASO 5: ARRANCAR EL MENÚ INTERACTIVO
        // ==========================================================
        // Le pasamos al menú todas las herramientas que necesita para trabajar
        MenuMundial menuPrincipal = new MenuMundial(
                gestoraDelegaciones,
                gestoraDeportiva,
                generadorInformes,
                registroEvento
        );

        // ¡Le damos play al sistema! El programa se quedará "atrapado"
        // adentro de este método hasta que el usuario elija salir (opción 0).
        menuPrincipal.iniciar();

        // Cuando el usuario elige salir en el menú, el código recién llega acá y termina.
        System.out.println("Ejecución finalizada correctamente.");
    }
}