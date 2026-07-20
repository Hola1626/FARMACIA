package pe.org.farmanova.moduloatencion.business.entities;

import java.time.LocalDate;

public class Medicamento {

    private int id;
    private String nombre;
    private String laboratorio;
    private double precio;
    private int stock;
    private LocalDate fechaVencimiento;

    public Medicamento(int id, String nombre, String laboratorio,
                       double precio, int stock, LocalDate fechaVencimiento) {
        this.id = id;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.precio = precio;
        this.stock = stock;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | S/ %.2f | Stock: %d",
                id, nombre, precio, stock);
    }
}