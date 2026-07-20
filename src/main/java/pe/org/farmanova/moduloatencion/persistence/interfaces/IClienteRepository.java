package pe.org.farmanova.moduloatencion.persistence.interfaces;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;

public interface IClienteRepository {

    public boolean insertar(Cliente cliente);

    public Cliente buscar(String dni);

    public boolean eliminar(String dni);

    public List<Cliente> listar();

    public int cantidad();

    public boolean estaVacio();
}
