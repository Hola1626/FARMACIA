package pe.org.farmanova.moduloatencion.service;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.business.entities.DetalleVenta;
import pe.org.farmanova.moduloatencion.business.entities.Medicamento;
import pe.org.farmanova.moduloatencion.business.entities.Usuario;
import pe.org.farmanova.moduloatencion.business.entities.Venta;
import pe.org.farmanova.moduloatencion.business.managers.VentaManager;

public class VentaService {

    private static final VentaManager manager = new VentaManager();

    public static Venta crearVenta(
            Cliente cliente,
            Usuario cajero) {

        return manager.crearVenta(cliente, cajero);

    }

    public static void finalizarVenta(Venta venta)
            throws BusinessException {

        manager.finalizarVenta(venta);

    }

    public static void anularVenta(Venta venta)
            throws BusinessException {

        manager.anularVenta(venta);

    }

    public static Venta buscarVenta(int idVenta)
            throws BusinessException {

        return manager.buscarVenta(idVenta);

    }

    public static void agregarDetalle(Venta venta,
            Medicamento medicamento,
            int cantidad)
            throws BusinessException {

        if (cantidad <= 0) {
            throw new BusinessException("Cantidad inválida.");
        }

        if (cantidad > medicamento.getStock()) {
            throw new BusinessException("Stock insuficiente.");
        }

        // Verificar si el medicamento ya está en la venta
        for (DetalleVenta detalle : venta.getDetalles()) {

            if (detalle.getMedicamento().getId() == medicamento.getId()) {

                // Validar que el nuevo total no supere el stock
                int nuevaCantidad = detalle.getCantidad() + cantidad;

                if (nuevaCantidad > medicamento.getStock()) {
                    throw new BusinessException("Stock insuficiente.");
                }

                detalle.setCantidad(nuevaCantidad);

                venta.calcularTotal();

                return;
            }

        }

        // Si no existe, crear un nuevo detalle
        DetalleVenta detalle = new DetalleVenta(
                medicamento,
                cantidad
        );

        venta.agregarDetalle(detalle);

    }

    public static void quitarDetalle(
            Venta venta,
            int indice)
            throws BusinessException {

        manager.quitarDetalle(
                venta,
                indice
        );

    }

    public static List<Venta> listarVentas() {
        return manager.listarVentas();
    }

    public static int cantidadVentas() {
        return manager.cantidadVentas();
    }

    public static boolean estaVacio() {
        return manager.estaVacio();
    }

}
