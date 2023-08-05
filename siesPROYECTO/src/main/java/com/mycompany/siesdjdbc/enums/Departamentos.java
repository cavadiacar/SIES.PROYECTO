
package com.mycompany.siesdjdbc.enums;


public enum Departamentos {
    MATEMATICAS("Matematicas"), LENGUAJES("Lenguajes"), ARQUITECTURA("Arquitectura"), CIENCIAS("Ciencias"), DERECHO("Derecho"), PROGRAMACION("Programacion");

    public String departamento;

    Departamentos(String departamento) {
        this.departamento = departamento;
    }
}
