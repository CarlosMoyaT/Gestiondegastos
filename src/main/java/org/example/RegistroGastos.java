package org.example;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.example.CategorizadorGastos.CategoriaGasto;


public class RegistroGastos {
    private List<Gasto> listaGastos;
    private Map<String, Double> limitesMensuales;  // Mapa para almacenar límites por categoría
    private RegistroIngresos registroIngresos;
    private TendenciaGastos tendenciaGastos;

    //Constructor
    public RegistroGastos() {
        this.listaGastos = new ArrayList<>();
        this.limitesMensuales = new HashMap<>();
        this.registroIngresos = new RegistroIngresos();
        this.tendenciaGastos = new TendenciaGastos(new HashMap<>());

    }

    //Metodo para agregar un nuevo gasto
    public void agregarGasto(double cantidad, LocalDate fecha, String categoria) {
        try {
            CategoriaGasto categoriaEnum = CategoriaGasto.valueOf(categoria.toUpperCase());
            Gasto nuevoGasto = new Gasto(cantidad, fecha, "", categoriaEnum.name(), false);
            listaGastos.add(nuevoGasto);

            verificarLimites(categoriaEnum); // Verificar límites mensuales y notificar
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría no válida: " + categoria);
        }
    }

    public void agregarIngresos(double cantidad, LocalDate fecha, String categoria) {
        registroIngresos.agregarIngreso(cantidad, fecha,  categoria);
    }

    public void establecerLimiteMensual(String categoria, double limite) {
        limitesMensuales.put(categoria, limite);
    }

    public double getLimiteMensual(CategoriaGasto categoria) {
        String nombreCategoria = categoria.name();
        if (limitesMensuales.containsKey(nombreCategoria)) {
            return limitesMensuales.get(nombreCategoria);
        } else {
            return 490;
        }
    }

    private void verificarLimites(CategorizadorGastos.CategoriaGasto categoria) {
        if (limitesMensuales.containsKey(categoria.name())) {
            double limite = limitesMensuales.get(categoria.name());
            double gastosMensuales = obtenerGastosPorCategoria(categoria);

            if (gastosMensuales >= 0.8 * limite && gastosMensuales <= limite) {
                System.out.println("Aviso: te estás acercando al límite de gastos para la categoría " + categoria);
            } else if (gastosMensuales > limite) {
                System.out.println("¡Cuidado! has superado el límite para la categoría " + categoria);
            }
        }
    }

    private double obtenerGastosPorCategoria(String categoria) {
        return listaGastos.stream()
                .filter(gasto -> gasto.getCategoria().equals(categoria))
                .mapToDouble(Gasto::getCantidad)
                .sum();
    }

    //Metodo para ver todos los gastos
    public double verTodosLosGastos() {
        double total = 0;
        for (Gasto gasto : listaGastos) {
            total += gasto.getCantidad();
        }
        return total;
    }

    public double getTotalGastos() {
        double total = 0;
        for (Gasto gasto : listaGastos) {
            total += gasto.getCantidad();
        }
        return total;
    }

    public double obtenerGastosPorCategoria(CategorizadorGastos.CategoriaGasto categoria) {
        return listaGastos.stream()
                .filter(gasto -> gasto.getCategoria().equals(categoria.name()))
                .mapToDouble(Gasto::getCantidad)
                .sum();
    }

    public void actualizarTendenciaGastos() {
        Map<String, Double> datosGastos = new HashMap<>();

        for (CategoriaGasto categoria : CategoriaGasto.values()) {
            double gastoCategoria = obtenerGastosPorCategoria(categoria);
            datosGastos.put(categoria.name(), gastoCategoria);
        }

        LocalDate fechaActual = LocalDate.now();

        if (tendenciaGastos != null) {
            try {
                tendenciaGastos.agregarTendencia(fechaActual, datosGastos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: La instancia de TendenciaGastos no está inicializada correctamente.");
        }
    }
}
