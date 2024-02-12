package org.example;

import static org.junit.Assert.*;

import org.example.CategorizadorGastos.CategoriaGasto;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


public class CalculadoraPresupuestoTest {
    private CalculadoraPresupuesto calculadora;
    private RegistroGastos registroGastos;

    @Before
    public void setUp() {
        calculadora = new CalculadoraPresupuesto();
        registroGastos = new RegistroGastos();
    }

    @Test
    public void testAsignarFondos() {
        calculadora.asignarFondos(CategoriaGasto.ALIMENTACION, 500.0);
        assertEquals(500.0, calculadora.getFondosPorCategoria().get(CategoriaGasto.ALIMENTACION), 0.01);

    }
    @Test
    public void testAjustarLimites() {
        registroGastos.agregarGasto(50.0, LocalDate.now(), "Alimentacion");
        calculadora.asignarFondos(CategoriaGasto.ALIMENTACION, 500.0);

        calculadora.ajustarLimites(registroGastos);

        assertEquals(490.0, registroGastos.getLimiteMensual(CategoriaGasto.ALIMENTACION), 0.01);
    }
}


