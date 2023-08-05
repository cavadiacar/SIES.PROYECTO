package com.mycompany.siesjdbc.persistencia.pEntidades;

import com.mycompany.siesjdbc.entidades.Persona;
import com.mycompany.siesjdbc.persistencia.Dao;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author migue
 */
public class PersonaDao extends Dao {

    public void guardarPersona(Persona persona) throws Exception {
        try {
            if (persona == null) {
                throw new Exception("Debe indicar una Persona");
            }
            if(validarPersonaCreadaPorDocumento(persona.getNumero_identificacion())){
                System.out.println("La persona ya existe en la base de datos");
            }else{
            String sql = "INSERT INTO Persona (nombre, apellido, numero_identificacion, estado_civil) "
                    + "VALUES ('" + persona.getNombre() + "', '" + persona.getApellido() + "', '" + persona.getNumero_identificacion() + "','" + persona.getEstadoCivil() + "');";

            System.out.println(sql);
            insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarPersona(Persona persona) throws Exception {
        try {
            if (persona == null) {
                throw new Exception("Debe indicar la persona que desea modificar");
            }
            String sql = "Select * from persona p natural join estudiante e where id = " + persona.getId() + ";";
            consultarBase(sql);
            if (resultado.next()) {
                System.out.println("La persona que desea modificar es un estudiante, vaya al panel de modificar estudiante");
            } else {
                sql = "UPDATE Persona SET "
                        + "nombre = '" + persona.getNombre() + "', apellido = '" + persona.getApellido() + "', estado_civil = '" + persona.getEstadoCivil() + "' "
                        + "WHERE id = '" + persona.getId() + "'";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarPersona(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id de la persona");
            }
            String sql = "Select * from persona p natural join estudiante e where id = " + id + ";";
            consultarBase(sql);
            if (resultado.next()) {
                System.out.println("La persona que desea modificar es un estudiante, vaya al panel de modificar estudiante");
            } else {
                sql = "DELETE FROM Persona WHERE id = " + id + "";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Collection<Persona> listarPersonas() throws Exception {
        try {
            String sql = "SELECT * FROM PERSONA ";

            consultarBase(sql);

            Persona persona = null;
            Collection<Persona> personas = new ArrayList();
            while (resultado.next()) {
                persona = new Persona();
                persona.setId(resultado.getInt("id"));
                persona.setNombre(resultado.getString("nombre"));
                persona.setApellido(resultado.getString("apellido"));
                persona.setNumero_identificacion(resultado.getString("numero_identificacion"));
                personas.add(persona);
            }
            return personas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el procedimiento");
        } finally {
            desconectarBase();
        }
    }

    public Integer buscarPersonaPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM PERSONA WHERE ID = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                return null;
            } else {
                System.out.println("Persona encontrada con exito");
                return id;
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar persona");
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
