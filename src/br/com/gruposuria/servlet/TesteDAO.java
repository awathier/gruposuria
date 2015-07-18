package br.com.gruposuria.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.model.AlunoModel;

@WebServlet("/TesteDAO")
public class TesteDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AlunoModel alunoModel;
	
	private ServletOutputStream outputStream;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesteDAO() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("operacao");
		String valor = request.getParameter("valor");
		
		try {
			outputStream = response.getOutputStream();
			outputStream.print("Operacao: " + operacao);
			outputStream.print("Valor: " + valor);
			
			Aluno aluno = alunoModel.consultarPorCpf(valor);
			
			outputStream.print("\nResultado: " + aluno);
		} catch (Exception e){
			System.out.println("\nErro:" + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		outputStream = response.getOutputStream();
		outputStream.print("Teste POST");
	}

}
