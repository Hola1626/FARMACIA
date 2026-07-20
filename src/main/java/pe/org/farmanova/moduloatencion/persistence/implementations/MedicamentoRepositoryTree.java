package pe.org.farmanova.moduloatencion.persistence.implementations;

import java.util.ArrayList;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Medicamento;
import pe.org.farmanova.moduloatencion.persistence.interfaces.IMedicamentoRepository;
import pe.org.farmanova.moduloatencion.persistence.nodes.NodoMedicamento;

public class MedicamentoRepositoryTree implements IMedicamentoRepository {

    private NodoMedicamento raiz;

    public MedicamentoRepositoryTree() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(Medicamento medicamento) {
        if (buscar(medicamento.getId()) != null) {
            return false;
        }

        raiz = insertarRec(raiz, medicamento);

        return true;
    }

    @Override
    public Medicamento buscar(int id) {
        return buscarRec(raiz, id);
    }

    @Override
    public boolean eliminar(int id) {
        if (buscar(id) == null) {
            return false;
        }

        raiz = eliminarRec(raiz, id);

        return true;
    }

    @Override
    public List<Medicamento> listar() {
        List <Medicamento> lista = new ArrayList<>(); 
        inOrden(raiz, lista);
        return lista;
    }

    @Override
    public int cantidad() {
        return contar(raiz);
    }

    @Override
    public boolean estaVacio() {
        return raiz == null;
    }

    //Metodos recursivos
    public NodoMedicamento insertarRec(NodoMedicamento nodo, Medicamento medicamento) {
        if (nodo == null) {
            return new NodoMedicamento(medicamento);
        }
        if (medicamento.getId() < nodo.getMedicamento().getId()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), medicamento));
        } else if (medicamento.getId() > nodo.getMedicamento().getId()) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), medicamento));
        }
        return nodo;
    }

    public Medicamento buscarRec(NodoMedicamento nodo, int id) {
        if (nodo == null) {
            return null;
        }
        if (id == nodo.getMedicamento().getId()) {
            return nodo.getMedicamento();
        }
        if (id < nodo.getMedicamento().getId()) {
            return buscarRec(nodo.getIzquierdo(), id);
        }
        return buscarRec(nodo.getDerecho(), id);

    }

    public NodoMedicamento eliminarRec(NodoMedicamento nodo, int id) {
        if (nodo == null) {
            return null;
        }
        if (id < nodo.getMedicamento().getId()) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), id)); 
        } else if (id > nodo.getMedicamento().getId()) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), id)); 
        } else {
            // Caso 1: solo hijo derecho
            if(nodo.getIzquierdo()==null){
                return nodo.getDerecho();
            }
            // Caso 2: solo hijo izquierdo
            if(nodo.getDerecho()==null){
                return nodo.getIzquierdo();
            }
            //Caso 3: dos hijos
            NodoMedicamento sucesor = obtenerMenor(nodo.getDerecho());
            nodo.setMedicamento(sucesor.getMedicamento());
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getMedicamento().getId()));
        }
        return nodo;
    }

    private NodoMedicamento obtenerMenor(NodoMedicamento nodo) {
        while (nodo.getIzquierdo()!=null){
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }
    
    public void inOrden(NodoMedicamento nodo, List<Medicamento> lista){
        if (nodo!=null){
            inOrden(nodo.getIzquierdo(), lista);
            lista.add(nodo.getMedicamento());
            inOrden(nodo.getDerecho(), lista);
        }
    }

    public int contar(NodoMedicamento nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contar(nodo.getIzquierdo()) + contar(nodo.getDerecho());
    }

}
