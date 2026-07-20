package pe.org.farmanova.moduloatencion.business.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import pe.org.farmanova.moduloatencion.business.enums.EstadoTurno;
import pe.org.farmanova.moduloatencion.business.enums.PrioridadTurno;

public class Turno implements Comparable<Turno> {

    private final int codigo;
    private final Cliente cliente;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final PrioridadTurno prioridad;
    private EstadoTurno estado;

    public Turno(int codigo, Cliente cliente, LocalDate fecha,
            LocalTime hora, PrioridadTurno prioridad,
            EstadoTurno estado) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public PrioridadTurno getPrioridad() {
        return prioridad;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format(
                "============================%n"
                + "        TICKET TURNO       %n"
                + "============================%n"
                + "Código   : %d%n"
                + "Cliente  : %s%n"
                + "DNI      : %s%n"
                + "Teléfono : %s%n"
                + "Fecha    : %s%n"
                + "Hora     : %s%n"
                + "Prioridad: %s%n"
                + "Estado   : %s%n"
                + "============================",
                codigo, cliente.getNombre()+" "+cliente.getApellido(),cliente.getDni(),cliente.getCelular(),
                fecha, hora, prioridad, estado
        );
    }

    @Override
    public int compareTo(Turno otro) {
        
        // La cola solo contiene turnos del día actual.
        // Por eso únicamente se compara prioridad y hora.

        int prioridad1 = this.prioridad.compareTo(otro.prioridad);

        if (prioridad1 != 0) {
            return prioridad1;
        }

        return this.hora.compareTo(otro.hora);

    }
}
