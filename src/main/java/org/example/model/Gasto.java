package org.example.model;

import java.time.LocalDate;


public class Gasto {

    private double cantidad;
    private LocalDate fecha;
    private String descripcion;
    private String categoria;
    private boolean esIngreso;

    //Constructor
    public Gasto(double cantidad, LocalDate fecha, String descripcion, String categoria, boolean esIngreso) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.esIngreso = esIngreso;

    }

    //Getters y Setters

    public double getCantidad() {

        return cantidad;
    }

    public void setCantidad(double cantidad) {

        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {

        return fecha;
    }

    public void setFecha(LocalDate fecha) {

        this.fecha = fecha;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public String getCategoria() {

        return categoria;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Metodo para mostrar informacion del gasto
    @Override
    public String toString() {
        return "Gasto{" +
                "cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
