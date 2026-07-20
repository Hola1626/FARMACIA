
package pe.org.farmanova.moduloatencion.business.managers;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.persistence.implementations.ClienteRepositoryTree;
import pe.org.farmanova.moduloatencion.service.BusinessException;

public class ClienteManager {

    private final ClienteRepositoryTree repository;

    public ClienteManager() {
        repository = new ClienteRepositoryTree();
    }

    public void registrarCliente(String dni, String nombre, String apellido, String celular)
            throws BusinessException {     
        
        Cliente cliente = new Cliente(dni, nombre, apellido, celular);

        if (!repository.insertar(cliente)) {
            throw new BusinessException("Ya existe un cliente con ese DNI.");
        }
    }
    

    public Cliente buscarCliente(String dni)
            throws BusinessException {

        Cliente cliente = repository.buscar(dni);

        if (cliente == null) {
            throw new BusinessException("Cliente no encontrado.");
        }

        return cliente;
    }

    public void eliminarCliente(String dni)
            throws BusinessException {

        if (!repository.eliminar(dni)) {
            throw new BusinessException("Cliente no encontrado.");
        }
    }

    public List<Cliente> listarClientes() {
        return repository.listar();
    }

    public int cantidadClientes() {
        return repository.cantidad();
    }

    public boolean estaVacio() {
        return repository.estaVacio();
    }
}
