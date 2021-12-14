package com.crudCliente.crudCliente.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Currency;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Felipe
 * @since Nov 2021
 * @version 1.0
 * Classe responsável por modelar os Contatos do Cliente
 *
 */
@Entity
public class Telefone implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Chave primária do objeto Telefone, com geração automática e auto incremento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	/**
	 * Atributo responsável por armazenar um dos número de telefone do Cliente.
	 */
	@NotEmpty(message = "Parace que não há um telefone cadastrado!")
	@NotNull(message = "O valor do Telefone não pode ser null!")
	@Column(unique = true)//valida a não iserção do mesmo número 2 vezes no sistema
	private String numeroTelefone;
	
	/**
	 * Atributo responsável por armazenar a quem pertence o contato.
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	
	
	/**
	 * Construtor do Telefone.
	 * @param id atributo do tipo inteiro, identifica a chave primaria
	 * @param numeroTelefone atributo do tipo String, identifica os núemros de telefones
	 * @param cliente atributo do tipo Cliente, identifica o Cliente dono do Telefone.
	 */
	public Telefone(Integer id,String numeroTelefone, Cliente cliente) {
		super();
		this.id = id;
		this.numeroTelefone = numeroTelefone;
		this.cliente = cliente;
	}

	public Telefone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
