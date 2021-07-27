package org.cuentas.core.threads;

import java.util.List;
import java.util.concurrent.Callable;

import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.core.mapper.CuentaMapper;
import org.cuentas.data.repository.CuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HiloCorriente implements Callable<List<CuentaDto>> {
	
	private final CuentasRepository cuentasRepository;

	@Override
	public List<CuentaDto> call() throws Exception {
		return CuentaMapper.INSTANCE.cuentaToCuentaDtos(this.cuentasRepository.findByType(TypeAccount.CORRIENTE.name()));
	}
	
}

