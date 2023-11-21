package model.entities;
import java.util.Objects;
import java.sql.Date;

public class Pedido {
    //private int codigo //ideia para chave primaria
    private String observacoes;
    private Double valor;
    private String status;
    private String nome;//poderia ser usada como chave primaria ?
    private Date compra;//gerada automaticamente do banco de dados
    private int fk_cliente_id; //chave estrangeira com cliente

    public Pedido(){}

    public Pedido(String observacoes, Double valor, String status, String nome, int fk_cliente_id){
        this.observacoes = observacoes;
        this.valor = valor;
        this.status = status;
        this.nome = nome;
        this.fk_cliente_id = fk_cliente_id;

    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCompra() {
        return compra;
    }

    public void setCompra(Date compra) {
        this.compra = compra;
    }

    public int getFk_cliente_id() {
        return fk_cliente_id;
    }

    public void setFk_cliente_id(int fk_cliente_id) {
        this.fk_cliente_id = fk_cliente_id;
    }

    //demais metodos
    @Override
	public String toString() {
		return " Pedido [valor=" + valor + ", status=" + status+ ", observacoes=" + observacoes  + "Cliente(id)"+ fk_cliente_id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fk_cliente_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return fk_cliente_id == other.fk_cliente_id;
	}

}
