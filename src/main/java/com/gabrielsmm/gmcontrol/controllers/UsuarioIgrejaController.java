package com.gabrielsmm.gmcontrol.controllers;

import com.gabrielsmm.gmcontrol.dtos.UsuarioIgrejaAcessoDTO;
import com.gabrielsmm.gmcontrol.services.UsuarioIgrejaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios-igrejas")
public class UsuarioIgrejaController {

    private UsuarioIgrejaService usuarioIgrejaService;

    @GetMapping(value = "/lista-igrejas/{usuarioId}")
    public ResponseEntity<Page<UsuarioIgrejaAcessoDTO>> getListaIgrejas(
            @PathVariable Long usuarioId,
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="registrosPorPagina", defaultValue="10") Integer registrosPorPagina,
            @RequestParam(value="ordem", defaultValue="id") String ordem,
            @RequestParam(value="direcao", defaultValue="ASC") String direcao,
            @RequestParam(value="filtro", required = false) String pesquisa) {
        Page<UsuarioIgrejaAcessoDTO> listaIgrejasPaginadas = usuarioIgrejaService
                .getListaIgrejasPaginada(usuarioId, pagina, registrosPorPagina, ordem, direcao, pesquisa);
        return ResponseEntity.ok(listaIgrejasPaginadas);
    }

    @PostMapping(value = "/atualizar-acesso/{usuarioId}")
    public ResponseEntity<Void> atualizarAcesso(@PathVariable Long usuarioId, @RequestBody UsuarioIgrejaAcessoDTO objDto) {
        usuarioIgrejaService.atualizarAcesso(usuarioId, objDto);
        return ResponseEntity.noContent().build();
    }

}
