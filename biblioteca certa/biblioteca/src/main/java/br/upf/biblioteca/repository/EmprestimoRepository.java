package br.upf.biblioteca.repository;


import br.upf.biblioteca.dto.EmprestimoDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<EmprestimoDTO, Long> {

    // Consulta com JPQL para buscar empréstimos por ID do usuário
    @Query("SELECT e FROM EmprestimoDTO e WHERE e.usuarioId = ?1")
    List<EmprestimoDTO> findByUsuarioIdJPQL(Long usuarioId);

    // Consulta nativa para buscar empréstimos por ID do usuário
    @Query(value = "SELECT * FROM tb_emprestimo WHERE emp_usuario_id = ?1", nativeQuery = true)
    List<EmprestimoDTO> findByUsuarioIdNative(Long usuarioId);
}