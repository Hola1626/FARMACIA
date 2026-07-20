package pe.org.farmanova.moduloatencion.persistence.implementations;

import java.util.ArrayList;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.persistence.interfaces.IClienteRepository;
import pe.org.farmanova.moduloatencion.persistence.nodes.NodoCliente;

public class ClienteRepositoryTree implements IClienteRepository {

    private NodoCliente raiz;

    public ClienteRepositoryTree() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(Cliente cliente) {

        if (buscar(cliente.getDni()) != null) {
            return false;
        }

        raiz = insertarRec(raiz, cliente);

        return true;
    }

    @Override
    public Cliente buscar(String dni) {
        return buscarRec(raiz, dni);
    }

    @Override
    public boolean eliminar(String dni) {

        if (buscar(dni) == null) {
            return false;
        }

        raiz = eliminarRec(raiz, dni);

        return true;
    }

    @Override
    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

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

    //Metodos Recursivos
    private NodoCliente insertarRec(NodoCliente nodo, Cliente cliente) {

        if (nodo == null) {
            return new NodoCliente(cliente);
        }
        int comparar = cliente.getDni().compareTo(nodo.getCliente().getDni());
        if (comparar < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), cliente));
        } else if (comparar > 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), cliente));
        }
        return nodo;        
    }

    private Cliente buscarRec(NodoCliente nodo, String dni) {

        if (nodo == null) {
            return null;
        }

        int comparacion = dni.compareTo(nodo.getCliente().getDni());

        if (comparacion == 0) {
            return nodo.getCliente();
        }

        if (comparacion < 0) {
            return buscarRec(nodo.getIzquierdo(), dni);
        }

        return buscarRec(nodo.getDerecho(), dni);
    }

    private NodoCliente obtenerMenor(NodoCliente nodo) {

        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }

        return nodo;
    }

    private NodoCliente eliminarRec(NodoCliente nodo, String dni) {

        if (nodo == null) {
            return null;
        }

        int comparacion = dni.compareTo(nodo.getCliente().getDni());

        if (comparacion < 0) {

            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), dni));

        } else if (comparacion > 0) {

            nodo.setDerecho(eliminarRec(nodo.getDerecho(), dni));

        } else {
            
            // Caso 1: solo hijo derecho
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }

            // Caso 2: solo hijo izquierdo
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            // Caso 3: dos hijos
            NodoCliente sucesor = obtenerMenor(nodo.getDerecho());

            nodo.setCliente(sucesor.getCliente());

            nodo.setDerecho(
                    eliminarRec(nodo.getDerecho(),
                            sucesor.getCliente().getDni())
            );
        }

        return nodo;
    }

    private void inOrden(NodoCliente nodo, List<Cliente> lista) {

        if (nodo != null) {

            inOrden(nodo.getIzquierdo(), lista);

            lista.add(nodo.getCliente());

            inOrden(nodo.getDerecho(), lista);
        }
    }

    private int contar(NodoCliente nodo) {

        if (nodo == null) {
            return 0;
        }

        return 1 + contar(nodo.getIzquierdo()) + contar(nodo.getDerecho());
    }

}
