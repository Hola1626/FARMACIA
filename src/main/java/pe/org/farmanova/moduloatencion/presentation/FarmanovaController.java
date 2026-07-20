/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pe.org.farmanova.moduloatencion.presentation;

import pe.org.farmanova.moduloatencion.presentation.JFrame.LoginJFrame;
import pe.org.farmanova.moduloatencion.service.BusinessException;

/**
 *
 * @author Administrador
 */
public class FarmanovaController {

    public static void main(String[] args) {

        try {
            DatosIniciales.cargarDatos();
        } catch (BusinessException ex) {
            ex.getMessage();
        }

        LoginJFrame login = new LoginJFrame();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        
    }
}
