package com.gabrielsmm.gmcontrol.repositories;

import com.gabrielsmm.gmcontrol.entities.UsuarioIgreja;
import com.gabrielsmm.gmcontrol.entities.UsuarioIgrejaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioIgrejaRepository extends JpaRepository<UsuarioIgreja, UsuarioIgrejaId> {
}
