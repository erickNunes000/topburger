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
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class DepartamentoDaoJDBC  implements DepartamentoDao{

	private Connection conn;
	
	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Departamento obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into departamento(id, nome) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId());
			st.setString(2,obj.getNome());
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
	public void atualizar(Departamento obj) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("update departamento set nome=? where id=?");
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
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
			st=conn.prepareStatement("delete  from departamento where id=?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Departamento procurarPorId(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from departamento where id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Departamento dep = instanciadorDepartamento(rs);
				return dep;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeResultset(rs);
		}
		
		return null;
	}

	private Departamento instanciadorDepartamento(ResultSet rs) throws SQLException{
		 Departamento dep = new Departamento();
			dep.setId(rs.getInt("id"));
			dep.setNome(rs.getString("nome"));
		return dep;
	}

	@Override
	public List<Departamento> procurarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from departamento");
			rs = st.executeQuery();
			List <Departamento> lista = new ArrayList<>();
			while(rs.next()) {
				Departamento dep = instanciadorDepartamento(rs);
				lista.add(dep);
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
