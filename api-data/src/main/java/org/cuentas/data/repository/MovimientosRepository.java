package org.cuentas.data.repository;

import java.util.List;

import org.cuentas.model.entity.Cuenta;
import org.cuentas.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MovimientosRepository extends JpaRepository<Movimiento, Long>{
	
	public List<Movimiento> findByOriginAccount(Cuenta cuenta);
	@Transactional
	@Modifying
	@Query("DELETE FROM Movimiento m")
	public void deleteByTest();
}
