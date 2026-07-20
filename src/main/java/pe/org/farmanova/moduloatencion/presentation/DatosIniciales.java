/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.org.farmanova.moduloatencion.presentation;

import java.time.LocalDate;
import java.time.LocalTime;
import pe.org.farmanova.moduloatencion.business.entities.Venta;
import pe.org.farmanova.moduloatencion.business.enums.PrioridadTurno;
import pe.org.farmanova.moduloatencion.business.enums.Rol;
import pe.org.farmanova.moduloatencion.business.sesion.Sesion;
import pe.org.farmanova.moduloatencion.service.BusinessException;
import pe.org.farmanova.moduloatencion.service.ClienteService;
import pe.org.farmanova.moduloatencion.service.MedicamentoService;
import pe.org.farmanova.moduloatencion.service.TurnoService;
import pe.org.farmanova.moduloatencion.service.UsuarioService;
import pe.org.farmanova.moduloatencion.service.VentaService;

/**
 *
 * @author Casa
 */
public class DatosIniciales {

    public static void cargarDatos() throws BusinessException {

        //USUARIOS
        UsuarioService.registrarUsuario(Sesion.ADMIN_PRINCIPAL, "farmanova123", Rol.ADMINISTRADOR);

        UsuarioService.registrarUsuario("AngelMostacero", "angel123", Rol.CAJERO);

        UsuarioService.registrarUsuario("IsaiMartinez", "isai123", Rol.CAJERO);
        
        UsuarioService.registrarUsuario("SandraGarcia", "sandra123", Rol.CAJERO);

        UsuarioService.registrarUsuario("JesusHuallanca", "jesus123", Rol.CAJERO);
        

        // CLIENTES
        ClienteService.registrarCliente("60876179", "Angel", "Mostacero", "999111222");

        ClienteService.registrarCliente("67458909", "Edson", "Campos", "987654321");

        ClienteService.registrarCliente("45678912", "Sandra", "Garcia", "955444333");

        ClienteService.registrarCliente("98345672", "Jesus", "Huallanca", "955444333");

        ClienteService.registrarCliente("35427898", "Isai", "Medina", "955444333");
        

        //TURNO
        TurnoService.registrarTurno(ClienteService.buscarCliente("60876179"), LocalDate.now(),
                LocalTime.of(9, 0), PrioridadTurno.NORMAL);

        TurnoService.registrarTurno(ClienteService.buscarCliente("67458909"), LocalDate.now(),
                LocalTime.of(10, 30), PrioridadTurno.EMERGENCIA);

        TurnoService.registrarTurno(ClienteService.buscarCliente("98345672"), LocalDate.now(),
                LocalTime.of(10, 30), PrioridadTurno.ADULTO_MAYOR);
        

        //MEDICAMENTOS
        MedicamentoService.registrarMedicamento("Paracetamol 500 mg", "Genfar", 2.50,
                120, LocalDate.of(2027, 5, 20));

        MedicamentoService.registrarMedicamento("Ibuprofeno 400 mg", "Bayer", 4.20,
                80, LocalDate.of(2027, 8, 15));

        MedicamentoService.registrarMedicamento("Amoxicilina 500 mg", "MK", 12.80,
                45, LocalDate.of(2026, 12, 30));

        MedicamentoService.registrarMedicamento("Loratadina 10 mg", "Medifarma", 3.90,
                60, LocalDate.of(2028, 3, 12));

        MedicamentoService.registrarMedicamento("Omeprazol 20 mg", "AC Farma", 8.50,
                90, LocalDate.of(2027, 10, 18));

        MedicamentoService.registrarMedicamento("Salbutamol Inhalador", "GlaxoSmithKline", 25.90,
                20, LocalDate.of(2028, 1, 25));
        

        //VENTAS
        Venta venta2 = VentaService.crearVenta(
                ClienteService.buscarCliente("60876179"),
                UsuarioService.buscarUsuario("AngelMostacero")
        );

        VentaService.agregarDetalle(
                venta2,
                MedicamentoService.buscarMedicamento(103),
                1
        );

        VentaService.agregarDetalle(
                venta2,
                MedicamentoService.buscarMedicamento(104),
                2
        );

        VentaService.finalizarVenta(venta2);

        
        Venta venta3 = VentaService.crearVenta(
                ClienteService.buscarCliente("45678912"),
                UsuarioService.buscarUsuario("IsaiMartinez")
        );

        VentaService.agregarDetalle(
                venta3,
                MedicamentoService.buscarMedicamento(102),
                3
        );

        VentaService.finalizarVenta(venta3);

    }
}
