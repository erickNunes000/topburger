package model.dao;

import java.util.List;

import model.entities.Produto;

public interface ProdutoDao {
    void inserir(Produto obj);
	void atualizar(Produto obj);
	void deletarPorNome(String nome);
	Produto procurarPorNome(String nome);
	List<Produto> procurarTodos();
    
}
