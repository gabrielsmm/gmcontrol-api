package com.gabrielsmm.gmcontrol.repositories.specifications;

import com.gabrielsmm.gmcontrol.entities.Igreja;
import org.springframework.data.jpa.domain.Specification;

public class IgrejaSpecification {

    public static Specification<Igreja> contemTextoNosAtributos(String texto) {
        return (root, query, builder) -> {
            String likePadrao = "%" + texto.toLowerCase() + "%";
            return builder.or(
                    builder.like(builder.toString(root.get("id")), likePadrao),
                    builder.like(builder.lower(root.get("nome")), likePadrao)
            );
        };
    }

}
