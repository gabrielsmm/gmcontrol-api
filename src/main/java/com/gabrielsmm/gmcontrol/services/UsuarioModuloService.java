package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioModuloAcessoDTO;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import com.gabrielsmm.gmcontrol.entities.UsuarioModulo;
import com.gabrielsmm.gmcontrol.entities.enums.Modulo;
import com.gabrielsmm.gmcontrol.repositories.UsuarioModuloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UsuarioModuloService {

    private UsuarioModuloRepository usuarioModuloRepository;

    private UsuarioService usuarioService;

    public List<UsuarioModuloAcessoDTO> getListaModulos(Long usuarioId) {
        usuarioService.find(usuarioId);

        List<UsuarioModuloAcessoDTO> listaModulos = new ArrayList<>();

        for (Modulo modulo : Modulo.values()) {
            boolean possuiAcesso = possuiAcesso(usuarioId, modulo.getCodigo());
            listaModulos.add(new UsuarioModuloAcessoDTO(modulo.getCodigo(), modulo.getDescricao(), possuiAcesso));
        }

        return listaModulos;
    }

    public void atualizarAcesso(Long usuarioId, UsuarioModuloAcessoDTO objDto) {
        Usuario usuario = usuarioService.find(usuarioId);
        Modulo modulo = Modulo.toEnum(objDto.getCodigo());

        Optional<UsuarioModulo> usuarioModuloOpt = usuarioModuloRepository.findByUsuarioIdAndModulo(usuarioId, modulo.getCodigo());

        if (objDto.isPossuiAcesso()) {
            if (usuarioModuloOpt.isEmpty()) {
                UUID chaveAgrupamento = UUID.randomUUID();

                UsuarioModulo usuarioModulo = new UsuarioModulo();
                usuarioModulo.setUsuario(usuario);
                usuarioModulo.setModulo(modulo.getCodigo());
                usuarioModulo.setChaveAgrupamento(chaveAgrupamento);

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

    public boolean possuiAcesso(Long usuarioId, Integer modulo) {
        return usuarioModuloRepository.findByUsuarioIdAndModulo(usuarioId, modulo).isPresent();
    }

}
