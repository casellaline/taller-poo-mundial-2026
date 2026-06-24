/**
 * Clase principal que arranca el sistema: crea el repositorio y las
 * gestoras, carga los datos de prueba y lanza el menu interactivo.
 */
public class Main {
    /**
     * Punto de entrada del programa.
     *
     * @param args args
     */
    public static void main(String[] args) {

        System.out.println("===================================");
        System.out.println("SISTEMA DE GESTION DEL MUNDIAL 2026");
        System.out.println("===================================");

        RepoMundial repositorio= new RepoMundial();

        GestionDelegaciones delegaciones = new GestionDelegaciones(
                repositorio.getPaises(),
                repositorio.getSelecciones(),
                repositorio.getArbitros()
        );

        OrganizacionDeportiva organizacionDeportiva = new OrganizacionDeportiva(
                repositorio.getPartidos(),
                repositorio.getGruposMundial());

        GestoraInfraestructura infraestructura = new GestoraInfraestructura(
                repositorio.getSedes()
        );

        RegistroEvento eventoNuevo = new RegistroEvento();

       GeneradorInformes informes = new GeneradorInformes(
                delegaciones,
                organizacionDeportiva);


        MenuMundial menuPrincipal = new MenuMundial(delegaciones, infraestructura,organizacionDeportiva, informes, eventoNuevo);

        menuPrincipal.iniciar();

        System.out.println("=====================");
        System.out.println("SISTEMA FINALIZADO.");
        System.out.println("=====================");
    }
}