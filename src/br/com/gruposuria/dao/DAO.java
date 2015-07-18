package br.com.gruposuria.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


public abstract class DAO<T> {
	private Class<T> tipoDeObjetoPersistente;
	
	@PersistenceContext(name="gruposuria", type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public DAO() {
	}
	
	/**
	 * Remove um objeto persistente da sessão deixando-o transient em uma sessão.
	 */
	public void removerDaSessao(T object) {
		entityManager.detach(object);
	}

	/**
	 * Atualiza o objeto com os dados do banco.
	 */
	public void refresh(T object){
		entityManager.refresh(object);
	}
	
	/**
	 * Provoca a ocorrencia das operacoes que ja ocorreram ate o ponto de chamada deste metodo
	 */
	public void flush(){
		entityManager.flush();
	}
	
	/**
	 * Limpa o cache 1º nível
	 */
	public void clear(){
		entityManager.clear();
	}
	

	/**
	 * Inclusão do TO.
	 * 
	 * @param to
	 *            transfer object
	 * @return 
	 * @return objeto
	 */	
	public T incluir(T t) {		
		entityManager.persist(t);
		return t;
	}
	
	/**
	 * Alteração da entity.
	 * 
	 * @param to transfer object.
	 */
	public T alterar(T t) {
		entityManager.merge(t);
		return t;
	}
	
	/**
	 * Exclusão do transfer object.
	 * 
	 * @param chave primaria
	 */
	public void excluir(long object) {		
		T removeTO = consultar(object);
		entityManager.remove(entityManager.merge(removeTO));
	}
	
	public void remove(T t) {
		entityManager.remove(entityManager.merge(t));
	}
	
	/**
	 * Consultar TO pela Chave.
	 * 
	 * @param to
	 *            transfer object.
	 * @return proxy do objeto.
	 */	
	public T consultar(long id) {		
		return (T) entityManager.find(getTipoDeObjetoPersistente(), id);		
	}
	
	/**
	 * Consultar TO pela Chave de modo Lazy
	 * 
	 * @param to
	 *            transfer object.
	 * @return proxy do objeto.
	 */	
	public T consultarLazy(long id) {		
		return (T) entityManager.getReference(getTipoDeObjetoPersistente(), id);		
	}

	/**
	 * @return novo tipo de objeto persistente.
	 */	
	protected abstract Class<T> novoTipoDeObjetoPersistente();

	/**
	 * @return tipo de objeto persistente.
	 */	
	protected Class<T> getTipoDeObjetoPersistente() {
		if (tipoDeObjetoPersistente == null) {
			tipoDeObjetoPersistente = novoTipoDeObjetoPersistente();
		}
		return tipoDeObjetoPersistente;
	}

	/**
	 * @return tipo de objeto persistente.
	 */
	protected Class<?> getTipoDeObjetoPersistente(T to) {
		return to.getClass();
	}



	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}



	/**
	 * @param entityManager the entityManager to set
	 */
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
