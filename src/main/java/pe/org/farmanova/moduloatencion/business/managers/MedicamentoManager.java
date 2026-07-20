package pe.org.farmanova.moduloatencion.business.managers;

import java.time.LocalDate;
import java.util.List;
import pe.org.farmanova.moduloatencion.business.entities.Medicamento;
import pe.org.farmanova.moduloatencion.persistence.implementations.MedicamentoRepositoryTree;
import pe.org.farmanova.moduloatencion.service.BusinessException;

public class MedicamentoManager {

    private final MedicamentoRepositoryTree repository;
    private int siguienteId = 101;

    public MedicamentoManager() {
        repository = new MedicamentoRepositoryTree();
    }

    public void registrarMedicamento(
            String nombre,
            String laboratorio,
            double precio,
            int stock,
            LocalDate fechaVencimiento)
            throws BusinessException {

        Medicamento medicamento = new Medicamento(
                siguienteId++,
                nombre,
                laboratorio,
                precio,
                stock,
                fechaVencimiento
        );

        if (!repository.insertar(medicamento)) {
            throw new BusinessException("Ya existe un medicamento con ese ID.");
        }
    }

    public Medicamento buscarMedicamento(int id)
            throws BusinessException {

        Medicamento medicamento = repository.buscar(id);

        if (medicamento == null) {
            throw new BusinessException("Medicamento no encontrado.");
        }

        return medicamento;
    }

    public void eliminarMedicamento(int id)
            throws BusinessException {

        Medicamento medicamento = repository.buscar(id);

        if (medicamento == null) {
            throw new BusinessException("No existe un medicamento con ese código.");
        }

        if (!repository.eliminar(id)) {
            throw new BusinessException("No se pudo eliminar el medicamento.");
        }

    }

    public List<Medicamento> listarMedicamentos() {
        return repository.listar();
    }

    public int cantidadMedicamentos() {
        return repository.cantidad();
    }

    public boolean estaVacio() {
        return repository.estaVacio();
    }
}
