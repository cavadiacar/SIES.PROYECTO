package com.mycompany.siesjdbc.servicios;

import com.mycompany.siesjdbc.entidades.Funcionario;
import com.mycompany.siesdjdbc.enums.EstadoCivil;
import com.mycompany.siesjdbc.persistencia.pEntidades.FuncionarioDao;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionarioServicio {

    FuncionarioDao emd = new FuncionarioDao();
    Funcionario empleado = null;
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public void crearEmpleado() {
        System.out.println("Bienvenido a la creación de empleados");
        try {
            empleado = new Funcionario();
            System.out.println("Ingrese el nombre del empleado");
            empleado.setNombre(sc.next());
            System.out.println("Ingrese el apellido del empleado");
            empleado.setApellido(sc.next());
            System.out.println("Ingrese el numero de identificación del empleado");
            empleado.setNumero_identificacion(sc.next());
            System.out.println("Ingrese el año de incorporación del empleado");
            empleado.setAnioIncorporacion(sc.next());
            System.out.println("Ingrese el número de despacho del empleado");
            empleado.setnDespacho(sc.next());
            empleado.setEstadoCivil(estadoCivil());
            emd.guardarEmpleado(empleado);

        } catch (Exception e) {
            System.err.println("Error al crear empleado");
            System.err.println(e);
        }
    }

    public void modificarEmpleado() {
        System.out.println("Bienvenido a la modificación de empleados");
        try {
            empleado = new Funcionario();
            System.out.println("Ingrese el ID de el empleado que desea modificar");
            empleado.setId(sc.nextInt());

            if (emd.buscarEmpleadoPorId(empleado.getId()) == null) {
                throw new Exception();
            } else {
                System.out.println("Ingrese el nuevo nombre del empleado");
                empleado.setNombre(sc.next());
                System.out.println("Ingrese el nuevo apellido del empleado");
                empleado.setApellido(sc.next());
                System.out.println("Ingrese el año de incorporación del empleado");
                empleado.setAnioIncorporacion(sc.next());
                System.out.println("Ingrese el nuevo número de despacho del empleado");
                empleado.setnDespacho(sc.next());
                empleado.setEstadoCivil(estadoCivil());
                emd.modificarEmpleado(empleado);
            }
        } catch (Exception e) {
            System.err.println("Error al crear empleado");
            System.err.println(e);
        }
    }

    public void eliminarEmpleado() {
        try {
            System.out.println("Bienvenido a la eliminación de empleados");
            System.out.println("Ingrese el Id del empleado que desea eliminar");
            Integer id = sc.nextInt();
            if (emd.buscarEmpleadoPorId(id) == null) {
                throw new Exception();
            } else {
                emd.eliminarEmpleado(id);
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar empleado");

        }
    }

    public void cambiarDespacho() {
        try {
            System.out.println("Bienvenido al panel de cambio de despacho");
            System.out.println("Ingrese el Id del empleado que desea cambiarle el despacho");
            Integer id = sc.nextInt();
            if (emd.buscarEmpleadoPorId(id) == null) {
                throw new Exception();
            } else {
                System.out.println("Ingrese el nuevo despacho");
                String nuevoDespacho = sc.next();
                emd.cambiarDespachoPorId(id, nuevoDespacho);
            }
        } catch (Exception e) {
            System.out.println("Error al cambiar despacho");
        }
    }

    public void cambiarAnioDeIncorporacion() {
        try {
            System.out.println("Bienvenido al panel de cambio de año de incorporación");
            System.out.println("Ingrese el Id del empleado que desea cambiarle el año de incorporación");
            Integer id = sc.nextInt();
            if (emd.buscarEmpleadoPorId(id) == null) {
                throw new Exception();
            } else {
                System.out.println("Ingrese el nuevo año de incorporación");
                String nuevoAño = sc.next();
                emd.cambiarAnioIncorporacionPorId(id, nuevoAño);
            }
        } catch (Exception e) {
            System.out.println("Error al modificar año de incorporación");
        }
    }
    
        public void listarEmpleados() {
        System.out.println("Bienvenido a la lista de empleados");
        try {
            ArrayList<Funcionario> empleados = (ArrayList<Funcionario>) emd.listarEmpleados();
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados agregados");
            } else {
                for (Funcionario aux : empleados) {
                    System.out.println(aux.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar empleados");
        }
    }

    public String estadoCivil() {
        System.out.println("Elija el estado civil");
        int cont = 1;
        for (EstadoCivil aux : EstadoCivil.values()) {
            System.out.println((cont++) + ". " + aux.estado);
        }
        int opcion = sc.nextInt();
        validarOpcion(opcion);
        switch (opcion) {
            case 1:
                return EstadoCivil.values()[0].estado;
            case 2:
                return EstadoCivil.values()[1].estado;
            case 3:
                return EstadoCivil.values()[2].estado;
            case 4:
                return EstadoCivil.values()[3].estado;
            case 5:
                return EstadoCivil.values()[4].estado;

        }
        return null;
    }

    public void validarOpcion(int opcion) {
        while (opcion < 1 || opcion > 5) {
            System.out.println("Elija una opción correcta");
            opcion = sc.nextInt();
        }
    }
}
