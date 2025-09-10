package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.PerfilDTO;
import com.gabrielsmm.gmcontrol.repositories.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public List<PerfilDTO> listarPerfis() {
        return perfilRepository.findAll()
                .stream()
                .map(PerfilDTO::new)
                .toList();
    }

}
