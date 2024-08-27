package com.gabrielsmm.gmcontrol.repositories;

import com.gabrielsmm.gmcontrol.entities.Igreja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IgrejaRepository extends JpaRepository<Igreja, Long>, JpaSpecificationExecutor<Igreja> {

}
