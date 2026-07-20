package pe.org.farmanova.moduloatencion.business.entities;

import pe.org.farmanova.moduloatencion.business.enums.Rol;

public class Usuario {

    private final String usuario;
    private String password;
    private Rol rol;

    public Usuario(String usuario, String password, Rol rol) {
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}