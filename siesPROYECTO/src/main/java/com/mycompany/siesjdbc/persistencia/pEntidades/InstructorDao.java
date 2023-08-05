/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siesjdbc.persistencia.pEntidades;

import com.mycompany.siesjdbc.entidades.Instructor;
import com.mycompany.siesjdbc.persistencia.Dao;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author migue
 */
public class InstructorDao extends Dao {

    public void guardarProfesor(Instructor profesor) throws Exception {
        try {
            if (profesor == null) {
                throw new Exception("Debe indicar un profesor");
            }

            if (validarPersonaCreadaPorDocumento(profesor.getNumero_identificacion())) {
                System.out.println("La persona ya existe en la base de datos");
            } else {
                String sql = "INSERT INTO Persona (nombre, apellido, numero_identificacion, estado_civil) "
                        + "VALUES ('" + profesor.getNombre() + "', '" + profesor.getApellido() + "', '" + profesor.getNumero_identificacion() + "','" + profesor.getEstadoCivil() + "');";

                insertarModificarEliminar(sql);

                sql = "SELECT id from persona where numero_identificacion = '" + profesor.getNumero_identificacion() + "';";
                consultarBase(sql);
                if (resultado.next()) {
                    sql = "INSERT INTO empleado (id, anio_incorporacion, despacho) VALUES (" + resultado.getInt("id") + ",'" + profesor.getAnioIncorporacion() + "','" + profesor.getnDespacho() + "');";
                    insertarModificarEliminar(sql);
                    sql = "SELECT id from persona where numero_identificacion = '" + profesor.getNumero_identificacion() + "';";
                    consultarBase(sql);
                    if (resultado.next()) {
                        sql = "INSERT INTO Profesor (id, departamento) VALUES (" + resultado.getInt("id") + ",'" + profesor.getDepartamento() + "')";
                        insertarModificarEliminar(sql);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarProfesor(Instructor profesor) throws Exception {
        try {
            if (profesor == null) {
                throw new Exception("Debe indicar el profesor que desea modificar");
            }
            String sql = "Select * from profesor where id = " + profesor.getId() + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona que desea modificar no es un profesor");
            } else {
                sql = "UPDATE profesor natural join empleado natural join persona p SET "
                        + "nombre = '" + profesor.getNombre() + "', apellido = '" + profesor.getApellido() + "', estado_civil = '" + profesor.getEstadoCivil() + "', "
                        + "despacho = '" + profesor.getnDespacho() + "', anio_incorporacion = '" + profesor.getAnioIncorporacion() + "', departamento = '" + profesor.getDepartamento() + "' WHERE id = '" + profesor.getId() + "'";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarProfesor(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id del profesor");
            }
            String sql = "Select * from profesor p where id = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona seleccionada no es un profesor");
            } else {
                sql = "DELETE "
                        + "FROM profesor "
                        + "WHERE profesor.id = " + id;
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Integer buscarProfesorPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM PERSONA NATURAL JOIN EMPLEADO NATURAL JOIN PROFESOR WHERE ID = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                return null;
            } else {
                System.out.println("Profesor encontrado con exito");
                return id;
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar profesor");
        } finally {
            desconectarBase();
        }
    }

    public Collection<Instructor> listarProfesores() throws Exception {
        try {
            String sql = "SELECT p.id, p.nombre, apellido, numero_identificacion, estado_civil, despacho, anio_incorporacion, departamento FROM \n"
                    + "PERSONA p NATURAL JOIN empleado e NATURAL JOIN profesor ;";

            consultarBase(sql);

            Instructor profesor = null;
            Collection<Instructor> profesores = new ArrayList();
            while (resultado.next()) {
                profesor = new Instructor();
                profesor.setId(resultado.getInt("id"));
                profesor.setNombre(resultado.getString("p.nombre"));
                profesor.setApellido(resultado.getString("apellido"));
                profesor.setNumero_identificacion(resultado.getString("numero_identificacion"));
                profesor.setEstadoCivil(resultado.getString("estado_civil"));
                profesor.setAnioIncorporacion(resultado.getString("anio_incorporacion"));
                profesor.setnDespacho(resultado.getString("despacho"));
                profesor.setDepartamento(resultado.getString("departamento"));

                profesores.add(profesor);
            }
            return profesores;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el procedimiento");
        } finally {
            desconectarBase();
        }
    }

    public void cambiarDepartamento(Integer id, String departamento) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id del profesor que desea modificar");
            }
            String sql = "Select * from profesor where id = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona que desea cambiarle el departamento no es un profesor");
            } else {
                sql = "UPDATE profesor "
                        + "SET departamento = '" + departamento + "' "
                        + "WHERE id = " + id + ";";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
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
