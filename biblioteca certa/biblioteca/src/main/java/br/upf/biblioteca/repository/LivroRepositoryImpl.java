package br.upf.biblioteca.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class LivroRepositoryImpl implements LivroRepository {

    @PersistenceContext
    protected EntityManager em;
    }
