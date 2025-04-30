package br.upf.biblioteca.controller;

import br.upf.biblioteca.dto.LivroDTO;
import org.modelmapper.ModelMapper;
import br.upf.biblioteca.service.LivroService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    
    @Autowired
	private ModelMapper modelMapper;
    
    @PostMapping(value = "/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public LivroDTO inserir(@RequestBody LivroDTO livroDTO) {
		return livroService.salvar(livroDTO);
	}

	@GetMapping(value = "/listarTodos")
	@ResponseStatus(HttpStatus.OK)
	public List<LivroDTO> listarTodos() {
		return livroService.listarTodos();
	}

	@GetMapping(value = "/buscarPorId")
	@ResponseStatus(HttpStatus.OK)
	public LivroDTO buscarPorId(@RequestHeader(value = "id") Long id) {
		return livroService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Livro não encontrado."));
	}

	@DeleteMapping(value = "/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void removerLivro(@RequestHeader(value = "id") Long id) {
		livroService.buscarPorId(id) 
				.map(livro -> { 
					livroService.removerPorId(livro.getId()); 
					return Void.TYPE;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Livro não encontrado."));
	}

	@PutMapping(value = "/atualizar")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void atualizar(@RequestBody LivroDTO livroDTO) {
		livroService.buscarPorId(livroDTO.getId()).map(livroBase -> {
			modelMapper.map(livroDTO, livroBase); 
			livroService.salvar(livroBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
				"Usuario não encontrado."));
}
}