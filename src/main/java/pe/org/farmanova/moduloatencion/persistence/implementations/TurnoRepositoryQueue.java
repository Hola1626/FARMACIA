package pe.org.farmanova.moduloatencion.persistence.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import pe.org.farmanova.moduloatencion.business.entities.Turno;
import pe.org.farmanova.moduloatencion.persistence.interfaces.ITurnoRepository;

public class TurnoRepositoryQueue implements ITurnoRepository {

    private final PriorityQueue<Turno> colaTurnos;
    private final ArrayList<Turno> historialTurnos;

    public TurnoRepositoryQueue() {
        colaTurnos = new PriorityQueue<>();
        historialTurnos = new ArrayList<>();
    }

    public boolean insertarACola(Turno turno) {
        return colaTurnos.offer(turno);
    }

    @Override
    public boolean insertarAHistorial(Turno turno) {
        return historialTurnos.add(turno);
    }

    @Override
    public Turno siguienteTurno() {
        return colaTurnos.poll();
    }

    @Override
    public Turno verSiguiente() {
        return colaTurnos.peek();
    }

    @Override
    public Turno marcarNoVino() {
        return colaTurnos.poll();
    }

    @Override
    public List<Turno> listarHistorial() {
        return new ArrayList<>(historialTurnos);
    }

    @Override
    public List<Turno> listarCola() {
        return new ArrayList<>(colaTurnos);
    }

    @Override
    public Turno buscarPorCodigo(int codigo) {

        for (Turno turno : historialTurnos) {

            if (turno.getCodigo() == codigo) {
                return turno;
            }

        }

        return null;
    }

    public void limpiarCola() {
        colaTurnos.clear();
    }

    @Override
    public int cantidadPendientes() {
        return colaTurnos.size();
    }

    @Override
    public boolean estaVacio() {
        return colaTurnos.isEmpty();
    }

    @Override
    public boolean eliminarHistorial(int codigo) {
        Turno turno = buscarPorCodigo(codigo);

        if (turno == null) {
            return false;
        }

        return historialTurnos.remove(turno);
    }

    @Override
    public boolean eliminarDeCola(Turno turno) {
        return colaTurnos.remove(turno);
    }

}
