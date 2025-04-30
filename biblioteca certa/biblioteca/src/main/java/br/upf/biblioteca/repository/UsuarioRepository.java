package br.upf.biblioteca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.upf.biblioteca.dto.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Long> {

	   public UsuarioDTO findByEmail(String email);

	   public List<UsuarioDTO> findByNomeContaining(String nome);

	    @Query("SELECT u FROM UsuarioDTO u WHERE u.senha = :senha ORDER BY u.id DESC")
	    List<UsuarioDTO> findBySenha(@Param("senha") String senha);
	}
