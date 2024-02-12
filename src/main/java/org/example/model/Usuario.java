package org.example.model;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre;
    private String email;
    private Map<String, RegistroGastos> areasGastos;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.areasGastos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setAreasGastos(String nombreArea) {
        areasGastos.put(nombreArea, new RegistroGastos());
    }

    public RegistroGastos obtenerRegistroGastos(String nombreArea) {
        return areasGastos.get(nombreArea);
    }



}
