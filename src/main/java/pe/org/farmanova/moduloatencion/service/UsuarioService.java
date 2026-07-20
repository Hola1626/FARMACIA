/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.org.farmanova.moduloatencion.service;

import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Usuario;
import pe.org.farmanova.moduloatencion.business.enums.Rol;
import pe.org.farmanova.moduloatencion.business.managers.UsuarioManager;

public class UsuarioService {

    private static final UsuarioManager manager = new UsuarioManager();

    public static void registrarUsuario(String usuario,
            String password,
            Rol rol)
            throws BusinessException {

        manager.registrarUsuario(usuario, password, rol);
    }

    public static Usuario buscarUsuario(String usuario)
            throws BusinessException {

        return manager.buscarUsuario(usuario);
    }

    public static Usuario login(String usuario,
            String password)
            throws BusinessException {

        return manager.login(usuario, password);

    }

    public static void eliminarUsuario(String usuario)
            throws BusinessException {

        manager.eliminarUsuario(usuario);
    }

    public static List<Usuario> listarUsuarios() {
        return manager.listarUsuarios();
    }

    public static int cantidadUsuarios() {
        return manager.cantidadUsuarios();
    }

    public static boolean estaVacio() {
        return manager.estaVacio();
    }

    

}
