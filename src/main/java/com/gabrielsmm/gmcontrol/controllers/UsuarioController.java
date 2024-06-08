package com.gabrielsmm.gmcontrol.controllers;

import com.gabrielsmm.gmcontrol.dtos.UsuarioRequestDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioResponseDTO;
import com.gabrielsmm.gmcontrol.services.UsuarioService;
import lombok.AllArgsConstructor;
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

    @GetMapping(value = "/usuario/{usuario}")
    public ResponseEntity<UsuarioResponseDTO> findByUsuario(@PathVariable String usuario) {
        UsuarioResponseDTO obj = usuarioService.findByUsuario(usuario);
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
    public ResponseEntity<Void> insert(@RequestBody UsuarioRequestDTO objDto) {
        UsuarioResponseDTO obj = usuarioService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UsuarioRequestDTO objDto) {
        usuarioService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
