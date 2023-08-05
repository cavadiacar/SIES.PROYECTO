package com.mycompany.siesjdbc.persistencia.pEntidades;
import com.mycompany.siesjdbc.entidades.Funcionario;
import com.mycompany.siesjdbc.persistencia.Dao;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author migue
 */
public class FuncionarioDao extends Dao {

    public void guardarEmpleado(Funcionario empleado) throws Exception {
        try {
            if (empleado == null) {
                throw new Exception("Debe indicar un Empleado");
            }
            
            if(validarPersonaCreadaPorDocumento(empleado.getNumero_identificacion())){
                System.out.println("La persona ya existe en la base de datos");
            }else{
            String sql = "INSERT INTO Persona (nombre, apellido, numero_identificacion, estado_civil) "
                    + "VALUES ('" + empleado.getNombre() + "', '" + empleado.getApellido() + "', '" + empleado.getNumero_identificacion() + "','" + empleado.getEstadoCivil() + "');";

            insertarModificarEliminar(sql);

            sql = "SELECT id from persona where numero_identificacion = '" + empleado.getNumero_identificacion() + "';";
            consultarBase(sql);
            if (resultado.next()) {
                sql = "INSERT INTO Empleado (id, anio_incorporacion, despacho) VALUES (" + resultado.getInt("id") + ",'" + empleado.getAnioIncorporacion()+ "','" + empleado.getnDespacho() + "');";
                insertarModificarEliminar(sql);
            }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarEmpleado(Funcionario empleado) throws Exception {
        try {
            if (empleado == null) {
                throw new Exception("Debe indicar el empleado que desea modificar");
            }
            String sql = "Select * from empleado where id = " + empleado.getId() + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona que desea modificar no es un empleado");
            } else {
                sql = "UPDATE empleado e natural join persona p SET "
                        + "nombre = '" + empleado.getNombre() + "', apellido = '" + empleado.getApellido() + "', estado_civil = '" + empleado.getEstadoCivil() + "', "
                        + "despacho = '" + empleado.getnDespacho() + "', anio_incorporacion = '" + empleado.getAnioIncorporacion()+ "' WHERE id = '" + empleado.getId() + "'";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarEmpleado(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id del empleado");
            }
            String sql = "Select * from empleado e where id = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                System.out.println("La persona seleccionada no es un empleado");
            } else {
                sql = "DELETE empleado, Persona "
                        + "FROM empleado "
                        + "NATURAL JOIN Persona "
                        + "WHERE empleado.id = " + id;
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Collection<Funcionario> listarEmpleados() throws Exception {
        try {
            String sql = "SELECT p.id, p.nombre, apellido, numero_identificacion, estado_civil, despacho, anio_incorporacion FROM \n"
                    + "PERSONA p NATURAL JOIN empleado e ;";

            consultarBase(sql);

            Funcionario empleado = null;
            Collection<Funcionario> empleados = new ArrayList();
            while (resultado.next()) {
                empleado = new Funcionario();
                empleado.setId(resultado.getInt("id"));
                empleado.setNombre(resultado.getString("p.nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                empleado.setNumero_identificacion(resultado.getString("numero_identificacion"));
                empleado.setEstadoCivil(resultado.getString("estado_civil"));
                empleado.setAnioIncorporacion(resultado.getString("anio_incorporacion"));
                empleado.setnDespacho(resultado.getString("despacho"));

                empleados.add(empleado);
            }
            return empleados;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el procedimiento");
        } finally {
            desconectarBase();
        }
    }

    public Integer buscarEmpleadoPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM PERSONA NATURAL JOIN EMPLEADO WHERE ID = " + id + ";";
            consultarBase(sql);
            if (!resultado.next()) {
                return null;
            } else {
                System.out.println("Empleado encontrado con exito");
                return id;
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar empleado");
        } finally {
            desconectarBase();
        }
    }

    public void cambiarDespachoPorId(Integer id, String despacho) throws Exception {
        try {
            if (id == null || despacho.isEmpty()) {
                throw new Exception("Verifique el id o el despacho ingresado");
            }
            String sql = "UPDATE Empleado SET despacho = '" + despacho + "' WHERE id = " + id + ";";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void cambiarAnioIncorporacionPorId(Integer id, String anio_incorporacion) throws Exception {
        try {
            if (id == null || anio_incorporacion.isEmpty()) {
                throw new Exception("Verifique el id o el año de incorporación ingresado");
            }

            String sql = "UPDATE Empleado SET anio_incorporacion = '" + anio_incorporacion + "' WHERE id = " + id + ";";
            System.out.println("pasa1");
            insertarModificarEliminar(sql);

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
