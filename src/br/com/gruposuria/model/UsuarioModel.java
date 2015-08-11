package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.UsuarioDAO;
import br.com.gruposuria.entity.Usuario;

@SuppressWarnings("serial")
@Stateless
public class UsuarioModel implements Serializable {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public List<Usuario> listaUsuario(){
		return usuarioDAO.listaTodos();
	}
	
	public boolean loginValido(Usuario usuario){
		
		Usuario u = usuarioDAO.consultarPorLoginESenha(usuario);
		System.out.println(u);	
		if(u!= null){
			return true;
		}
		
		return false;
	}
	
	
	
}
