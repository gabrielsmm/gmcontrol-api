package com.gabrielsmm.gmcontrol.controllers;

import com.gabrielsmm.gmcontrol.dtos.UsuarioPerfilAcessoDTO;
import com.gabrielsmm.gmcontrol.services.UsuarioPerfilService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios/{usuarioId}/perfis")
public class UsuarioPerfilController {

    private UsuarioPerfilService usuarioPerfilService;

    @GetMapping
    public ResponseEntity<List<UsuarioPerfilAcessoDTO>> getListaModulos(@PathVariable Long usuarioId) {
        List<UsuarioPerfilAcessoDTO> listaPerfis = usuarioPerfilService.getListaPerfis(usuarioId);
        return ResponseEntity.ok(listaPerfis);
    }

    @PutMapping
    public ResponseEntity<Void> atualizarAcesso(@PathVariable Long usuarioId,
                                                @RequestBody UsuarioPerfilAcessoDTO objDto) {
        usuarioPerfilService.atualizarAcesso(usuarioId, objDto);
        return ResponseEntity.noContent().build();
    }

}
