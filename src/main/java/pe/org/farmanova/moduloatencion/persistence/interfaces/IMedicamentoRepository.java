package pe.org.farmanova.moduloatencion.persistence.interfaces;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Medicamento;

public interface IMedicamentoRepository {

    boolean insertar(Medicamento medicamento);

    Medicamento buscar(int id);

    boolean eliminar(int id);

    List<Medicamento> listar();

    int cantidad();

    boolean estaVacio();
}
