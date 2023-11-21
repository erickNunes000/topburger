package model.entities;

import java.util.Objects;

public class Produto {
    private String nome;
    private Double preco;
    private String descricao;

    public Produto(){}

    public Produto(String nome, Double preco, String descricao){
       
        this.nome = nome;
        this.preco = preco;
        this.descricao=descricao;
    }
     //nome
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    //preco
    public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
    //descricao
    public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    //demais metodos
    @Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco R$=" + preco + ", descricao=" + descricao  + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return nome == other.nome;
	}
}
