package com.mycompany.siesjdbc.entidades;



public final class Instructor extends Funcionario {

    private String departamento;

    public Instructor() {
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return super.toString() + "Profesor{" + "departamento=" + departamento + '}';
    }
    

}
