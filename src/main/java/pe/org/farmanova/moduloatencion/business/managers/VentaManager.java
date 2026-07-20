package pe.org.farmanova.moduloatencion.business.managers;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.business.entities.DetalleVenta;
import pe.org.farmanova.moduloatencion.business.entities.Medicamento;
import pe.org.farmanova.moduloatencion.business.entities.Usuario;
import pe.org.farmanova.moduloatencion.business.entities.Venta;
import pe.org.farmanova.moduloatencion.business.enums.EstadoVenta;
import pe.org.farmanova.moduloatencion.persistence.implementations.VentaRepositoryList;
import pe.org.farmanova.moduloatencion.service.BusinessException;

public class VentaManager {

    private final VentaRepositoryList repository;
    private int siguienteId = 1001;

    public VentaManager() {
        repository = new VentaRepositoryList();
    }

    public Venta crearVenta(Cliente cliente,
            Usuario cajero) {

        return new Venta(
                siguienteId++,
                cliente,
                cajero
        );

    }

    public void finalizarVenta(Venta venta)
            throws BusinessException {

        if (venta.getDetalles().isEmpty()) {
            throw new BusinessException("La venta no tiene productos.");
        }

        for (DetalleVenta detalle : venta.getDetalles()) {

            Medicamento medicamento = detalle.getMedicamento();

            medicamento.setStock(
                    medicamento.getStock() - detalle.getCantidad()
            );

        }

        venta.setEstado(EstadoVenta.PAGADA);

        if (!repository.insertar(venta)) {
            throw new BusinessException("No se pudo guardar la venta.");
        }

    }

    public void anularVenta(Venta venta)
            throws BusinessException {

        venta.setEstado(EstadoVenta.ANULADA);

    }

    public Venta buscarVenta(int idVenta)
            throws BusinessException {

        Venta venta = repository.buscar(idVenta);

        if (venta == null) {
            throw new BusinessException("Venta no encontrada.");
        }

        return venta;

    }

    public void agregarDetalle(Venta venta,
            Medicamento medicamento,
            int cantidad)
            throws BusinessException {

        if (cantidad <= 0) {
            throw new BusinessException("Cantidad inválida.");
        }

        if (cantidad > medicamento.getStock()) {
            throw new BusinessException("Stock insuficiente.");
        }

        DetalleVenta detalle
                = new DetalleVenta(medicamento, cantidad);

        venta.agregarDetalle(detalle);

    }

    public void quitarDetalle(Venta venta,
            int indice)
            throws BusinessException {

        if (venta.getDetalles().isEmpty()) {
            throw new BusinessException("La venta no tiene productos.");
        }

        if (indice < 0 || indice >= venta.getDetalles().size()) {
            throw new BusinessException("Seleccione un medicamento.");
        }

        venta.quitarDetalle(indice);

    }

    public List<Venta> listarVentas() {
        return repository.listar();
    }

    public int cantidadVentas() {
        return repository.cantidad();
    }

    public boolean estaVacio() {
        return repository.estaVacio();
    }

}
