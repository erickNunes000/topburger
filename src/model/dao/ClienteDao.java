package model.dao;

import java.util.List;

import model.entities.Cliente;


public interface ClienteDao {
    void inserir(Cliente obj);
	void atualizar(Cliente obj);
	void deletarPorId(int id);
	Cliente procurarPorId(int id);
	List<Cliente> procurarTodos();
}
