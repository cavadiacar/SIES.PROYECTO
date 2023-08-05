
package com.mycompany.siesdjdbc.enums;


public enum EstadoCivil {
    SOLTERO("Soltero"), UNION_LIBRE("Unión libre"), CASADO("Casado"), VIUDO("Viudo"), DIVORCIADO("Divorciado");
    public String estado;

    EstadoCivil(String estado) {
        this.estado = estado;
    }
}
