package com.mycompany.siesjdbc.entidades;

import com.mycompany.siesdjdbc.enums.Cursos;
import java.util.ArrayList;


public final class Egresado extends Persona {

    protected ArrayList<String> cursos = new ArrayList<>();

    public Egresado(Integer Id, String nombre, String numero_identificacion, String apellido, String estadoCivil) {
        super(Id, nombre, numero_identificacion, apellido, estadoCivil);
    }

    
    public Egresado() {
    }

    public ArrayList<String> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<String> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return super.toString() + "Estudiante{" + "cursos=" + cursos + '}';
    }

    
}
