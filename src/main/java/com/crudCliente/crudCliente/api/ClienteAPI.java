package com.crudCliente.crudCliente.api;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crudCliente.crudCliente.DAO.ClienteDAO;
import com.crudCliente.crudCliente.DAO.TelefoneDAO;
import com.crudCliente.crudCliente.model.Cliente;
import com.crudCliente.crudCliente.model.Telefone;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/clientes" })
public class ClienteAPI {
	
	@Autowired
	private ClienteDAO dao;

	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> ListarClientes(){
		List<Cliente> clientes = dao.findAll();
		return ResponseEntity.ok().body(clientes);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscapeloID(@PathVariable Integer id){
		Cliente cliente = dao.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody Cliente cliente){
		cliente = dao.create(cliente);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id,@Valid @RequestBody Cliente cliente){
		Cliente newCliente = dao.update(id, cliente);
		return ResponseEntity.ok().body(newCliente);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizarItemCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
		Cliente newCliente = dao.update(id, cliente);
		return ResponseEntity.ok().body(newCliente);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Integer id){
		dao.delete(id);
		return ResponseEntity.noContent().build();
	}

}
