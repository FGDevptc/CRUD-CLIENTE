package com.crudCliente.crudCliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crudCliente.crudCliente.model.Telefone;

@Repository
public interface TelefoneRepository  extends JpaRepository<Telefone, Integer> {
	
	@Query("SELECT obj FROM Telefone obj WHERE obj.cliente.id = :cliente_id")
	List<Telefone> findAllByClientes(@Param(value = "cliente_id") Integer cliente_id);
	
	@Query("SELECT tel FROM Telefone tel WHERE tel.numeroTelefone = ?1")
	List<Telefone> findAllByTelefone(String numeroTelefone);

}
