package model.dao;

import java.util.List;

import model.entities.Pedido;

public interface PedidoDao {
    void inserir(Pedido obj);
	void atualizar(Pedido obj);
	void deletarPorId(int codigo);//id usada sera a chave estrangeira fk_cliente_id
	Pedido procurarPorId(int codigo);
	List<Pedido> procurarTodos();
}
