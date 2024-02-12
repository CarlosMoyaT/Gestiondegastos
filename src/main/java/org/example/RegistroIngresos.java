package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroIngresos {
    private List<Gasto> ingresos;

    public RegistroIngresos() {
        this.ingresos = new ArrayList<>();
    }

    public void agregarIngreso(double cantidad, LocalDate fecha, String categoria) {
        Gasto nuevoIngreso = new Gasto(cantidad, fecha, "", categoria, true);
        ingresos.add(nuevoIngreso);
    }
}

