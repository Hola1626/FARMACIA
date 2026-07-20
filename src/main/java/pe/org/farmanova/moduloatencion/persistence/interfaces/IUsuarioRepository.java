package pe.org.farmanova.moduloatencion.persistence.interfaces;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Usuario;

public interface IUsuarioRepository {

    boolean insertar(Usuario usuario);

    Usuario buscar(String usuario);

    Usuario login(String usuario, String password);

    boolean eliminar(String usuario);

    List<Usuario> listar();

    int cantidad();

    boolean estaVacio();
}
