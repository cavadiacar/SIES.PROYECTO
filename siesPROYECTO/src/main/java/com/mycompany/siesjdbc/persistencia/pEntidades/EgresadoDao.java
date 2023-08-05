package com.mycompany.siesjdbc.persistencia.pEntidades;

import com.mycompany.siesjdbc.entidades.Egresado;
import com.mycompany.siesjdbc.persistencia.Dao;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author migue
 */
public class EgresadoDao extends Dao {

    public void guardarEstudiante(Egresado egresado) throws Exception {
        try {
            if (egresado == null) {
                throw new Exception("Debe indicar un Estudiante");
            }
            
            if(validarPersonaCreadaPorDocumento(egresado.getNumero_identificacion())){
                System.out.println("La persona ya existe en la base de datos");
            }else{
            String sql = "INSERT INTO Persona (nombre, apellido, numero_identificacion, estado_civil) "
                    + "VALUES ('" + egresado.getNombre() + "', '" + egresado.getApellido() + "', '" + egresado.getNumero_identificacion() + "','" + egresado.getEstadoCivil() + "');";

            insertarModificarEliminar(sql);

            sql = "SELECT id from persona where numero_identificacion = '" + egresado.getNumero_identificacion() + "';";
            consultarBase(sql);
            if (resultado.next()) {
                sql = "INSERT INTO Egresado (id) VALUES (" + resultado.getInt("id") + ");";
                insertarModificarEliminar(sql);
            }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarEgresado(Egresado egresado) throws Exception {
        try {
            if (egresado == null) {
                throw new Exception("Debe indicar el egresado que desea modificar");
            }
            String sql = "Select * from egresado where id = " + egresado.getId() + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona que desea modificar no es un egresado");
            } else {
                sql = "UPDATE Persona SET "
                        + "nombre = '" + egresado.getNombre() + "', apellido = '" + egresado.getApellido() + "', estado_civil = '" + egresado.getEstadoCivil() + "' "
                        + "WHERE id = '" + egresado.getId() + "';";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarEgresado(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id de el egresado");
            }
            String sql = "delete from matricula where egresado_id = " + id + ";";
            insertarModificarEliminar(sql);

            sql = "DELETE Egresado, Persona "
                    + "FROM Egresado "
                    + "NATURAL JOIN Persona "
                    + "WHERE Egresado.id = " + id;
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Collection<Egresado> listarEgresados() throws Exception {
        try {
            String sql = "SELECT p.id, p.nombre, apellido, numero_identificacion, estado_civil FROM \n"
                    + "PERSONA p NATURAL JOIN estudiante e ;";

            consultarBase(sql);

            Egresado estudiante = null;
            Collection<Egresado> estudiantes = new ArrayList();
            ArrayList<String> cursos = new ArrayList();
            while (resultado.next()) {
                estudiante = new Egresado();
                estudiante.setId(resultado.getInt("id"));
                estudiante.setNombre(resultado.getString("p.nombre"));
                estudiante.setApellido(resultado.getString("apellido"));
                estudiante.setNumero_identificacion(resultado.getString("numero_identificacion"));
                estudiante.setEstadoCivil(resultado.getString("estado_civil"));
                // cursos.add(resultado.getString("c.nombre"));
                for (Egresado aux : estudiantes) {
                    if (resultado.getInt("id") == aux.getId()) {
                        cursos.add(resultado.getString("c.nombre"));
                    }
                }
                estudiante.setCursos(cursos);
                estudiantes.add(estudiante);
            }
            return estudiantes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el procedimiento");
        } finally {
            desconectarBase();
        }
    }

    public Integer buscarEstudiantePorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM PERSONA NATURAL JOIN ESTUDIANTE WHERE ID = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                return null;
            } else {
                System.out.println("Estudiante encontrado con exito");
                return id;
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar estudiante");
        }
    }

    public void matricularEstudianteaCurso(Egresado estudiante, String curso) throws Exception {
        try {
            if (estudiante == null || curso.isEmpty()) {
                throw new Exception("Debe indicar el estudiante que desea matricular y el curso al que lo desea matricular");
            }
            String sql = "select id from Curso where nombre = '" + curso + "';";
            consultarBase(sql);
            int id = 0;
            if (resultado.next()) {
                id = resultado.getInt("id");
            }

            sql = "select e.id from estudiante e natural join persona where numero_identificacion = '" + estudiante.getNumero_identificacion() + "';";
            consultarBase(sql);
            if (estudiante.getNumero_identificacion() == null) {
                sql = "INSERT INTO Matricula (estudiante_id, curso_id) VALUES (" + estudiante.getId() + "," + id + ")";
                insertarModificarEliminar(sql);
            } else {
                if (resultado.next()) {
                    sql = "INSERT INTO Matricula (estudiante_id, curso_id) VALUES (" + resultado.getInt("id") + "," + id + ")";
                    insertarModificarEliminar(sql);
                }
            }
            System.out.println("Terminamos");
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }


    public void retirarEstudianteDeCursoPorIdyNombre(Integer id, String nombre) throws Exception {
        try {
            if (id == null && nombre == null) {
                throw new Exception("Verifique que el id o el nombre del curso no esten vacíos");
            }
            int id2 = 0;
            String sql = "SELECT id from curso where nombre = '" + nombre + "';";
            consultarBase(sql);

            if (resultado.next()) {
                id2 = resultado.getInt("id");
            }
            sql = "delete from matricula where estudiante_id = " + id + " and curso_id= '" + id2 + "';";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
      
     public Boolean validarPersonaCreadaPorDocumento(String documento) throws Exception {
        try {
            String sql = "Select * from persona where numero_identificacion = '" + documento + "';";
            consultarBase(sql);
            return resultado.next();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
}
