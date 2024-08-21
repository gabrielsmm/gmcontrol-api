package com.gabrielsmm.gmcontrol.repositories.specifications;

import com.gabrielsmm.gmcontrol.entities.Usuario;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {

    public static Specification<Usuario> contemTextoNosAtributos(String texto) {
        return (root, query, builder) -> {
            String likePadrao = "%" + texto.toLowerCase() + "%";
            return builder.or(
                    builder.like(builder.toString(root.get("id")), likePadrao),
                    builder.like(builder.lower(root.get("nome")), likePadrao),
                    builder.like(builder.lower(root.get("nomeUsuario")), likePadrao),
                    builder.like(builder.lower(root.get("email")), likePadrao)
            );
        };
    }

}
