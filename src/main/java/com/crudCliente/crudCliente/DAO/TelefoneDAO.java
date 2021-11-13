package com.crudCliente.crudCliente.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudCliente.crudCliente.model.Cliente;
import com.crudCliente.crudCliente.model.Telefone;
import com.crudCliente.crudCliente.repository.TelefoneRepository;

@Service
public class TelefoneDAO {
	
	@Autowired
	TelefoneRepository repository;
	
	@Autowired
	ClienteDAO cDAO;
	
	public List<Telefone> findAll(Integer cliente_id){
		cDAO.findById(cliente_id);
		return repository.findAllByClientes(cliente_id);
	}
	
	public Telefone findById(Integer id) {
		Optional<Telefone> telefones = this.repository.findById(id);
		return telefones.orElse(null);
	}
	
	public List<Telefone> create(Integer cliente_id, List<Telefone> telefones) {
		Cliente cliente = cDAO.findById(cliente_id);
		for(Telefone telefone: telefones) {
			telefone.setCliente(cliente);
			telefone = this.repository.save(telefone);
		}
		
		return telefones;
	}


	public Telefone update(Integer id, Telefone telefone) {
		Telefone obj = findById(id);
		obj.setNumeroTelefone(telefone.getNumeroTelefone());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
		
	}

}
