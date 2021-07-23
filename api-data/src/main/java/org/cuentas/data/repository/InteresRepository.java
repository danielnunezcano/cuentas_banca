package org.cuentas.data.repository;

import java.util.List;

import org.cuentas.model.entity.Cuenta;
import org.cuentas.model.entity.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface InteresRepository extends JpaRepository<Interes, Long>{
	
	public List<Interes> findByCuentaOrderByDateDesc(Cuenta cuenta);
	@Transactional
	@Modifying
	@Query("delete from Interes as i")
	public void deleteByTest();
	
}
