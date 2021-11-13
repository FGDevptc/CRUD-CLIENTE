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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crudCliente.crudCliente.DAO.TelefoneDAO;
import com.crudCliente.crudCliente.model.Telefone;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/telefones" })
public class TelefoneAPI {
	
	@Autowired
	private TelefoneDAO dao;
	
	
	
	@GetMapping
	public ResponseEntity<List<Telefone>> ListarTelefones(@RequestParam(value = "cliente", defaultValue = "0") Integer cliente_id){
		List<Telefone> telefones = dao.findAll(cliente_id);
		return ResponseEntity.ok().body(telefones);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Telefone> buscapeloID(@PathVariable Integer id){
		Telefone telefone = dao.findById(id);
		return ResponseEntity.ok().body(telefone);
	}
	
	@PostMapping
	public ResponseEntity<List<Telefone>> inserirTelefone(@RequestParam(value = "cliente", defaultValue = "0") Integer cliente_id, @Valid @RequestBody List<Telefone> telefones){
		telefones = dao.create(cliente_id, telefones);
		return ResponseEntity.ok().body(telefones);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Integer id, @Valid @RequestBody Telefone telefone){
		Telefone newTelefone = dao.update(id, telefone);
		return ResponseEntity.ok().body(newTelefone);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Telefone> atualizarItemTelefone(@PathVariable Integer id,@Valid @RequestBody Telefone telefone){
		Telefone newTelefone = dao.update(id, telefone);
		return ResponseEntity.ok().body(newTelefone);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluirTelefone(@PathVariable Integer id){
		dao.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
