
package com.mycompany.siesjdbc.servicios;

import com.mycompany.siesjdbc.entidades.PersonalDeServicio;
import com.mycompany.siesdjdbc.enums.Departamentos;
import com.mycompany.siesdjdbc.enums.EstadoCivil;
import com.mycompany.siesdjdbc.enums.Seccion;
import com.mycompany.siesjdbc.persistencia.pEntidades.PersonalServicioDao;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonalServicio {
    
    PersonalServicioDao psd = new PersonalServicioDao();
    PersonalDeServicio personal = null;
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public void crearPersonalDeServicio() {
        System.out.println("Bienvenido a la creación de personal");
        try {
            personal = new PersonalDeServicio();
            System.out.println("Ingrese el nombre de la persona de servicio");
            personal.setNombre(sc.next());
            System.out.println("Ingrese el apellido de la persona de servicio");
            personal.setApellido(sc.next());
            System.out.println("Ingrese el numero de identificación de la persona de servicio");
            personal.setNumero_identificacion(sc.next());
            System.out.println("Ingrese el año de incorporación de la persona de servicio");
            personal.setAnioIncorporacion(sc.next());
            System.out.println("Ingrese el número de despacho de la persona de servicio");
            personal.setnDespacho(sc.next());
            personal.setEstadoCivil(estadoCivil());
            personal.setSeccion(seccion());

            psd.guardarPersonaDeServicio(personal);

        } catch (Exception e) {
            System.err.println("Error al crear persona de servicio");
            System.err.println(e);
        }
    }

    public void modificarPersonalDeServicio() {
        System.out.println("Bienvenido a la modificación de personal");
        try {
            personal = new PersonalDeServicio();
            System.out.println("Ingrese el ID de la persona de servicio que desea modificar");
            personal.setId(sc.nextInt());

            if (psd.buscarPersonalDeServicioPorId(personal.getId()) == null) {
                throw new Exception();
            } else {
                System.out.println("Ingrese el nuevo nombre de la persona de servicio");
                personal.setNombre(sc.next());
                System.out.println("Ingrese el nuevo apellido de la persona de servicio");
                personal.setApellido(sc.next());
                System.out.println("Ingrese el año de incorporación de la persona de servicio");
                personal.setAnioIncorporacion(sc.next());
                System.out.println("Ingrese el nuevo número de despacho de la persona de servicio");
                personal.setnDespacho(sc.next());
                personal.setEstadoCivil(estadoCivil());
                personal.setSeccion(seccion());   
                psd.modificarPersonalDeServicio(personal);
            }
        } catch (Exception e) {
            System.err.println("Error al modificar persona de servicio");
            System.err.println(e);
        }
    }

    public void eliminarPersonaDeServicio() {
        try {
            System.out.println("Bienvenido a la eliminación de personas de servicio");
            System.out.println("Ingrese el Id de la persona de servicio que desea eliminar");
            Integer id = sc.nextInt();
            if (psd.buscarPersonalDeServicioPorId(id) == null) {
                throw new Exception();
            } else {
                psd.eliminarPersonalDeServicio(id);
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar persona de servicio");

        }
    }

    public void listarProfesores() {
        System.out.println("Bienvenido a la lista de personas de servicio");
        try {
            ArrayList<PersonalDeServicio> personal = (ArrayList<PersonalDeServicio>) psd.listarPersonalDeServicio();
            if (personal.isEmpty()) {
                System.out.println("No hay personas de servicio agregadas");
            } else {
                for (PersonalDeServicio aux : personal) {
                    System.out.println(aux.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar personas de servicio");
        }
    }

    
    public void cambiarSeccion(){
        try {
            System.out.println("Panel de cambio de seccion");
                personal = new PersonalDeServicio();
                System.out.println("Ingrese el ID de la persona de servicio que desea cambiar de departamento");
                personal.setId(sc.nextInt());
                if (psd.buscarPersonalDeServicioPorId(personal.getId()) == null) {
                    throw new Exception();
                } else {
                    psd.cambiarSeccion(personal.getId(), seccion());
                }
            }catch (Exception e) {
            System.out.println("Error al cambiar de seccion");
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

    public String seccion() {
        System.out.println("Elija la seccion");
        int cont = 1;
        for (Seccion aux : Seccion.values()) {
            System.out.println((cont++) + ". " + aux.seccion);
        }
        int opcion = sc.nextInt();
        validarOpcion(opcion);
        switch (opcion) {
            case 1:
                return Seccion.values()[0].seccion;
            case 2:
                return Seccion.values()[1].seccion;
            case 3:
                return Seccion.values()[2].seccion;
            case 4:
                return Seccion.values()[3].seccion;
            case 5:
                return Seccion.values()[4].seccion;

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
