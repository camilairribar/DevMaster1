package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService ColaboradorService;

    @GetMapping
    public List<Colaborador> obtenerTodos() {
        return ColaboradorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Colaborador obtenerPorId(@PathVariable Integer id) {
        return ColaboradorService.obtenerPorId(id);
    }

    @PostMapping
    public Colaborador crearColaborador(@RequestBody Colaborador colaborador) {
        return ColaboradorService.guardar(colaborador);
    }

    @DeleteMapping("/{id}")
    public void eliminarColaborador(@PathVariable Integer id) {
        ColaboradorService.eliminar(id);
    }
}
