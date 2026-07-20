package pe.org.farmanova.moduloatencion.persistence.nodes;

import pe.org.farmanova.moduloatencion.business.entities.Medicamento;

public class NodoMedicamento {

    private Medicamento medicamento;
    private NodoMedicamento izquierdo;
    private NodoMedicamento derecho;

    public NodoMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public NodoMedicamento getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoMedicamento izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoMedicamento getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoMedicamento derecho) {
        this.derecho = derecho;
    }

    

}
