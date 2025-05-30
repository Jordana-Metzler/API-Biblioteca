package br.upf.biblioteca.service;

import br.upf.biblioteca.dto.LivroDTO;
import br.upf.biblioteca.repository.LivroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
   
	public LivroDTO salvar(LivroDTO dto) {
		return livroRepository.save(dto);
	}
	
	public List<LivroDTO> listarTodos(){		
		return livroRepository.findAll();
	}
	
	public Optional<LivroDTO> buscarPorId(Long id) {
		return livroRepository.findById(id);
	}
	
	public void removerPorId(Long id) {
		livroRepository.deleteById(id);
	}
}
