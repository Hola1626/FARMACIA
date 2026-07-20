
package pe.org.farmanova.moduloatencion.service;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Cliente;
import pe.org.farmanova.moduloatencion.business.managers.ClienteManager;

public class ClienteService {

    private static final ClienteManager manager = new ClienteManager();

    public static void registrarCliente(String dni, String nombre, String apellido, String celular)
            throws BusinessException {

        manager.registrarCliente(dni, nombre, apellido, celular);
    }

    public static Cliente buscarCliente(String dni)
            throws BusinessException {

        return manager.buscarCliente(dni);
    }

    public static void eliminarCliente(String dni)
            throws BusinessException {

        manager.eliminarCliente(dni);
    }

    public static List<Cliente> listarClientes() {
        return manager.listarClientes();
    }

    public static int cantidadClientes() {
        return manager.cantidadClientes();
    }

    public static boolean estaVacio() {
        return manager.estaVacio();
    }
}
