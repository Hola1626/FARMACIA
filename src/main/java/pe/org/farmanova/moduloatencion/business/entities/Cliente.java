package pe.org.farmanova.moduloatencion.business.entities;

public class Cliente {

    private String dni;
    private String nombre;
    private String apellido;
    private String celular;

    public Cliente(String dni, String nombre, String apellido, String celular) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
    }

    

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    

    @Override
    public String toString() {
        return String.format(
                "DNI: %-10s | Nombre: %-18s | Apellido: %-18s | Celular: %-9s",
                dni, nombre, apellido, celular
        );
    }
}