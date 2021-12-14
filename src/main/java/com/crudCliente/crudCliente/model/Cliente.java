package com.crudCliente.crudCliente.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


/**
 * @author Felipe
 * @since Nov 2021
 * @version 1.0
 * Classe responsável por modelar o objeto do Cliente
 *
 */
@Entity
public class Cliente implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Chave primária do objeto Cliente, com geração automática e auto incremento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	/**
	 * Atributo responsável por armazenar o nome do Cliente.
	 */
	@NotEmpty(message = "O campo nome não pode ser vazio!")
	@Length(min = 10, message = "O campo nome deve ter pelo menos 10 caracteres!")
	private String nome;
	
	
	/**
	 * Atributo responsável por armazenar o Endereço do Cliente.
	 */
	@NotEmpty(message = "O campo endereço não pode ser vazio!")
	private String endereco;
	
	/**
	 * Atributo responsável por armazenar o bairro do Cliente.
	 */
	@NotEmpty(message = "O campo bairro não pode ser vazio!")
	private String bairro;
	
	/**
	 * Atributo por retornar e armazenar todos os contatos do Cliente.
	 */
	@OneToMany(mappedBy = "cliente", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<Telefone> telefones;
	
	
	public Cliente(Integer id, String nome, String endereco, String bairro) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.telefones = new ArrayList<Telefone>();
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

}
