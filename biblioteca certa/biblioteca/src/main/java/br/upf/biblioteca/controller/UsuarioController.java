package br.upf.biblioteca.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.upf.biblioteca.dto.UsuarioDTO;
import br.upf.biblioteca.service.UsuarioService;
import br.upf.biblioteca.utils.TokenJWT;

@RestController
@RequestMapping(value = "/biblioteca/usuarios")
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;
    

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = "/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO inserir(@RequestBody UsuarioDTO user,
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		return usuarioService.salvar(user);
	}
	
	@GetMapping(value = "/listarTodos")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioDTO> listarTodos(@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		return usuarioService.listarTodos();
	}
	
	@GetMapping(value = "/buscarPorId")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioDTO buscarPorId(@RequestHeader(value = "id") Long id, 
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		return usuarioService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não encontrado."));
	}

	@DeleteMapping(value = "/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerUsuario(@RequestHeader(value = "id") Long id, 
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		usuarioService.buscarPorId(id)
				.map(usuario -> { usuarioService.removerPorId(usuario.getId());
					return Void.TYPE;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não encontrado."));
	}
	
	@PutMapping(value = "/atualizar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@RequestBody UsuarioDTO user, 
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		usuarioService.buscarPorId(user.getId()).map(usuarioBase -> {
			modelMapper.map(user, usuarioBase);
			usuarioService.salvar(usuarioBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não encontrado."));
	}
	
	@GetMapping(value = "/buscarPorEmail")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioDTO findByEmail(@RequestHeader(value = "email") String email, 
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		return usuarioService.buscarPorEmail(email);
	}
	

	@GetMapping(value = "/buscarPorParteNome")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioDTO> buscarPorParteNome(@RequestHeader(value = "nome") String nome, 
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		return usuarioService.buscarPorParteNome(nome);
	}
	
	@GetMapping(value = "/buscarPorSenha")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioDTO> buscarPorSenha(@RequestHeader(value = "senha") String senha, 
			@RequestHeader(value = "token") String token) {
		TokenJWT.validarToken(token);
		return usuarioService.buscarPorSenha(senha);
	}

	@GetMapping(value = "/authorize")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioDTO authorize(@RequestHeader(value = "email") String email,
			@RequestHeader(value = "senha") String senha) {
		UsuarioDTO usuarioDTO; 
		if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
			usuarioDTO = usuarioService.buscarPorEmail(email);
			if (usuarioDTO != null) {
				if (usuarioDTO.getSenha().equals(senha)) {
					usuarioDTO.setToken(TokenJWT.processarTokenJWT(email));
					return usuarioDTO;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
}
