package org.example;
import java.time.LocalDate;
import java.util.Map;


public class TendenciaGastos {
    private Map<LocalDate, Map<String, Double>> tendencias;

    public TendenciaGastos(Map<LocalDate, Map<String, Double>> tendencias) {
        this.tendencias = tendencias;
    }

    public void agregarTendencia(LocalDate fecha, Map<String, Double> datos) {
        tendencias.put(fecha, datos);
    }

    public void visualizarTendencias() {
        for (Map.Entry<LocalDate , Map<String, Double>> entry : tendencias.entrySet()) {
            LocalDate fecha = entry.getKey();
            Map<String, Double> datos = entry.getValue();

            System.out.println("Fecha " + fecha);

            for (Map.Entry<String, Double> categoriaEntry : datos.entrySet()) {
                String categoria = categoriaEntry.getKey();
                double gasto = categoriaEntry.getValue();

                System.out.println("   " + categoria + ": " + gasto);
            }
            System.out.println();
        }
    }
}
