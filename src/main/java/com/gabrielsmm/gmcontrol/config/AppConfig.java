package com.gabrielsmm.gmcontrol.config;

import com.gabrielsmm.gmcontrol.config.converters.UsuarioModuloToIntegerConverter;
import com.gabrielsmm.gmcontrol.dtos.UsuarioResponseDTO;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.getConfiguration().setPropertyCondition(context ->
            context.getSource() != null
        );

        // Mapeamento explícito entre Usuario e UsuarioResponseDTO
        TypeMap<Usuario, UsuarioResponseDTO> typeMap = modelMapper.createTypeMap(Usuario.class, UsuarioResponseDTO.class);

        // Aplicar o converter para UsuarioModulos
        typeMap.addMappings(mapper -> mapper.using(new UsuarioModuloToIntegerConverter())
                .map(Usuario::getUsuarioModulos, UsuarioResponseDTO::setUsuarioModulos));

        return modelMapper;
    }

}
