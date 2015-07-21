package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.InstituicaoDAO;
import br.com.gruposuria.entity.Instituicao;

@SuppressWarnings("serial")
@Stateless
public class InstituicaoModel implements Serializable {

	@Inject
	private InstituicaoDAO instituicaoDAO;
	
	public List<Instituicao> consultarPorNome(String nome){
		return instituicaoDAO.consultarPorNome(nome);
	}
	
	public Instituicao consultarPorCodigo(Long id){
		return instituicaoDAO.consultarPorCodigo(id);
	}
	
	public Instituicao salvar(Instituicao instituicao){
		try {
			instituicao = instituicaoDAO.incluir(instituicao);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return instituicao;
	}
	
	public Instituicao alterar(Instituicao instituicao){
		try {
			instituicao = instituicaoDAO.alterar(instituicao);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return instituicao;
	}

	public Instituicao excluir(Instituicao instituicao){
		try {
			instituicaoDAO.excluir(instituicao.getCodigo());
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return instituicao;
	}

	public List<Instituicao> listaInstituicoes(){
		return instituicaoDAO.listaTodos();
	}
	
	public List<Instituicao> listaInstituicoesPorNome(String nome){
		return instituicaoDAO.consultarPorNome(nome);
	}
	
	public List<Instituicao> listar(Instituicao instituicao){
		return instituicaoDAO.listar(instituicao);
	}
	
}
