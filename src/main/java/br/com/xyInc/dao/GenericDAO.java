package br.com.xyInc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.Valid;

/**
 * 
 * Classe responsável pelos métodos do CRUD
 * 
 * @author Tha1208
 * 
 * @since 20/05/2017
 *
 */
public abstract class GenericDAO<T, I extends Serializable> {

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	private Class<T> persistedClass;

	protected GenericDAO() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db");
		this.entityManager = this.entityManagerFactory.createEntityManager();				
	}

	protected GenericDAO(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}

	public T salvar(@Valid T entity) {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		entityManager.flush();
		t.commit();
		return entity;
	}

	public T atualizar(@Valid T entity) {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.merge(entity);
		entityManager.flush();
		t.commit();
		return entity;
	}

	public void remover(I id) {
		T entity = encontrar(id);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		T mergedEntity = entityManager.merge(entity);
		entityManager.remove(mergedEntity);
		entityManager.flush();
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {	
		return this.entityManager.createQuery("SELECT p FROM " + persistedClass.getName()+" p ").getResultList();
	}
		
	public T encontrar(I id) {
		return entityManager.find(persistedClass, id);
	}
}