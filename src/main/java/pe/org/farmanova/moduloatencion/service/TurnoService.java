package pe.org.farmanova.moduloatencion.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.business.entities.Turno;
import pe.org.farmanova.moduloatencion.business.enums.EstadoTurno;
import pe.org.farmanova.moduloatencion.business.enums.PrioridadTurno;
import pe.org.farmanova.moduloatencion.business.managers.TurnoManager;

public class TurnoService {

    private static final TurnoManager manager = new TurnoManager();

    public static void registrarTurno(Cliente cliente,
            LocalDate fecha,
            LocalTime hora,
            PrioridadTurno prioridad
    )
            throws BusinessException {

        manager.registrarTurno(cliente,
                fecha,
                hora,
                prioridad
        );
    }

    public static Turno atenderSiguienteTurno()
            throws BusinessException {

        return manager.atenderSiguienteTurno();
    }

    public static Turno verSiguienteTurno()
            throws BusinessException {

        return manager.verSiguienteTurno();
    }

    public static List<Turno> listarTurnos() {
        return manager.listarTurnos();
    }

    public static List<Turno> listarPorEstado(EstadoTurno estado) {
        return manager.listarPorEstado(estado);
    }

    public static List<Turno> listarCola() {
        return manager.listarCola();
    }

    public static List<Turno> listarTurnosDelDia() {

        return manager.listarTurnosDelDia();

    }

    public static int cantidadTurnos() {
        return manager.cantidadTurnos();
    }

    public static boolean estaVacio() {
        return manager.estaVacio();
    }

    public static void cargarTurnosDeHoy() {
        manager.cargarTurnosDeHoy();
    }

    public static Turno marcarNoVino()
            throws BusinessException {

        return manager.marcarNoVino();
    }

    public static Turno buscarPorCodigo(int codigo)
            throws BusinessException {

        return manager.buscarPorCodigo(codigo);
    }

    public static void eliminarTurno(int codigo)
            throws BusinessException {

        manager.eliminarTurno(codigo);
    }

}
