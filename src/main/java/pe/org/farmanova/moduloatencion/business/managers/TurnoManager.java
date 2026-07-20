package pe.org.farmanova.moduloatencion.business.managers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.business.entities.Turno;
import pe.org.farmanova.moduloatencion.business.enums.EstadoTurno;
import pe.org.farmanova.moduloatencion.business.enums.PrioridadTurno;
import pe.org.farmanova.moduloatencion.persistence.implementations.TurnoRepositoryQueue;
import pe.org.farmanova.moduloatencion.service.BusinessException;

public class TurnoManager {

    private final TurnoRepositoryQueue repository;
    private int siguienteCodigo = 101;

    public TurnoManager() {
        repository = new TurnoRepositoryQueue();

    }

    public void registrarTurno(Cliente cliente,
            LocalDate fecha,
            LocalTime hora,
            PrioridadTurno prioridad)
            throws BusinessException {

        Turno turno = new Turno(
                siguienteCodigo++,
                cliente,
                fecha,
                hora,
                prioridad,
                EstadoTurno.PENDIENTE
        );

        if (!repository.insertarAHistorial(turno)) {
            throw new BusinessException("No se pudo registrar el turno en el historial.");
        }

        if (turno.getFecha().equals(LocalDate.now())) {

            if (!repository.insertarACola(turno)) {
                throw new BusinessException("No se pudo agregar el turno a la cola de atención.");
            }

        }

    }

    public void cargarTurnosDeHoy() {

        repository.limpiarCola();

        for (Turno turno : repository.listarHistorial()) {

            if (turno.getFecha().equals(LocalDate.now())
                    && turno.getEstado() == EstadoTurno.PENDIENTE) {

                repository.insertarACola(turno);

            }

        }

    }

    public Turno atenderSiguienteTurno()
            throws BusinessException {

        Turno turno = repository.siguienteTurno();

        if (turno == null) {
            throw new BusinessException("No hay turnos registrados.");
        }

        turno.setEstado(EstadoTurno.ATENDIDO);

        return turno;
    }

    public Turno verSiguienteTurno()
            throws BusinessException {

        Turno turno = repository.verSiguiente();

        if (turno == null) {
            throw new BusinessException("No hay turnos registrados.");
        }

        return turno;
    }

    public List<Turno> listarTurnos() {
        return repository.listarHistorial();
    }

    public List<Turno> listarCola() {
        return repository.listarCola();
    }

    public List<Turno> listarPorEstado(EstadoTurno estado) {

        List<Turno> resultado = new ArrayList<>();

        for (Turno t : repository.listarHistorial()) {

            if (t.getEstado() == estado) {
                resultado.add(t);
            }
        }

        return resultado;

    }

    public List<Turno> listarTurnosDelDia() {

        List<Turno> resultado = new ArrayList<>();

        for (Turno turno : repository.listarHistorial()) {

            if (turno.getFecha().equals(LocalDate.now())) {
                resultado.add(turno);
            }

        }

        return resultado;
    }

    public Turno marcarNoVino()
            throws BusinessException {

        Turno turno = repository.marcarNoVino();

        if (turno == null) {
            throw new BusinessException("No hay turnos pendientes.");
        }

        turno.setEstado(EstadoTurno.NO_VINO);

        return turno;
    }

    public Turno buscarPorCodigo(int codigo)
            throws BusinessException {

        Turno turno = repository.buscarPorCodigo(codigo);

        if (turno == null) {
            throw new BusinessException("No existe un turno con ese código.");
        }

        return turno;

    }

    public int cantidadTurnos() {
        return repository.cantidadPendientes();
    }

    public boolean estaVacio() {
        return repository.estaVacio();
    }

    public void eliminarTurno(int codigo)
            throws BusinessException {

        Turno turno = repository.buscarPorCodigo(codigo);

        if (turno == null) {
            throw new BusinessException("No existe un turno con ese código.");
        }

        if (turno.getEstado() != EstadoTurno.PENDIENTE) {
            throw new BusinessException(
                    "Solo se pueden eliminar turnos pendientes.");
        }

        if (!repository.eliminarDeCola(turno)) {
            throw new BusinessException(
                    "No se pudo eliminar el turno de la cola.");
        }

        if (!repository.eliminarHistorial(codigo)) {
            throw new BusinessException(
                    "No se pudo eliminar el turno.");
        }
    }

}
