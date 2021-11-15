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
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluirTelefone(@PathVariable Integer id){
		dao.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
