package com.gabrielsmm.gmcontrol.controllers;

import com.gabrielsmm.gmcontrol.dtos.IgrejaDTO;
import com.gabrielsmm.gmcontrol.services.IgrejaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/igrejas")
public class IgrejaController {

    private IgrejaService igrejaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<IgrejaDTO> find(@PathVariable Long id) {
        IgrejaDTO obj = igrejaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid IgrejaDTO objDto) {
        IgrejaDTO obj = igrejaService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid IgrejaDTO objDto) {
        igrejaService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        igrejaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/lista-paginada")
    public ResponseEntity<Page<IgrejaDTO>> getListaPaginada(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="registrosPorPagina", defaultValue="10") Integer registrosPorPagina,
            @RequestParam(value="ordem", defaultValue="id") String ordem,
            @RequestParam(value="direcao", defaultValue="DESC") String direcao,
            @RequestParam(value="filtro", required = false) String filtro) {
        Page<IgrejaDTO> list = igrejaService.getListaPaginada(pagina, registrosPorPagina, ordem, direcao, filtro);
        return ResponseEntity.ok().body(list);
    }

}
