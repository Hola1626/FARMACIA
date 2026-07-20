package pe.org.farmanova.moduloatencion.persistence.nodes;

import pe.org.farmanova.moduloatencion.business.entities.Cliente;

public class NodoCliente {

    private Cliente cliente;
    private NodoCliente izquierdo;
    private NodoCliente derecho;

    public NodoCliente(Cliente cliente) {
        this.cliente = cliente;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
       
    public NodoCliente getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoCliente izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoCliente getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoCliente derecho) {
        this.derecho = derecho;
    }
}