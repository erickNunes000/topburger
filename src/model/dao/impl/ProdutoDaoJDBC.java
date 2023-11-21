package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import db.DB;
import db.DbException;

import model.dao.ProdutoDao;

import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDao{

    private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

     @Override
	 public void inserir(Produto obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into produto(nome, preco,descricao) values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		
			st.setString(1,obj.getNome());
            st.setDouble(2,obj.getPreco());
            st.setString(3,obj.getDescricao());
			int linhas = st.executeUpdate();
			if(linhas>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					//int id = rs.getInt(1);
					//obj.setId(id);

					System.out.println("td certo");
					
				}
				else {
					throw new DbException("Nenhuma linha afetada!");
				}
				DB.closeResultset(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void atualizar(Produto obj) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("update produto set preco=?, descricao=? where nome=?");
			st.setDouble(1, obj.getPreco());
            st.setString(2, obj.getDescricao());
            st.setString(3, obj.getNome());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletarPorNome(String nome) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("delete  from produto where nome=?");
			st.setString(1, nome);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Produto procurarPorNome(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from produto where nome=?");
			st.setString(1, nome);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Produto produto = instanciadorProduto(rs);
				return produto;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeResultset(rs);
		}
		
		return null;
	}

    /* 
	private Produto instanciadorProduto(ResultSet rs) {
        return null;
    }*/

    private Produto instanciadorProduto(ResultSet rs) throws SQLException{
		 Produto produto = new Produto();
			
			produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
		return produto;
	}

	@Override
	public List<Produto> procurarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from produto");
			rs = st.executeQuery();
			List <Produto> lista = new ArrayList<>();
			while(rs.next()) {
				Produto produto = instanciadorProduto(rs);
				lista.add(produto);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException("Erro ao listar! Causa: "+e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultset(rs);
		}
		
	}

}
