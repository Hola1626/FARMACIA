
package pe.org.farmanova.moduloatencion.persistence.implementations;

import java.util.ArrayList;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Venta;
import pe.org.farmanova.moduloatencion.persistence.interfaces.IVentaRepository;

public class VentaRepositoryList implements IVentaRepository {

    private final ArrayList<Venta> ventas;

    public VentaRepositoryList() {
        ventas = new ArrayList<>();
    }

    @Override
    public boolean insertar(Venta venta) {

        if (buscar(venta.getIdVenta()) != null) {
            return false;
        }

        return ventas.add(venta);
    }

    @Override
    public Venta buscar(int idVenta) {

        for (Venta venta : ventas) {

            if (venta.getIdVenta() == idVenta) {
                return venta;
            }

        }

        return null;
    }

    @Override
    public List<Venta> listar() {
        return new ArrayList<>(ventas);
    }

    @Override
    public int cantidad() {
        return ventas.size();
    }

    @Override
    public boolean estaVacio() {
        return ventas.isEmpty();
    }

}