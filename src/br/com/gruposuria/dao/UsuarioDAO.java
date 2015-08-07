package br.com.gruposuria.dao;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.gruposuria.entity.Usuario;

@Named
@RequestScoped
public class UsuarioDAO extends DAO<Usuario> {

	public UsuarioDAO() {

	}

	@Override
	protected Class<Usuario> novoTipoDeObjetoPersistente() {
		return Usuario.class;
	}

	public List<Usuario> listaTodos() {
		TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.listaTodos", Usuario.class);

		List<Usuario> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}
		
	public Usuario consultarPorLoginESenha(Usuario usuario){
		TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.consultaPorLoginESenha", Usuario.class);
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());

		Usuario u = null;
		
		try{
			u = (Usuario)query.getSingleResult();
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return u;	
	}
	
}
