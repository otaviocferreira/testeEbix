package com.otavio.ebix.correiosRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.ServicoAdicionalXML;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;

@RestController("/correios")
public class Correios {
	
	@Autowired
	public AtendeCliente atendeCliente;

	@GetMapping("/consultaCEP/{cep}")
	public EnderecoERP consultaCEP(@PathVariable("cep") String cep){
        try {
			return atendeCliente.consultaCEP(cep);
		} catch (SQLException_Exception | SigepClienteException e) {
			e.printStackTrace();
			return null;
		}
    }

	@GetMapping("/buscaServicosAdicionaisAtivos")
	public List<ServicoAdicionalXML> buscaServicosAdicionaisAtivos(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha){
        try {
			return atendeCliente.buscaServicosAdicionaisAtivos(usuario, senha);
		} catch (SQLException_Exception | SigepClienteException e) {
			e.printStackTrace();
			return null;
		}
    }
}
