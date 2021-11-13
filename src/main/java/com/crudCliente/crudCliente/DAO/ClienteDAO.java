package com.crudCliente.crudCliente.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudCliente.crudCliente.model.Cliente;
import com.crudCliente.crudCliente.model.Telefone;
import com.crudCliente.crudCliente.repository.ClienteRepository;

@Service
public class ClienteDAO {
	
	@Autowired
	ClienteRepository repository;
	
	@Autowired
	TelefoneDAO tDAO;
	
	
	public List<Cliente> findAll(){
		return this.repository.findAll();
	}
	
	public Cliente findById(Integer id) {
		Optional<Cliente> clientes = this.repository.findById(id);
		return clientes.orElse(null);
	}
	
	public Cliente create(Cliente cliente) {
		Cliente newCliente = cliente;
		for(Telefone tel: newCliente.getTelefones()) {
			tel.setCliente(cliente);
		}
		return this.repository.save(cliente);
	}

	public Cliente update(Integer id, Cliente cliente) {
		Cliente obj = findById(id); //verifico se realmente o dado é existente.
		
		
		Cliente newCliente = cliente; //criado para atualizar as relações do telefone
		
		for(Telefone tel: cliente.getTelefones()) {
			tel.setCliente(obj);
		}
		obj.setNome(cliente.getNome());
		obj.setEndereco(cliente.getEndereco());
		obj.setBairro(cliente.getBairro());
		obj.setTelefones(cliente.getTelefones());
		
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
		
	}



}
