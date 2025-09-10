package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioModuloAcessoDTO;
import com.gabrielsmm.gmcontrol.entities.Modulo;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioModuloAcessoService {

    private UsuarioService usuarioService;

    private ModuloService moduloService;

    public List<UsuarioModuloAcessoDTO> getListaModulos(Long usuarioId) {
        Usuario usuario = usuarioService.find(usuarioId);

        List<UsuarioModuloAcessoDTO> listaModulos = new ArrayList<>();
        List<Modulo> modulosExistentes = moduloService.findAll();

        for (Modulo modulo : modulosExistentes) {
            boolean possuiAcesso = usuario.getModulos().contains(modulo);
            listaModulos.add(new UsuarioModuloAcessoDTO(modulo.getId(), modulo.getNome(), possuiAcesso));
        }

        return listaModulos;
    }

    public void atualizarAcesso(Long usuarioId, UsuarioModuloAcessoDTO objDto) {
        Usuario usuario = usuarioService.find(usuarioId);
        Modulo modulo = moduloService.find(objDto.getCodigo());

        if (objDto.isPossuiAcesso()) {
            usuario.getModulos().add(modulo);
        } else {
            usuario.getModulos().remove(modulo);
        }

        usuarioService.save(usuario);
    }

    public boolean possuiAcesso(Long usuarioId, Integer moduloId) {
        Usuario usuario = usuarioService.find(usuarioId);
        return usuario.getModulos().stream()
                .anyMatch(m -> m.getId().equals(moduloId));
    }

}
