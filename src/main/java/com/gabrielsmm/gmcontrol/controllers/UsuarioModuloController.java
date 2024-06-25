package com.gabrielsmm.gmcontrol.controllers;

import com.gabrielsmm.gmcontrol.dtos.UsuarioModuloAcessoDTO;
import com.gabrielsmm.gmcontrol.services.UsuarioModuloService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios-modulos")
public class UsuarioModuloController {

    private UsuarioModuloService usuarioModuloService;

    @GetMapping(value = "/lista-modulos/{usuarioId}")
    public ResponseEntity<List<UsuarioModuloAcessoDTO>> getListaModulos(@PathVariable Long usuarioId) {
        List<UsuarioModuloAcessoDTO> listaModulos = usuarioModuloService.getListaModulos(usuarioId);
        return ResponseEntity.ok(listaModulos);
    }

}
