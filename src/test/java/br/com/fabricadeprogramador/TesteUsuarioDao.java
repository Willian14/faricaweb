package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.Dao.UsuarioDao;
import br.com.fabricadeprogramador.entity.Usuario;

public class TesteUsuarioDao {

	public static void main(String[] args) {
		//testeCadastrar();
		//testeAlterar();
		testeExcluir();

	}
	public static void testeCadastrar(){
		Usuario usu = new Usuario();
				
				usu.setNome("Jãozão");
				usu.setLogin("jjjj");
				usu.setSenha("210100");
				
				UsuarioDao usuDao = new UsuarioDao();
				usuDao.cadastrar(usu);
				
				System.out.println("Cadastrado com sucesso!!!!");
	}
	
	public static void testeAlterar(){
		Usuario usu = new Usuario();
				usu.setId(4);
				usu.setNome("Jãozão da silva");
				usu.setLogin("jjjjssss");
				usu.setSenha("2101004");
				
				UsuarioDao usuDao = new UsuarioDao();
				usuDao.alterar(usu);
				
				System.out.println("Alterado com sucesso!!!!");
	}
	
	public static void testeExcluir(){
		Usuario usu = new Usuario();
		usu.setId(4);
		
		UsuarioDao usuDao = new UsuarioDao();
		usuDao.excluir(usu);
		System.out.println("Excluido com sucesso!!!!!");
	}
}
