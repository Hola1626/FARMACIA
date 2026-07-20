package pe.org.farmanova.moduloatencion.persistence.interfaces;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Turno;

public interface ITurnoRepository {

    boolean insertarAHistorial(Turno turno);

    Turno siguienteTurno();

    Turno verSiguiente();

    Turno buscarPorCodigo(int codigo);

    Turno marcarNoVino();

    List<Turno> listarHistorial();

    List<Turno> listarCola();

    int cantidadPendientes();

    boolean estaVacio();

    boolean eliminarHistorial(int codigo);

    boolean eliminarDeCola(Turno turno);

}
