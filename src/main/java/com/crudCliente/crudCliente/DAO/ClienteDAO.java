package com.crudCliente.crudCliente.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudCliente.crudCliente.model.Cliente;
import com.crudCliente.crudCliente.model.Telefone;
import com.crudCliente.crudCliente.repository.ClienteRepository;
import com.crudCliente.crudCliente.validation.Validacao;

/**
 * @author Felipe
 * @since Nov 2021
 * @version 1.0
 * Classe de Serviço do Cliente, responsável por mediar a comunicação da API aos dados na database 
 *
 */
@Service
public class ClienteDAO {

	@Autowired
	ClienteRepository repository;

	@Autowired
	TelefoneDAO tDAO;

	Validacao validador= new Validacao();

	/**
	 * Método responsável por retornar os dados do Cliente.
	 * @return List<Cliente> retornará todos os clientes encontrados no banco de dados.
	 */
	public List<Cliente> findAll() {
		return this.repository.findAll();
	}
	
	/**
	 * Método responsável por buscar um cliente Específico.
	 * @param id o método receberá o id do cliente a ser buscado no Banco de Dados
	 * @return Cliente retornará apenas um cliente com o id buscado.
	 */
	public Cliente findById(Integer id) {
		Optional<Cliente> clientes = this.repository.findById(id);
		return clientes.orElse(null);
	}
	/**
	 * Método responsável por inserir um cliente.
	 * @param cliente o método receberá um Cliente a ser inserido no Banco de Dados
	 * @return Cliente retornará o cliente inserido.
	 */
	public Cliente create(Cliente cliente) {
		Cliente newCliente = cliente;
		for (Telefone tel : newCliente.getTelefones()) {
			if(validador.charsIguais(tel.getNumeroTelefone())==true) { return null;}
			tel.setCliente(cliente);
		}

		return this.repository.save(cliente);
	}
	/**
	 * Método responsável por atualizar os dados de um cliente.
	 * @param id o método receberá o id do Cliente que terá seus dados atualizados no Banco de Dados
	 * @param cliente o método receberá os novos dados do Cliente a ser atualizado no Banco de Dados
	 * @return Cliente retornará o cliente atualizado.
	 */
	public Cliente update(Integer id, Cliente cliente) {
		Cliente obj = findById(id); // verifico se realmente o dado é existente.

		Cliente newCliente = cliente; // criado para atualizar as relações do telefone
		for (Telefone tel : cliente.getTelefones()) {
			if(validador.charsIguais(tel.getNumeroTelefone())==true) { return null;}
			tel.setCliente(obj);
		}
		obj.setNome(cliente.getNome());
		obj.setEndereco(cliente.getEndereco());
		obj.setBairro(cliente.getBairro());
		obj.setTelefones(cliente.getTelefones());

		return repository.save(obj);
	}
	/**
	 * Método responsável por deletar um Cliente da base de dados.
	 * @param id o método receberá o id do Cliente que terá seus dados deletados do Banco de Dados
	 */
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);

	}

}
