package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
//import java.sql.Date;

import db.DB;
import db.DbException;

import model.dao.PedidoDao;

import model.entities.Pedido;

public class PedidoDaoJDBC implements PedidoDao{

     private Connection conn;
	
	public PedidoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

    @Override
	public void inserir(Pedido obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into pedido(codigo, observacoes,valor,status,nome,fk_cliente_id ) values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getCodigo());
			st.setString(2, obj.getObservacoes());
			st.setDouble(3,obj.getValor());
            st.setString(4,obj.getStatus());
            st.setString(5,obj.getNome());
            st.setInt(6, obj.getFk_cliente_id());
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
	public void atualizar(Pedido obj) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("update pedido set observacoes=?, valor=?, status=? where fk_cliente_id=? and codigo=?");
			st.setString(1, obj.getObservacoes());
            st.setDouble(2, obj.getValor());
            st.setString(3, obj.getStatus());
			st.setInt(4, obj.getFk_cliente_id());
            st.setInt(5, obj.getCodigo());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletarPorId(int codigo) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("delete from pedido where codigo=?");
			st.setInt(1, codigo);
           
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Pedido procurarPorId(int codigo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from pedido where codigo=?");
			st.setInt(1, codigo);
           
			rs = st.executeQuery();
			
			if(rs.next()) {
				Pedido pedido = instanciadorPedido(rs);
				return pedido;
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

    private Pedido instanciadorPedido(ResultSet rs) throws SQLException{
		 Pedido pedido = new Pedido();
		 	pedido.setCodigo(rs.getInt("codigo"));
			pedido.setObservacoes(rs.getString("observacoes"));
            pedido.setValor(rs.getDouble("valor"));
			pedido.setStatus(rs.getString("status"));
            pedido.setNome(rs.getString("nome"));
            pedido.setFk_cliente_id(rs.getInt("fk_cliente_id"));
		return pedido;
	}

	@Override
	public List<Pedido> procurarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from cliente");
			rs = st.executeQuery();
			List <Pedido> lista = new ArrayList<>();
			while(rs.next()) {
				Pedido pedido = instanciadorPedido(rs);
				lista.add(pedido);
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
