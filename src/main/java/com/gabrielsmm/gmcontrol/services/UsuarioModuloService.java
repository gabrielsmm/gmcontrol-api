package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioModuloAcessoDTO;
import com.gabrielsmm.gmcontrol.entities.enums.Modulo;
import com.gabrielsmm.gmcontrol.repositories.UsuarioModuloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioModuloService {

    private UsuarioModuloRepository usuarioModuloRepository;

    private UsuarioService usuarioService;

    public List<UsuarioModuloAcessoDTO> getListaModulos(Long usuarioId) {
        usuarioService.findById(usuarioId);

        List<UsuarioModuloAcessoDTO> listaModulos = new ArrayList<>();

        for (Modulo modulo : Modulo.values()) {
            boolean possuiAcesso = possuiAcesso(usuarioId, modulo.getCodigo());
            listaModulos.add(new UsuarioModuloAcessoDTO(modulo.getCodigo(), modulo.getDescricao(), possuiAcesso));
        }

        return listaModulos;
    }

    public boolean possuiAcesso(Long usuarioId, Integer modulo) {
        return usuarioModuloRepository.findByUsuarioIdAndModulo(usuarioId, modulo).isPresent();
    }

}
