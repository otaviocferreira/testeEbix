package com.otavio.ebix.correiosRest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;

@Configuration
public class RestCorreiosConfig {

	@Bean
	public AtendeCliente atendeCliente() {
		return new AtendeClienteService().getAtendeClientePort();
	}
}
