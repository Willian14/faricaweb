package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.Dao.UsuarioDao;
import br.com.fabricadeprogramador.entity.Usuario;
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	
	
	public UsuarioController(){
		System.out.println("construtor..");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init...");
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usu = new Usuario();
		if(id != null)
			usu.setId(Integer.parseInt(id));
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
	
		UsuarioDao usuDao = new UsuarioDao();
		usuDao.salvar(usu);
		
		resp.getWriter().print("<h1>sucesso!!!!!</h1>");
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		
		if(acao.equals("excluir")){
			String id = req.getParameter("id");
			
			UsuarioDao usuDao = new UsuarioDao();
			Usuario usu = new Usuario();
			if(id != null)
				usu.setId(Integer.parseInt(id));
			usuDao.excluir(usu);
		    resp.getWriter().print("<h1> Excluido com sucesso</h1>");
		}else if(acao.equals("listar")){
			UsuarioDao usuDao = new UsuarioDao();
			List<Usuario> lista = usuDao.buscarTodos();
			
			req.setAttribute("lista", lista);
			RequestDispatcher dispacher = req.getRequestDispatcher("WEB-INF/listaUsu.jsp");
			dispacher.forward(req, resp);
		}
	
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy..");
		super.destroy();
	}
}
