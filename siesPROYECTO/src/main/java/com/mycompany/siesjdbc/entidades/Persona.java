/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siesjdbc.entidades;

import com.mycompany.siesdjdbc.enums.EstadoCivil;
import java.util.ArrayList;
import java.util.Scanner;


public class Persona {
    protected Integer Id;
    protected String nombre;
    protected String numero_identificacion;
    protected String apellido;
    protected String estadoCivil;

    public Persona(Integer Id, String nombre, String numero_identificacion, String apellido, String estadoCivil) {
        this.Id = Id;
        this.nombre = nombre;
        this.numero_identificacion = numero_identificacion;
        this.apellido = apellido;
        this.estadoCivil = estadoCivil;
    }

    public Persona() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero_identificacion() {
        return numero_identificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }
    

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return "Persona{" + "Id=" + Id + ", nombre=" + nombre + ", numero_identificacion=" + numero_identificacion + ", apellido=" + apellido + ", estadoCivil=" + estadoCivil + '}';
    }

   
    
    
  
}
