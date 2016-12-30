package br.com.fabricadeprogramador.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fabricadeprogramador.entity.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.ConexaoFactory;

public class UsuarioDao {
	private Connection con = ConexaoFactory.getConnection();
	public void cadastrar(Usuario usu) {
		
		String sql = "insert into usuario (nome,login,senha) values(?,?,?)";
		try (PreparedStatement preparador =  con.prepareStatement(sql)){
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void alterar(Usuario usu) {
		String sql = "update usuario set nome = ?,login = ?,senha = ? where id = ?";
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void excluir(Usuario usu) {
		String sql ="delete from usuario where id=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			
			preparador.setInt(1, usu.getId());
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public void salvar(Usuario usuario){
		if(usuario.getId()!=null && usuario.getId() > 0){
			alterar(usuario);
		}else{
			cadastrar(usuario);
		}
	}
	/**
	 * Busca de um registro no banco de dados pelo número do id do usuario.
	 * parametros id : é um inteiro que representa o número do id do usuario a ser buscado.
	 * Returno: retorna um objeto de usuario quando encontra ou nulo quando não encontra.
	 */
	public Usuario buscaPorId(Integer id){
		String sql = "select * from usuario where id = ?";
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			
			ResultSet rs = preparador.executeQuery();
			if (rs.next()){
				Usuario usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setLogin(rs.getString("login"));
				usu.setSenha(rs.getString("senha"));
				
				return usu;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Realiza a busca de todos os registros da tabela usuario
	 * @return Uma lista de objetos usuario contendo 0 elementos quando não encontrar registro e N elementos quando encontrar
	 */
	public ArrayList<Usuario> buscarTodos(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		try (PreparedStatement preparador  = con.prepareStatement(sql)){
			ResultSet rs = preparador.executeQuery();
			//Posiciolnando o cursor nos registros
			while(rs.next()){
				Usuario usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setLogin(rs.getString("login"));
				usu.setSenha(rs.getString("senha"));
			// adicionando objetos usuario na lista	
				lista.add(usu);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Usuario autenticar(Usuario usuConsulta){
		String sql = "select * from usuario where login = ? and senha = ?";
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			
			ResultSet rs = preparador.executeQuery();
			if (rs.next()){
				Usuario usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setLogin(rs.getString("login"));
				usu.setSenha(rs.getString("senha"));
				
				return usu;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
