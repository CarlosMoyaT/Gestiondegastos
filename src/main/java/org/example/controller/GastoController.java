package org.example.controller;

import org.example.model.Gasto;
import org.example.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @GetMapping
    public List<Gasto> getAllGastos() {
        return GastoService.getAllGastos();
    }

    @PostMapping
    public void addGasto (@RequestBody Gasto gasto) {
        gastoService.addGasto(gasto);
    }


}
