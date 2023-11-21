package model.entities;

import java.util.Objects;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;

    public Cliente(){}

    public Cliente(int id, String nome, String telefone, String endereco){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco=endereco;
    }
    //id
    public int getId() {
		return this.id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

    //nome
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    //telefone
    public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    //endereco
    public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

    //demais metodos
    @Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return id == other.id;
	}
	



}
