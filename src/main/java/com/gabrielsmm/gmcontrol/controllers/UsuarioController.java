package com.gabrielsmm.gmcontrol.controllers;

import com.gabrielsmm.gmcontrol.dtos.UsuarioInsertRequestDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioResponseDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioUpdateRequestDTO;
import com.gabrielsmm.gmcontrol.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> find(@PathVariable Long id) {
        UsuarioResponseDTO obj = usuarioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/usuario/{nomeUsuario}")
    public ResponseEntity<UsuarioResponseDTO> findByNomeUsuario(@PathVariable String nomeUsuario) {
        UsuarioResponseDTO obj = usuarioService.findByNomeUsuario(nomeUsuario);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> findByEmail(@PathVariable String email) {
        UsuarioResponseDTO obj = usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/usuario-logado")
    public ResponseEntity<UsuarioResponseDTO> findUsuarioLogado() {
        UsuarioResponseDTO obj = usuarioService.findUsuarioLogado();
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid UsuarioInsertRequestDTO objDto) {
        UsuarioResponseDTO obj = usuarioService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateRequestDTO objDto) {
        usuarioService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/lista-paginada")
    public ResponseEntity<Page<UsuarioResponseDTO>> getListaPaginada(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="registrosPorPagina", defaultValue="10") Integer registrosPorPagina,
            @RequestParam(value="ordem", defaultValue="id") String ordem,
            @RequestParam(value="direcao", defaultValue="DESC") String direcao,
            @RequestParam(value="filtro", required = false) String filtro) {
        Page<UsuarioResponseDTO> list = usuarioService.getListaPaginada(pagina, registrosPorPagina, ordem, direcao, filtro);
        return ResponseEntity.ok().body(list);
    }

}
