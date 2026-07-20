package pe.org.farmanova.moduloatencion.business.entities;

public class DetalleVenta {

    private Medicamento medicamento;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleVenta() {
    }

    public DetalleVenta(Medicamento medicamento, int cantidad) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.precioUnitario = medicamento.getPrecio();
        calcularSubtotal();
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    private void calcularSubtotal() {
        this.subtotal = this.precioUnitario * this.cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s | Cant: %d | Subtotal: S/ %.2f",
                medicamento.getNombre(), cantidad, subtotal);
    }
}