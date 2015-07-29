package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.InstrutorDAO;
import br.com.gruposuria.entity.Instrutor;

@SuppressWarnings("serial")
@Stateless
public class InstrutorModel implements Serializable {

	@Inject
	private InstrutorDAO instrutorDAO;
	
	public List<Instrutor> consultarPorNome(String nome){
		return instrutorDAO.consultarPorNome(nome);
	}
	
	public Instrutor consultarPorCodigo(Long id){
		return instrutorDAO.consultarPorCodigo(id);
	}
	
	public Instrutor salvar(Instrutor instrutor){
		try {
			instrutor = instrutorDAO.incluir(instrutor);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return instrutor;
	}
	
	public Instrutor alterar(Instrutor instrutor){
		try {
			instrutor = instrutorDAO.alterar(instrutor);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return instrutor;
	}

	public Instrutor excluir(Instrutor instrutor){
		try {
			instrutorDAO.excluir(instrutor.getCodigo());
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return instrutor;
	}

	public List<Instrutor> listaInstrutores(){
		return instrutorDAO.listaTodos();
	}
	
	public List<Instrutor> listar(Instrutor instrutor){
		return instrutorDAO.listar(instrutor);
	}
	
}
