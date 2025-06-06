package br.upf.biblioteca.service;

import br.upf.biblioteca.dto.UsuarioDTO;
import br.upf.biblioteca.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	 @Autowired
	    private UsuarioRepository usuarioRepository;
	    
	    public UsuarioDTO salvar(UsuarioDTO dto) {
			return usuarioRepository.save(dto);
		}
		
		public List<UsuarioDTO> listarTodos(){		
			return usuarioRepository.findAll();
		}
		
		public Optional<UsuarioDTO> buscarPorId(Long id) {
			return usuarioRepository.findById(id);
		}
		
		public void removerPorId(Long id) {
			usuarioRepository.deleteById(id);
		}
		
		public List<UsuarioDTO> buscarPorParteNome(String nome) {
			return usuarioRepository.findByNomeContaining(nome);
		} 
		
		public UsuarioDTO buscarPorEmail(String email) {
			return usuarioRepository.findByEmail(email);
		}
		
		public List<UsuarioDTO> buscarPorSenha(String senha) {
			return usuarioRepository.findBySenha(senha);
		}
		
}
