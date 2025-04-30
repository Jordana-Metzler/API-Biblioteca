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
import br.upf.biblioteca.dto.EmprestimoDTO;
import br.upf.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/biblioteca/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = "/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public EmprestimoDTO inserir(@RequestBody EmprestimoDTO emprestimoDTO) {
		return emprestimoService.salvar(emprestimoDTO);
	}

	@GetMapping(value = "/listarTodos")
	@ResponseStatus(HttpStatus.OK)
	public List<EmprestimoDTO> listarTodos() {
		return emprestimoService.listarTodos();
	}

	@GetMapping(value = "/buscarPorId")
	@ResponseStatus(HttpStatus.OK)
	public EmprestimoDTO buscarPorId(@RequestHeader(value = "id") Long id) {
		return emprestimoService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Empréstimo não encontrado."));
	}

	@DeleteMapping(value = "/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void removerUsuario(@RequestHeader(value = "id") Long id) {
		emprestimoService.buscarPorId(id) 
				.map(emprestimo -> {
					emprestimoService.removerPorId(emprestimo.getId());
					return Void.TYPE;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Usuario não encontrado."));
	}

	@PutMapping(value = "/atualizar")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void atualizar(@RequestBody EmprestimoDTO emprestimoDTO) {
		emprestimoService.buscarPorId(emprestimoDTO.getId()).map(emprestimoBase -> {
			modelMapper.map(emprestimoDTO, emprestimoBase); 
			emprestimoService.salvar(emprestimoBase); 
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
				"Usuario não encontrado."));
   
}
}