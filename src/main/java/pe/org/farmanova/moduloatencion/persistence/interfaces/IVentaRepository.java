package pe.org.farmanova.moduloatencion.persistence.interfaces;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Venta;

public interface IVentaRepository {

    boolean insertar(Venta venta);

    Venta buscar(int idVenta);

    List<Venta> listar();

    int cantidad();

    boolean estaVacio();
}
