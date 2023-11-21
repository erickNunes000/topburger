package App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import db.DB;
import db.DbIntegrityException;

//import dao´s
import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.dao.ClienteDao;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;

//import daojdbc´s
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.ItemPedidoDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
import model.dao.impl.PedidoDaoJDBC;

//import models
import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.ItemPedido;
import model.entities.Produto;
import model.entities.Pedido;

public class Application {
    public static void main(String []args){
       
		Connection conn = null;
		conn = DB.getConnection();
		System.out.println("connected to erico´s database...");

		Pedido pedido = new Pedido("pao integral sem casca", 21.55, "pendente","manoel filho", 1);
		
		PedidoDaoJDBC func = new PedidoDaoJDBC(conn);
		func.inserir(pedido);
		//func.deletarPorId(2);
		DB.closeConnection();
    }
	//comment
    
}
