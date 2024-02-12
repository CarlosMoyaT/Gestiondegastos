package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        RegistroGastos registro = new RegistroGastos();

        //Agregar gastos
        registro.agregarGasto(50.0, LocalDate.now(), "Comida");

        // Buscar por fecha
        HistorialDeGastos historial = new HistorialDeGastos();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaBusqueda = null;
        try {
            fechaBusqueda = dateFormat.parse("2022-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Gasto> gastosPorFecha = historial.buscarPorFecha(fechaBusqueda);

        // Buscar por categoría
        List<Gasto> gastosComida = historial.buscarPorCategoria("Comida");
        List<Gasto> gastosTransporte = historial.buscarPorCategoria("Transporte");
        List<Gasto> gastosEntretenimiento = historial.buscarPorCategoria("Entretenimiento");

        //Agregar gastos
        registro.agregarGasto(50.0, LocalDate.now(), "Comida");
        registro.agregarGasto(30.0, LocalDate.now(), "Transporte");
        registro.agregarGasto(20.0, LocalDate.now(), "Entretenimiento");

        //Limites mensuales
        registro.establecerLimiteMensual("Comida", 200.0);
        registro.establecerLimiteMensual("Transporte", 100.0);
        registro.establecerLimiteMensual("Entretenimiento", 80.0);

        //Calculadora de presupuesto
        CalculadoraPresupuesto calculadora = new CalculadoraPresupuesto();
        calculadora.asignarFondos(CategorizadorGastos.CategoriaGasto.ALIMENTACION, 500.0);
        calculadora.asignarFondos(CategorizadorGastos.CategoriaGasto.TRANSPORTE, 300.0);
        calculadora.asignarFondos(CategorizadorGastos.CategoriaGasto.ENTRETENIMIENTO, 200.0);

        //Ver los gastos
        System.out.println("Todos los gastos");
        registro.verTodosLosGastos();

        //Ver gastos totales
        System.out.println("Gastos totales €" + registro.getTotalGastos());

        //Sistema de recordatorios
        RecordatorioGastosRecurrentes.iniciarRecordatorio();

        RegistroGastos nuevoRegistro = new RegistroGastos();

        //Despues de realizar operaciones de gastos
        nuevoRegistro.actualizarTendenciaGastos();
        calculadora.ajustarLimites(nuevoRegistro);



    }
}