package org.example;
import java.util.HashMap;
import java.util.Map;

public class CalculadoraPresupuesto {
    private Map<CategorizadorGastos.CategoriaGasto, Double> fondosPorCategoria;

    public CalculadoraPresupuesto() {
        this.fondosPorCategoria = new HashMap<>();
    }

    public void asignarFondos(CategorizadorGastos.CategoriaGasto categoria, double fondo) {
        fondosPorCategoria.put(categoria, fondo);
    }

    public void ajustarLimites(RegistroGastos registro) {
        for (Map.Entry<CategorizadorGastos.CategoriaGasto, Double> entry : fondosPorCategoria.entrySet()) {
            CategorizadorGastos.CategoriaGasto categoria = entry.getKey();
            double fondo = entry.getValue();

            double gastosMensuales = registro.obtenerGastosPorCategoria(categoria);
            double nuevoLimite = fondo - gastosMensuales * 0.2; // Reduce el limite en un 20%

            registro.establecerLimiteMensual(categoria.name(), nuevoLimite);
        }
    }

    public Map<CategorizadorGastos.CategoriaGasto, Double> getFondosPorCategoria() {
        return fondosPorCategoria;
    }

}
