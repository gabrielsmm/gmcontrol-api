package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.entities.Perfil;
import com.gabrielsmm.gmcontrol.repositories.PerfilRepository;
import com.gabrielsmm.gmcontrol.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public Perfil find(Integer id) {
        Optional<Perfil> obj = perfilRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Perfil.class.getName()));
    }

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

}
