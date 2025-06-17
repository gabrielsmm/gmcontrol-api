package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioModuloAcessoDTO;
import com.gabrielsmm.gmcontrol.entities.Modulo;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import com.gabrielsmm.gmcontrol.entities.UsuarioModulo;
import com.gabrielsmm.gmcontrol.repositories.UsuarioModuloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioModuloService {

    private UsuarioModuloRepository usuarioModuloRepository;

    private UsuarioService usuarioService;

    private ModuloService moduloService;

    public List<UsuarioModuloAcessoDTO> getListaModulos(Long usuarioId) {
        usuarioService.find(usuarioId);

        List<UsuarioModuloAcessoDTO> listaModulos = new ArrayList<>();

        List<Modulo> modulosExistentes = moduloService.findAll();

        for (Modulo modulo : modulosExistentes) {
            boolean possuiAcesso = possuiAcesso(usuarioId, modulo.getId());
            listaModulos.add(new UsuarioModuloAcessoDTO(modulo.getId(), modulo.getNome(), possuiAcesso));
        }

        return listaModulos;
    }

    public void atualizarAcesso(Long usuarioId, UsuarioModuloAcessoDTO objDto) {
        Usuario usuario = usuarioService.find(usuarioId);
        Modulo modulo = moduloService.find(objDto.getCodigo());

        Optional<UsuarioModulo> usuarioModuloOpt = usuarioModuloRepository.findByUsuarioIdAndModuloId(usuarioId, modulo.getId());

        if (objDto.isPossuiAcesso()) {
            if (usuarioModuloOpt.isEmpty()) {
                UsuarioModulo usuarioModulo = new UsuarioModulo();
                usuarioModulo.setUsuario(usuario);
                usuarioModulo.setModulo(modulo);

                usuario.getUsuarioModulos().add(usuarioModulo);
                usuarioModuloRepository.save(usuarioModulo);
            }
        } else {
            if (usuarioModuloOpt.isPresent()) {
                usuario.getUsuarioModulos().remove(usuarioModuloOpt.get());
                usuarioModuloRepository.delete(usuarioModuloOpt.get());
            }
        }
    }

    public boolean possuiAcesso(Long usuarioId, Integer moduloId) {
        return usuarioModuloRepository.findByUsuarioIdAndModuloId(usuarioId, moduloId).isPresent();
    }

}
