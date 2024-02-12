package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class HistorialDeGastos {
    private List<Gasto> gastos;

    public HistorialDeGastos() {
        this.gastos = new ArrayList<>();
    }

    public void agregarGasto (Gasto gasto) {
        gastos.add(gasto);
    }

    public List<Gasto> buscarPorFecha(Date fecha) {
        return gastos.stream()
             .filter(g -> g.getFecha().equals(fecha))
             .collect(Collectors.toList());
    }

    public List<Gasto> buscarPorCategoria(String categoria) {
        return gastos.stream()
                .filter(g -> g.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

}
