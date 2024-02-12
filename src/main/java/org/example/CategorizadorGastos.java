package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CategorizadorGastos {
    private Map<CategoriaGasto, RegistroGastos> categorias;
    private RegistroIngresos registroIngresos;

    public enum CategoriaGasto {
        ALIMENTACION,
        TRANSPORTE,
        ENTRETENIMIENTO,
        INGRESO
        // Se pueden agregar más categorías
    }

    public CategorizadorGastos() {
        this.categorias = new HashMap<>();
        this.registroIngresos = new RegistroIngresos();

        for (CategoriaGasto categoria : CategoriaGasto.values()) {
            categorias.put(categoria, new RegistroGastos());
        }
    }

    public void agregarIngreso(double cantidad, LocalDate fecha, String categoria, String name, boolean b) {
        registroIngresos.agregarIngreso(cantidad, fecha, categoria);
    }

    public void agregarGasto(Gasto gasto) {
        String categoriaString = gasto.getCategoria();
        for (CategoriaGasto categoria : CategoriaGasto.values()) {
            if (categoria.name().equalsIgnoreCase(categoriaString)) {
                categorias.get(categoria).agregarGasto(gasto.getCantidad(), gasto.getFecha(), categoria.name());
                return;
            }
        }
        System.out.println("Categoría no válida: " + categoriaString);
    }

    public RegistroGastos obtenerRegistroCategoria(CategoriaGasto categoria) {
        return categorias.get(categoria);
    }
}

