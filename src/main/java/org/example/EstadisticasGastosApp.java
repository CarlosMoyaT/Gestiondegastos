package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;

import java.text.ParseException;
import java.time.LocalDate;

public class EstadisticasGastosApp extends Application {
    private CategorizadorGastos categorizadorGastos;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        categorizadorGastos = new CategorizadorGastos();

        inicializarGastos();

        PieChart pieChart = createPieChart();
        Scene scene = new Scene(pieChart, 600, 400);

        stage.setTitle("Estadisticas de gastos por categoria");
        stage.setScene(scene);
        stage.show();
    }

    private void inicializarGastos() {
        categorizadorGastos.agregarGasto(new Gasto(50.0, LocalDate.parse("2022-01-01"), "Comida", CategorizadorGastos.CategoriaGasto.ALIMENTACION.name(), true));
        categorizadorGastos.agregarGasto(new Gasto(30.0, LocalDate.parse("2022-01-02"), "Transporte", CategorizadorGastos.CategoriaGasto.TRANSPORTE.name(), true));
        categorizadorGastos.agregarGasto(new Gasto(20.0, LocalDate.parse("2022-01-03"), "Película", CategorizadorGastos.CategoriaGasto.ENTRETENIMIENTO.name(), true));

        categorizadorGastos.agregarIngreso(100.0, LocalDate.parse("2022-01-04"), "Salario", CategorizadorGastos.CategoriaGasto.INGRESO.name(), true);
    }

    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        //Obtener los datos para el gráfico desde el categorizador
        for (CategorizadorGastos.CategoriaGasto categoria : CategorizadorGastos.CategoriaGasto.values()) {
            double cantidadGastos = categorizadorGastos.obtenerRegistroCategoria(categoria).verTodosLosGastos();
            pieChart.getData().add(new PieChart.Data(categoria.name(), cantidadGastos));
        }
        return pieChart;
    }

    private void manejarError(Exception e) {
        // Manejar el error de acuerdo a tus requisitos
        e.printStackTrace();
    }
}

