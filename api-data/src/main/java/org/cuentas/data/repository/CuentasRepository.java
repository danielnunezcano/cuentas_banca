package org.cuentas.data.repository;

import java.util.List;

import org.cuentas.model.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CuentasRepository extends JpaRepository<Cuenta, Long>{
	
	public List<Cuenta> findByType(String type);
	@Transactional
	@Modifying
	@Query("DELETE FROM Cuenta c")
	public void deleteByTest();
}
