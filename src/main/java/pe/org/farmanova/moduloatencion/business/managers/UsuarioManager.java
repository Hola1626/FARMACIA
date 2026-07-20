package pe.org.farmanova.moduloatencion.business.managers;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Usuario;
import pe.org.farmanova.moduloatencion.business.enums.Rol;
import pe.org.farmanova.moduloatencion.business.sesion.Sesion;
import pe.org.farmanova.moduloatencion.persistence.implementations.UsuarioRepositoryList;
import pe.org.farmanova.moduloatencion.service.BusinessException;

public class UsuarioManager {

    private final UsuarioRepositoryList repository;

    public UsuarioManager() {
        repository = new UsuarioRepositoryList();
    }

    public Usuario login(String usuario, String password)
            throws BusinessException {

        if (usuario == null || usuario.isBlank()) {
            throw new BusinessException("Ingrese un usuario.");
        }

        if (password == null || password.isBlank()) {
            throw new BusinessException("Ingrese una contraseña.");
        }

        Usuario u = repository.login(usuario, password);

        if (u == null) {
            throw new BusinessException("Usuario o contraseña incorrectos.");
        }

        return u;

    }

    public void registrarUsuario(String usuario, String password, Rol rol)
            throws BusinessException {

        if (rol == Rol.ADMINISTRADOR) {

            Usuario adminPrincipal = repository.buscar(Sesion.ADMIN_PRINCIPAL);

            if (adminPrincipal != null && !Sesion.esAdministradorPrincipal()) {

                throw new BusinessException(
                        "Solo el administrador principal puede crear otros administradores.");

            }

        }

        Usuario nuevo = new Usuario(usuario, password, rol);

        if (!repository.insertar(nuevo)) {
            throw new BusinessException("El usuario ya existe.");
        }

    }

    public Usuario buscarUsuario(String usuario)
            throws BusinessException {

        Usuario u = repository.buscar(usuario);

        if (u == null) {
            throw new BusinessException("Usuario no encontrado.");
        }

        return u;
    }

    public void eliminarUsuario(String usuario)
            throws BusinessException {

        Usuario u = repository.buscar(usuario);

        if (u == null) {
            throw new BusinessException("Usuario no encontrado.");
        }

        if (u.getRol() == Rol.ADMINISTRADOR && !Sesion.esAdministradorPrincipal()) {
            throw new BusinessException("Solo el administrador principal puede eliminar administradores.");
        }

        if (u.getUsuario().equals(Sesion.ADMIN_PRINCIPAL)) {

            throw new BusinessException("El administrador principal no puede eliminarse.");

        }

        if (usuario.equals(Sesion.getUsuarioActual().getUsuario())) {
            throw new BusinessException("No puede eliminar el usuario con el que inició sesión.");
        }

        if (!repository.eliminar(usuario)) {
            throw new BusinessException("No se pudo eliminar el usuario.");
        }

    }

    public List<Usuario> listarUsuarios() {
        return repository.listar();
    }

    public int cantidadUsuarios() {
        return repository.cantidad();
    }

    public boolean estaVacio() {
        return repository.estaVacio();
    }
}
