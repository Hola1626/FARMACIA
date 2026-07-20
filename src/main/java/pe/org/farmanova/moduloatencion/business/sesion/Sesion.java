/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.org.farmanova.moduloatencion.business.sesion;

import pe.org.farmanova.moduloatencion.business.entities.Usuario;

public class Sesion {

    //Sesion sera una clase de apoyo para mantener al usuario logeado actualmente
    private static Usuario usuarioActual;
    
    public static final String ADMIN_PRINCIPAL = "EdsonCampos";

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean esAdministradorPrincipal() {

        return usuarioActual != null
                && usuarioActual.getUsuario().equals(ADMIN_PRINCIPAL);

    }

}
