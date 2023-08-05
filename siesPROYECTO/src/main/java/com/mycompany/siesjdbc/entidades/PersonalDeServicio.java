
package com.mycompany.siesjdbc.entidades;

import com.mycompany.siesdjdbc.enums.Seccion;




public final class PersonalDeServicio extends Funcionario {

    private String seccion;

    public PersonalDeServicio(String seccion, String anioIncorporacipn, String nDespacho, Integer Id, String nombre, String numero_identificacion, String apellido, String estadoCivil) {
        super(anioIncorporacipn, nDespacho, Id, nombre, numero_identificacion, apellido, estadoCivil);
        this.seccion = seccion;
    }
    

    public PersonalDeServicio() {

    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return super.toString() + "PersonalDeServicio{" + "seccion=" + seccion + '}';
    }

    
 
}
