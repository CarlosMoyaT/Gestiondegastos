package org.example;

import java.time.LocalDate;

public class GastoRecurrente {
    private String descripcion;
    private LocalDate fechaRecordatorio;

    public GastoRecurrente(String descripcion, LocalDate fechaRecordatorio) {
        this.descripcion = descripcion;
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public boolean esFechaRecordatorio(LocalDate fecha) {
        // Lógica para verificar si la fecha actual coincide con la fecha de recordatorio
        return fechaRecordatorio.equals(fecha);
    }

    public String getDescripcion() {
        return descripcion;
    }
}
