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

import model.entities.ItemPedido;
import model.dao.ItemPedidoDao;



public class ItemPedidoDaoJDBC implements ItemPedidoDao{

    private Connection conn;
	
	public ItemPedidoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

     @Override
	public void inserir(ItemPedido obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into itempedido(codigo, quantidade,fk_produto_nome) values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getCodigo());
			st.setInt(2,obj.getQuantidade());
            st.setString(3,obj.getNome());
            
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
	public void atualizar(ItemPedido obj) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("update itempedido set fk_produto_nome=?, quantidade=? where codigo=?");
			st.setString(1, obj.getNome());
            st.setInt(2, obj.getQuantidade());
			st.setInt(3, obj.getCodigo());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletarPorCodigo(int codigo) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("delete  from itempedido where codigo=?");
			st.setInt(1, codigo);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public ItemPedido procurarPorCodigo(int codigo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from itempedido where codigo=?");
			st.setInt(1, codigo);
			rs = st.executeQuery();
			
			if(rs.next()) {
				ItemPedido itempedido = instanciadorItemPedido(rs);
				return itempedido;
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
	private Cliente instanciadorCliente(ResultSet rs) {
        return null;
    }*/

    private ItemPedido instanciadorItemPedido(ResultSet rs) throws SQLException{
		 ItemPedido itempedido = new ItemPedido();
			itempedido.setCodigo(rs.getInt("codigo"));
			itempedido.setQuantidade(rs.getInt("quantidade"));
            itempedido.setNome(rs.getString("fk_produto_nome"));
            
		return itempedido;
	}

	@Override
	public List<ItemPedido> procurarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from itempedido");
			rs = st.executeQuery();
			List <ItemPedido> lista = new ArrayList<>();
			while(rs.next()) {
				ItemPedido itempedido = instanciadorItemPedido(rs);
				lista.add(itempedido);
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
