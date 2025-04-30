package br.upf.biblioteca.service;

import br.upf.biblioteca.dto.EmprestimoDTO;
import br.upf.biblioteca.repository.EmprestimoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	public EmprestimoDTO salvar(EmprestimoDTO dto) {
		return emprestimoRepository.save(dto);
	}
	
	public List<EmprestimoDTO> listarTodos(){		
		return emprestimoRepository.findAll();
	}
	
	public Optional<EmprestimoDTO> buscarPorId(Long id) {
		return emprestimoRepository.findById(id);
	}
	
	public void removerPorId(Long id) {
		emprestimoRepository.deleteById(id);
	}
	
}