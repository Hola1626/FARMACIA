
package pe.org.farmanova.moduloatencion.persistence.implementations;

import java.util.ArrayList;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Usuario;
import pe.org.farmanova.moduloatencion.persistence.interfaces.IUsuarioRepository;

public class UsuarioRepositoryList implements IUsuarioRepository {

    private final ArrayList<Usuario> usuarios;

    public UsuarioRepositoryList() {
        usuarios = new ArrayList<>();
    }

    @Override
    public boolean insertar(Usuario usuario) {

        if (buscar(usuario.getUsuario()) != null) {
            return false;
        }

        return usuarios.add(usuario);
    }

    @Override
    public Usuario buscar(String usuario) {

        for (Usuario u : usuarios) {

            if (u.getUsuario().equals(usuario)) {
                return u;
            }

        }

        return null;
    }

    @Override
    public Usuario login(String usuario, String password) {

        for (Usuario u : usuarios) {

            if (u.getUsuario().equals(usuario)
                    && u.getPassword().equals(password)) {

                return u;
            }

        }

        return null;
    }

    @Override
    public boolean eliminar(String usuario) {

        Usuario u = buscar(usuario);

        if (u == null) {
            return false;
        }

        return usuarios.remove(u);
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public int cantidad() {
        return usuarios.size();
    }

    @Override
    public boolean estaVacio() {
        return usuarios.isEmpty();
    }

}