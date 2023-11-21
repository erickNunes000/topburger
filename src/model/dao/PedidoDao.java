package model.dao;

import java.util.List;

import model.entities.Pedido;

public interface PedidoDao {
    void inserir(Pedido obj);
	void atualizar(Pedido obj);
	void deletarPorId(int id,  String nome);//id usada sera a chave estrangeira fk_cliente_id
	Pedido procurarPorId(int id,  String nome);
	List<Pedido> procurarTodos();
}
