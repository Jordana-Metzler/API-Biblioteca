package br.upf.biblioteca.repository;

import br.upf.biblioteca.dto.LivroDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroDTO, Long> {

}    