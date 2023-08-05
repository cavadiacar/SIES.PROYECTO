package com.mycompany.siesjdbc.persistencia.pEntidades;

import com.mycompany.siesjdbc.entidades.PersonalDeServicio;
import com.mycompany.siesjdbc.persistencia.Dao;
import java.util.ArrayList;
import java.util.Collection;

public class PersonalServicioDao extends Dao {

    public void guardarPersonaDeServicio(PersonalDeServicio personal) throws Exception {
        try {
            if (personal == null) {
                throw new Exception("Debe indicar una persona de servicio");
            }

            if (validarPersonaCreadaPorDocumento(personal.getNumero_identificacion())) {
                System.out.println("La persona ya existe en la base de datos");
            } else {
                String sql = "INSERT INTO Persona (nombre, apellido, numero_identificacion, estado_civil) "
                        + "VALUES ('" + personal.getNombre() + "', '" + personal.getApellido() + "', '" + personal.getNumero_identificacion() + "','" + personal.getEstadoCivil() + "');";

                insertarModificarEliminar(sql);

                sql = "SELECT id from persona where numero_identificacion = '" + personal.getNumero_identificacion() + "';";
                consultarBase(sql);
                if (resultado.next()) {
                    sql = "INSERT INTO empleado (id, anio_incorporacion, despacho) VALUES (" + resultado.getInt("id") + ",'" + personal.getAnioIncorporacion() + "','" + personal.getnDespacho() + "');";
                    insertarModificarEliminar(sql);

                    sql = "SELECT id from persona where numero_identificacion = '" + personal.getNumero_identificacion() + "';";
                    consultarBase(sql);
                    
                    if(resultado.next()){
                    sql = "INSERT INTO personalservicio (id, seccion) VALUES (" + resultado.getInt("id") + ",'" + personal.getSeccion() + "')";
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

    public void modificarPersonalDeServicio(PersonalDeServicio personal) throws Exception {
        try {
            if (personal == null) {
                throw new Exception("Debe indicar la persona de servicio que desea modificar");
            }
            String sql = "Select * from personalservicio where id = " + personal.getId() + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona que desea modificar no es una persona de servicio");
            } else {
                sql = "UPDATE personalservicio natural join empleado natural join persona p SET "
                        + "nombre = '" + personal.getNombre() + "', apellido = '" + personal.getApellido() + "', estado_civil = '" + personal.getEstadoCivil() + "', "
                        + "despacho = '" + personal.getnDespacho() + "', anio_incorporacion = '" + personal.getAnioIncorporacion() + "', seccion = '" + personal.getSeccion() + "' WHERE id = '" + personal.getId() + "'";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarPersonalDeServicio(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id de la persona de servicio");
            }
            String sql = "Select * from personalservicio p where id = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona seleccionada no es una persona de servicio");
            } else {
                sql = "DELETE "
                        + "FROM personalservicio "
                        + "WHERE personalservicio.id = " + id;
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Integer buscarPersonalDeServicioPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM PERSONA NATURAL JOIN EMPLEADO NATURAL JOIN PERSONALSERVICIO WHERE ID = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                return null;
            } else {
                System.out.println("Persona de servicio encontrada con exito");
                return id;
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar persona de servicio");
        } finally {
            desconectarBase();
        }
    }

    public Collection<PersonalDeServicio> listarPersonalDeServicio() throws Exception {
        try {
            String sql = "SELECT p.id, p.nombre, apellido, numero_identificacion, estado_civil, despacho, anio_incorporacion, seccion FROM \n"
                    + "PERSONA p NATURAL JOIN empleado e NATURAL JOIN personalservicio ;";

            consultarBase(sql);

            PersonalDeServicio personal = null;
            Collection<PersonalDeServicio> personasdeservicio = new ArrayList();
            while (resultado.next()) {
                personal = new PersonalDeServicio();
                personal.setId(resultado.getInt("id"));
                personal.setNombre(resultado.getString("p.nombre"));
                personal.setApellido(resultado.getString("apellido"));
                personal.setNumero_identificacion(resultado.getString("numero_identificacion"));
                personal.setEstadoCivil(resultado.getString("estado_civil"));
                personal.setAnioIncorporacion(resultado.getString("anio_incorporacion"));
                personal.setnDespacho(resultado.getString("despacho"));
                personal.setSeccion(resultado.getString("seccion"));

                personasdeservicio.add(personal);
            }
            return personasdeservicio;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el procedimiento");
        } finally {
            desconectarBase();
        }
    }

    public void cambiarSeccion(Integer id, String seccion) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id de la persona de servicio que desea modificar");
            }
            String sql = "Select * from personalservicio where id = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona que desea cambiarle el departamento no es una persona de servicio");
            } else {
                sql = "UPDATE personalservicio "
                        + "SET seccion = '" + seccion + "' "
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
