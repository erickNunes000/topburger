package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {
	void inserir(Departamento obj);
	void atualizar(Departamento obj);
	void deletarPorId(int id);
	Departamento procurarPorId(int id);
	List<Departamento> procurarTodos();
}
