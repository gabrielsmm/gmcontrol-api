package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioPerfilAcessoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioPerfilService {

    private UsuarioService usuarioService;

    private PerfilService perfilService;

    public List<UsuarioPerfilAcessoDTO> getListaPerfis(Long usuarioId) {
        var usuario = usuarioService.find(usuarioId);
        var perfisExistentes = perfilService.findAll();

        List<UsuarioPerfilAcessoDTO> listaPerfis = new ArrayList<>();

        for (var perfil : perfisExistentes) {
            boolean possuiAcesso = usuario.getPerfis().contains(perfil);
            listaPerfis.add(new UsuarioPerfilAcessoDTO(perfil.getId(), perfil.getNome(), perfil.getDescricao(), possuiAcesso));
        }

        return listaPerfis;
    }

    public void atualizarAcesso(Long usuarioId, UsuarioPerfilAcessoDTO objDto) {
        var usuario = usuarioService.find(usuarioId);
        var perfil = perfilService.find(objDto.getId());

        if (objDto.isPossuiAcesso()) {
            usuario.getPerfis().add(perfil);
        } else {
            usuario.getPerfis().remove(perfil);
        }

        usuarioService.save(usuario);
    }

}
