package com.gabrielsmm.gmcontrol.config.converters;

import com.gabrielsmm.gmcontrol.entities.UsuarioModulo;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioModuloToIntegerConverter implements Converter<Collection<UsuarioModulo>, Set<Integer>> {

    @Override
    public Set<Integer> convert(MappingContext<Collection<UsuarioModulo>, Set<Integer>> context) {
        return context.getSource().stream()
                .map(UsuarioModulo::getModulo)
                .collect(Collectors.toSet());
    }

}
