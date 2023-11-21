package model.entities;

import java.util.Objects;

public class ItemPedido {
    private int codigo;//chave primaria
    private int quantidade;
    private String fk_produto_nome; //chave estrangeira com produro

    public ItemPedido(){}
    public ItemPedido(int codigo, int quantidade, String nome){
        this.codigo=codigo;
        this.quantidade = quantidade;
        this.fk_produto_nome = nome;
    }

    public int getCodigo() {
        return this.codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getNome() {
        return this.fk_produto_nome;
    }
    public void setNome(String nome) {
        this.fk_produto_nome = nome;
    }

    //demais metodos
    @Override
	public String toString() {
		return "Item Pedido [codigo=" + codigo + ", quantidade=" + quantidade + ", nome=" + fk_produto_nome  + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return codigo == other.codigo;
	}

}
