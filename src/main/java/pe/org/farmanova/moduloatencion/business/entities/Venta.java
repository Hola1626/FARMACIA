package pe.org.farmanova.moduloatencion.business.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.enums.EstadoVenta;

public class Venta {

    private int idVenta;
    private Cliente cliente;
    private Usuario cajero;
    private final LocalDateTime fechaHora;
    private final List<DetalleVenta> detalles;
    private double total;
    private EstadoVenta estado;

    public Venta() {
        this.detalles = new ArrayList<>();
        this.fechaHora = LocalDateTime.now();
        this.estado = EstadoVenta.PENDIENTE;
    }

    public Venta(int idVenta, Cliente cliente, Usuario cajero) {
        this();
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.cajero = cajero;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Usuario getCajero() {
        return cajero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    /*Estos metodos siguiendo un patron de programacion si deben estar aqui
    porque es la clase venta quien conoce todos los datos del detalle
    Es decir, es la experta en informacion del detalle venta.
     */
    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        calcularTotal();
    }

    public void quitarDetalle(int indice) {
        detalles.remove(indice);
        calcularTotal();
    }

    public void calcularTotal() {

        total = 0;

        for (DetalleVenta detalle : detalles) {
            total += detalle.getSubtotal();
        }

    }

    @Override
    public String toString() {
        return String.format(
                "Venta #%d%nCliente: %s%nCajero: %s%nTotal: S/ %.2f%nEstado: %s%n",
                idVenta,
                cliente,
                cajero.getUsuario(),
                total,
                estado
        );
    }
}
