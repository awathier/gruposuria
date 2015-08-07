package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.GestaoContatoDAO;
import br.com.gruposuria.dao.UsuarioDAO;
import br.com.gruposuria.entity.GestaoContato;
import br.com.gruposuria.entity.Usuario;

@SuppressWarnings("serial")
@Stateless
public class GestaoContatoModel implements Serializable {

	@Inject
	private GestaoContatoDAO gestaoContatoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	public List<Usuario> listaUsuario(){
		return usuarioDAO.listaTodos();
	}
	
	public GestaoContato consultarPorCodigo(long codigo){
		return gestaoContatoDAO.consultarPorCodigo(codigo);
	}
	
	public List<GestaoContato> consultarPorNome(String nome){
		return gestaoContatoDAO.consultarPorNome(nome);
	}
	
	public GestaoContato salvar(GestaoContato gestaoContato){
		try {
			gestaoContato = gestaoContatoDAO.incluir(gestaoContato);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return gestaoContato;
	}
	
	public GestaoContato alterar(GestaoContato gestaoContato){
		try {
			gestaoContato = gestaoContatoDAO.alterar(gestaoContato);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return gestaoContato;
	}

	public GestaoContato excluir(GestaoContato gestaoContato){
		try {
			gestaoContatoDAO.excluir(gestaoContato.getCodigo());
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return gestaoContato;
	}

	public List<GestaoContato> listaGestaoContatos(){
		return gestaoContatoDAO.listaTodos();
	}
	
	public List<GestaoContato> listar(GestaoContato gestaoContato){
		return gestaoContatoDAO.listar(gestaoContato);
	}
	
}
