package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.entities.Modulo;
import com.gabrielsmm.gmcontrol.repositories.ModuloRepository;
import com.gabrielsmm.gmcontrol.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModuloService {

    private ModuloRepository moduloRepository;

    public Modulo find(Integer id) {
        Optional<Modulo> obj = moduloRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Modulo.class.getName()));
    }

    public List<Modulo> findAll() {
        return moduloRepository.findAll();
    }

}
