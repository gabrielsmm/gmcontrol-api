package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.IgrejaDTO;
import com.gabrielsmm.gmcontrol.entities.Igreja;
import com.gabrielsmm.gmcontrol.repositories.IgrejaRepository;
import com.gabrielsmm.gmcontrol.repositories.specifications.IgrejaSpecification;
import com.gabrielsmm.gmcontrol.services.exceptions.DataIntegrityException;
import com.gabrielsmm.gmcontrol.services.exceptions.ObjectNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class IgrejaService {

    private IgrejaRepository igrejaRepository;

    private ModelMapper modelMapper;

    public Igreja find(Long id) {
        Optional<Igreja> obj = igrejaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Igreja.class.getName()));
    }

    public IgrejaDTO findById(Long id) {
        Igreja igreja = igrejaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Igreja não encontrada com o ID: " + id));

        return modelMapper.map(igreja, IgrejaDTO.class);
    }

    public IgrejaDTO insert(IgrejaDTO objDto) {
        Igreja igreja = modelMapper.map(objDto, Igreja.class);
        igreja.setId(null);
        try {
            igreja = igrejaRepository.save(igreja);
            return modelMapper.map(igreja, IgrejaDTO.class);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public IgrejaDTO update(Long id, IgrejaDTO objDto) {
        Igreja igreja = find(id);
        modelMapper.map(objDto, igreja);
        igreja = igrejaRepository.save(igreja);
        return modelMapper.map(igreja, IgrejaDTO.class);
    }

    public void delete(Long id) {
        find(id);
        try {
            igrejaRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível excluir, erro de integridade de dados");
        }
    }

    public Page<IgrejaDTO> getListaPaginada(Integer pagina, Integer registrosPorPagina, String ordem, String direcao, String filtro) {
        PageRequest pageRequest = PageRequest.of(pagina, registrosPorPagina, Sort.Direction.valueOf(direcao), ordem);
        Page<Igreja> igrejasPage;

        if (StringUtils.isNotBlank(filtro)) {
            igrejasPage = igrejaRepository.findAll(IgrejaSpecification.contemTextoNosAtributos(filtro), pageRequest);
        } else {
            igrejasPage = igrejaRepository.findAll(pageRequest);
        }

        return igrejasPage.map(igreja -> modelMapper.map(igreja, IgrejaDTO.class));
    }

}
