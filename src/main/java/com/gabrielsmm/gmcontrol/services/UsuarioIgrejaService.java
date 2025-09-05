package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.IgrejaDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioIgrejaAcessoDTO;
import com.gabrielsmm.gmcontrol.entities.Igreja;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import com.gabrielsmm.gmcontrol.entities.UsuarioIgreja;
import com.gabrielsmm.gmcontrol.entities.UsuarioIgrejaId;
import com.gabrielsmm.gmcontrol.repositories.UsuarioIgrejaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioIgrejaService {

    private UsuarioIgrejaRepository usuarioIgrejaRepository;

    private UsuarioService usuarioService;

    private IgrejaService igrejaService;

    private ModelMapper modelMapper;

    public Page<UsuarioIgrejaAcessoDTO> getListaIgrejasPaginada(Long usuarioId, Integer pagina, Integer registrosPorPagina, String ordem, String direcao, String pesquisa) {
        usuarioService.find(usuarioId);

        Page<IgrejaDTO> igrejasPaginadas = igrejaService.getListaPaginada(pagina, registrosPorPagina, ordem, direcao, pesquisa);

        return igrejasPaginadas.map(igreja -> {
            boolean possuiAssociacao = possuiAssociacao(usuarioId, igreja.getId());
            return new UsuarioIgrejaAcessoDTO(igreja, possuiAssociacao);
        });
    }

    public void atualizarAcesso(Long usuarioId, UsuarioIgrejaAcessoDTO objDto) {
        Usuario usuario = usuarioService.find(usuarioId);
        Igreja igreja = igrejaService.find(objDto.getIgreja().getId());

        UsuarioIgrejaId id = new UsuarioIgrejaId(usuarioId, igreja.getId());
        Optional<UsuarioIgreja> usuarioIgrejaOpt = usuarioIgrejaRepository.findById(id);

        if (objDto.isPossuiAcesso()) {
            if (usuarioIgrejaOpt.isEmpty()) {
                UsuarioIgreja usuarioIgreja = new UsuarioIgreja(usuario, igreja);
                usuarioIgrejaRepository.save(usuarioIgreja);
            }
        } else {
            usuarioIgrejaOpt.ifPresent(usuarioIgrejaRepository::delete);
        }
    }

    public boolean possuiAssociacao(Long usuarioId, Long igrejaId) {
        return usuarioIgrejaRepository.existsById(new UsuarioIgrejaId(usuarioId, igrejaId));
    }

}
