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

import model.dao.ClienteDao;
import model.entities.Cliente;


public class ClienteDaoJDBC implements ClienteDao{

    private Connection conn;
	
	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

    @Override
	public void inserir(Cliente obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into cliente(id, nome,telefone,endereco) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId());
			st.setString(2,obj.getNome());
            st.setString(3,obj.getTelefone());
            st.setString(4,obj.getEndereco());
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
	public void atualizar(Cliente obj) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("update cliente set nome=?, telefone=?, endereco=? where id=?");
			st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getEndereco());
			st.setInt(4, obj.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletarPorId(int id) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("delete  from cliente where id=?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Cliente procurarPorId(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from cliente where id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Cliente cliente = instanciadorCliente(rs);
				return cliente;
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

    private Cliente instanciadorCliente(ResultSet rs) throws SQLException{
		 Cliente cliente = new Cliente();
			cliente.setId(rs.getInt("id"));
			cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEndereco(rs.getString("endereco"));
		return cliente;
	}

	@Override
	public List<Cliente> procurarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from cliente");
			rs = st.executeQuery();
			List <Cliente> lista = new ArrayList<>();
			while(rs.next()) {
				Cliente cliente = instanciadorCliente(rs);
				lista.add(cliente);
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
