package br.upf.biblioteca.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public abstract class EmprestimoRepositoryImpl implements EmprestimoRepository {
	
	@PersistenceContext
	private EntityManager em;
}
