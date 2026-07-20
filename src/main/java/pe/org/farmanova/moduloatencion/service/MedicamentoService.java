/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.org.farmanova.moduloatencion.service;

import java.time.LocalDate;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Medicamento;
import pe.org.farmanova.moduloatencion.business.managers.MedicamentoManager;

public class MedicamentoService {

    private static final MedicamentoManager manager = new MedicamentoManager();

    public static void registrarMedicamento(
            String nombre,
            String laboratorio,
            double precio,
            int stock,
            LocalDate fechaVencimiento
    )
            throws BusinessException {

        manager.registrarMedicamento(
                nombre,
                laboratorio,
                precio,
                stock,
                fechaVencimiento
        );
    }

    public static Medicamento buscarMedicamento(int id)
            throws BusinessException {

        return manager.buscarMedicamento(id);
    }

    public static void eliminarMedicamento(int id)
            throws BusinessException {

        manager.eliminarMedicamento(id);
    }

    public static List<Medicamento> listarMedicamentos() {
        return manager.listarMedicamentos();
    }

    public static int cantidadMedicamentos() {
        return manager.cantidadMedicamentos();
    }

    public static boolean estaVacio() {
        return manager.estaVacio();
    }
}
