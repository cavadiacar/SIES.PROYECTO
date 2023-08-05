package com.mycompany.siesjdbc.entidades;

public class Funcionario extends Persona {

    protected String anioIncorporacion, nDespacho;

    public Funcionario(String anioIncorporacion, String nDespacho, Integer Id, String nombre, String numero_identificacion, String apellido, String estadoCivil) {
        super(Id, nombre, numero_identificacion, apellido, estadoCivil);
        this.anioIncorporacion = anioIncorporacion;
        this.nDespacho = nDespacho;
    }

    public Funcionario(String anioIncorporacion, String nDespacho) {
        this.anioIncorporacion = anioIncorporacion;
        this.nDespacho = nDespacho;
    }

    public Funcionario() {
    }

    public String getAnioIncorporacion() {
        return anioIncorporacion;
    }

    public void setAnioIncorporacion(String anioIncorporacion) {
        this.anioIncorporacion = anioIncorporacion;
    }

    public String getnDespacho() {
        return nDespacho;
    }

    public void setnDespacho(String nDespacho) {
        this.nDespacho = nDespacho;
    }
   

}
