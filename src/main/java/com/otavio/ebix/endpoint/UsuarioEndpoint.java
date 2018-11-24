package com.otavio.ebix.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.otavio.ebix.crudWS.AddUsuarioRequest;
import com.otavio.ebix.crudWS.AddUsuarioResponse;
import com.otavio.ebix.crudWS.DeleteUsuarioRequest;
import com.otavio.ebix.crudWS.DeleteUsuarioResponse;
import com.otavio.ebix.crudWS.GetAllUsuariosResponse;
import com.otavio.ebix.crudWS.GetUsuarioByIdRequest;
import com.otavio.ebix.crudWS.GetUsuarioByIdResponse;
import com.otavio.ebix.crudWS.ServiceStatus;
import com.otavio.ebix.crudWS.UpdateUsuarioRequest;
import com.otavio.ebix.crudWS.UpdateUsuarioResponse;
import com.otavio.ebix.crudWS.UsuarioInfo;
import com.otavio.ebix.model.Usuario;
import com.otavio.ebix.service.IUsuarioService;

@Endpoint
public class UsuarioEndpoint {
	private static final String NAMESPACE_URI = "http://ebix.otavio.com/ws";
	@Autowired
	private IUsuarioService usuarioService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsuarioByIdRequest")
	@ResponsePayload
	public GetUsuarioByIdResponse getUsuario(@RequestPayload GetUsuarioByIdRequest request) {
		GetUsuarioByIdResponse response = new GetUsuarioByIdResponse();
		UsuarioInfo usuarioInfo = new UsuarioInfo();
		BeanUtils.copyProperties(usuarioService.getUsuarioById(request.getId()), usuarioInfo);
		response.setUsuario(usuarioInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsuariosRequest")
	@ResponsePayload
	public GetAllUsuariosResponse getAllUsuarios() {
		GetAllUsuariosResponse response = new GetAllUsuariosResponse();
		List<UsuarioInfo> usuarioInfoList = new ArrayList<>();
		List<Usuario> usuarioList = usuarioService.getAllUsuarios();
		for (int i = 0; i < usuarioList.size(); i++) {
			UsuarioInfo ob = new UsuarioInfo();
			BeanUtils.copyProperties(usuarioList.get(i), ob);
			usuarioInfoList.add(ob);
		}
		response.getUsuario().addAll(usuarioInfoList);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUsuarioRequest")
	@ResponsePayload
	public AddUsuarioResponse addUsuario(@RequestPayload AddUsuarioRequest request) {
		AddUsuarioResponse response = new AddUsuarioResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Usuario usuario = new Usuario();
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		boolean flag = usuarioService.addUsuario(usuario);

		if (flag == false) {
			serviceStatus.setStatusCode("CONFLITO");
			serviceStatus.setMensagem("Usuário já existe.");
			response.setServiceStatus(serviceStatus);
		} else {
			serviceStatus.setStatusCode("SUCESSO");
			serviceStatus.setMensagem("Usuário " + usuario.getNome() + " adicionado com sucesso!");
			response.setServiceStatus(serviceStatus);
		}

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUsuarioRequest")
	@ResponsePayload
	public UpdateUsuarioResponse updateUsuario(@RequestPayload UpdateUsuarioRequest request) {
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(request.getUsuario(), usuario);
		usuarioService.updateUsuario(usuario);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatusCode("SUCESSO");
		serviceStatus.setMensagem("Usuário atualizado com sucesso!");
		UpdateUsuarioResponse response = new UpdateUsuarioResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUsuarioRequest")
	@ResponsePayload
	public DeleteUsuarioResponse deleteUsuario(@RequestPayload DeleteUsuarioRequest request) {
		Usuario usuario = usuarioService.getUsuarioById(request.getId());
		ServiceStatus serviceStatus = new ServiceStatus();
		if (usuario == null) {
			serviceStatus.setStatusCode("FALHA");
			serviceStatus.setMensagem("Usuário não existe.");
		} else {
			usuarioService.deleteUsuario(usuario);
			serviceStatus.setStatusCode("SUCESSO");
			serviceStatus.setMensagem("Usuário deletado com sucesso!");
		}
		DeleteUsuarioResponse response = new DeleteUsuarioResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}
}