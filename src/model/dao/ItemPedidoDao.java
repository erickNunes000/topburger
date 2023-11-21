package model.dao;

import java.util.List;

import model.entities.ItemPedido;

public interface ItemPedidoDao {
    void inserir(ItemPedido obj);
	void atualizar(ItemPedido obj);
	void deletarPorCodigo(int codigo);
	ItemPedido procurarPorCodigo(int codigo);
	List<ItemPedido> procurarTodos();
}
