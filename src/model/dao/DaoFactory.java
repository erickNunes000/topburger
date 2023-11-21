package model.dao;

import db.DB;

import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;//produto
import model.dao.impl.ClienteDaoJDBC;//cliente
import model.dao.impl.ItemPedidoDaoJDBC;//itempedido
import model.dao.impl.PedidoDaoJDBC;//itempedido

public class DaoFactory {

	public static DepartamentoDao createDepartamentoDao() {
		return new DepartamentoDaoJDBC(DB.getConnection());
	}

	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static ProdutoDao createProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}

	public static ItemPedidoDao createItemPedidoDao() {
		return new ItemPedidoDaoJDBC(DB.getConnection());
	}
	public static PedidoDao createPedidoDao() {
		return new PedidoDaoJDBC(DB.getConnection());
	}

}
