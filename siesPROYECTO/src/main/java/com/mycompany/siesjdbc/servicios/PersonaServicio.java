package com.mycompany.siesjdbc.servicios;

import com.mycompany.siesjdbc.entidades.Persona;
import com.mycompany.siesdjdbc.enums.EstadoCivil;
import com.mycompany.siesjdbc.persistencia.pEntidades.PersonaDao;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonaServicio {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    PersonaDao pd = new PersonaDao();
    Persona persona = null;

    public void crearPersona() {
        System.out.println("Bienvenido a la creación de personas");
        try {
            persona = new Persona();
            System.out.println("Ingrese el nombre de la persona");
            persona.setNombre(sc.next());
            System.out.println("Ingrese el apellido de la persona");
            persona.setApellido(sc.next());
            System.out.println("Ingrese el numero de identificación de la persona");
            persona.setNumero_identificacion(sc.next());
            persona.setEstadoCivil(estadoCivil());

            pd.guardarPersona(persona);
        } catch (Exception e) {
            System.err.println("Error al crear persona");
            System.err.println(e);
        }
    }

    public void modificarPersona() {
        try {

            System.out.println("Bienvenido a la modificación de personas");
            persona = new Persona();
            System.out.println("Ingrese el ID de la persona que desea modificar");
            persona.setId(sc.nextInt());

            if (pd.buscarPersonaPorId(persona.getId()) == null) {
                throw new Exception();
            } else {
                System.out.println("Ingrese el nombre nuevo de la persona");
                persona.setNombre(sc.next());
                System.out.println("Ingrese el apellido nuevo de la persona");
                persona.setApellido(sc.next());
                System.out.println("Modificación de estado cívil");
                persona.setEstadoCivil(estadoCivil());
                pd.modificarPersona(persona);
            }
        } catch (Exception e) {
            System.err.println("Error al modificar persona, verifique que el Id sea correcto");
        }
    }

    public void eliminarPersona() {
        try {
            System.out.println("Bienvenido a la eliminación de personas");
            System.out.println("Ingrese el Id de la persona que desea eliminar");
            Integer id = sc.nextInt();
            if (pd.buscarPersonaPorId(id) == null) {
                throw new Exception();
            } else {
                pd.eliminarPersona(id);
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar persona, verifique que el id sea correcto");
        }

    }

    public void listarPersonas() {
        System.out.println("Bienvenido a la lista de personas");
        try {
            ArrayList<Persona> personas = (ArrayList<Persona>) pd.listarPersonas();
            if (personas.isEmpty()) {
                System.out.println("No hay empleados agregadas");
            } else {
                for (Persona aux : personas) {
                    System.out.println(aux.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar personas");
        }
    }

    public String estadoCivil() {
        System.out.println("Elija el estado civil");
        int cont = 1;
        for (EstadoCivil aux : EstadoCivil.values()) {
            System.out.println((cont++) + ". " + aux.estado);
        }
        int opcion = sc.nextInt();
        while (opcion < 1 || opcion > 5) {
            System.out.println("Elija una opción correcta");
            opcion = sc.nextInt();
        }
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
}
