package org.example;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class RecordatorioGastosRecurrentes {
    private static List<GastoRecurrente> gastosRecurrentes = new ArrayList<>();


    public static void recordarGastos() {
        System.out.println("Recordando gastos recurrentes...");

        LocalDate fechaActual = LocalDate.now();

        for (GastoRecurrente gasto : gastosRecurrentes) {
            if (gasto.esFechaRecordatorio(fechaActual)) {
                System.out.println("Recordatorio: " + gasto.getDescripcion());
            }
        }
    }

    public static void agregarGastoRecurrente(GastoRecurrente gasto) {
        gastosRecurrentes.add(gasto);
    }

    public static void iniciarRecordatorio() {
        agregarGastoRecurrente(new GastoRecurrente("Factura de electricidad", LocalDate.of(2024, 2, 1)));
        agregarGastoRecurrente(new GastoRecurrente("Alquiler", LocalDate.of(2024, 2, 15)));

        programarRecordatorio();
    }

    private static void programarRecordatorio() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(RecordatorioGastosRecurrentes::recordarGastos, 0, 1, TimeUnit.DAYS);
    }
}

