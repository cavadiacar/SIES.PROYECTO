package com.mycompany.siesjdbc.servicios;

import com.mycompany.siesjdbc.entidades.Egresado;
import com.mycompany.siesdjdbc.enums.Cursos;
import com.mycompany.siesdjdbc.enums.EstadoCivil;
import com.mycompany.siesjdbc.persistencia.pEntidades.EgresadoDao;
import java.util.ArrayList;
import java.util.Scanner;

public class EgresadoServicio {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    EgresadoDao ed = new EgresadoDao();
    Egresado estudiante = null;

    public void crearEstudiante() {
        System.out.println("Bienvenido a la creación de egresados");
        try {
            estudiante = new Egresado();
            System.out.println("Ingrese el nombre del egresado");
            estudiante.setNombre(sc.next());
            System.out.println("Ingrese el apellido del e");
            estudiante.setApellido(sc.next());
            System.out.println("Ingrese el numero de identificación del estudiante");
            estudiante.setNumero_identificacion(sc.next());
            estudiante.setEstadoCivil(estadoCivil());
            ed.guardarEstudiante(estudiante);
            System.out.println("¿Desea matricular al estudiante en algún curso? S/N");
            String opcion = sc.next();
            validarSN(opcion);
            if (opcion.equalsIgnoreCase("s")) {
                ed.matricularEstudianteaCurso(estudiante, seleccionarCurso());
            }

        } catch (Exception e) {
            System.err.println("Error al crear estudiante");
            System.err.println(e);
        }
    }

    public void modificarEstudiante() {
        System.out.println("Bienvenido a la modificación de estudiantes");
        try {
            estudiante = new Egresado();
            System.out.println("Ingrese el ID del estudiante que desea modificar");
            estudiante.setId(sc.nextInt());
            if (ed.buscarEstudiantePorId(estudiante.getId()) == null) {
                throw new Exception();
            } else {
                System.out.println("Ingrese el nuevo nombre del estudiante");
                estudiante.setNombre(sc.next());
                System.out.println("Ingrese el nuevo apellido del estudiante");
                estudiante.setApellido(sc.next());
                estudiante.setEstadoCivil(estadoCivil());
                ed.modificarEgresado(estudiante);
                System.out.println("¿Desea matricular al estudiante en algún curso? S/N");
                String opcion = sc.next();
                validarSN(opcion);
                if (opcion.equalsIgnoreCase("s")) {
                    ed.matricularEstudianteaCurso(estudiante, seleccionarCurso());
                }
            }

        } catch (Exception e) {
            System.err.println("Error al crear estudiante");
            System.err.println(e);
        }
    }

    public void matricularEstudianteACurso() {
        try {
            System.out.println("Panel de matriculas de estudiantes");
            System.out.println("¿Desea matricular al estudiante en algún curso? S/N");
            String opcion = sc.next();
            validarSN(opcion);
            if (opcion.equalsIgnoreCase("s")) {
                estudiante = new Egresado();
                System.out.println("Ingrese el ID del estudiante que desea atricular");
                estudiante.setId(sc.nextInt());
                if (ed.buscarEstudiantePorId(estudiante.getId()) == null) {
                    throw new Exception();
                } else {
                    ed.matricularEstudianteaCurso(estudiante, seleccionarCurso());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al matricular estudiante");
        }
    }

    public void retirarEstudianteDeCurso() {
        try {
            estudiante = new Egresado();
            System.out.println("Bienvenido al panel de retiro de estudiantes por curso");
            System.out.println("Ingrese el id del estudiante");
            estudiante.setId(sc.nextInt());
            if (ed.buscarEstudiantePorId(estudiante.getId()) == null) {
                throw new Exception();
            } else {
                ed.retirarEstudianteDeCursoPorIdyNombre(estudiante.getId(), seleccionarCurso());
            }

        } catch (Exception e) {
            System.out.println("Error al retirar estudiante de curso");
        }
    }

    public void eliminarEstudiante() {
        try {
            System.out.println("Bienvenido a la eliminación de estudiantes");
            System.out.println("Ingrese el Id del estudiante que desea eliminar");
            Integer id = sc.nextInt();
            if (ed.buscarEstudiantePorId(id) == null) {
                throw new Exception();
            } else {
                ed.eliminarEgresado(id);
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar estudiante, verifique que el id sea correcto");
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

    public void listarEstudiantes() {
        System.out.println("Bienvenido a la lista de estudiantes");
        try {
            ArrayList<Egresado> estudiantes = (ArrayList<Egresado>) ed.listarEgresados();
            if (estudiantes.isEmpty()) {
                System.out.println("No hay estudiantes agregados");
            } else {
                for (Egresado aux : estudiantes) {
                    System.out.println(aux.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar estudiantes");
        }
    }

    private void validarSN(String opcion) {
        while (!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("n")) {
            System.out.println("Elija una opción válida S/N");
            opcion = sc.next();
        }
    }

    private String seleccionarCurso() {
        System.out.println("Bienvenido al panel de selección de cursos");
        System.out.println("Seleccione un curso:");
        int cont = 1;
        for (Cursos aux : Cursos.values()) {
            System.out.println((cont++) + ". " + aux.materia);
        }
        int opcion = sc.nextInt();
        validarOpcion(opcion);
        switch (opcion) {
            case 1:
                return Cursos.values()[0].materia;
            case 2:
                return Cursos.values()[1].materia;
            case 3:
                return Cursos.values()[2].materia;
            case 4:
                return Cursos.values()[3].materia;
            case 5:
                return Cursos.values()[4].materia;
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
