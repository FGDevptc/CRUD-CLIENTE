package com.crudCliente.crudCliente.DAO;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudCliente.crudCliente.model.Cliente;
import com.crudCliente.crudCliente.model.Telefone;
import com.crudCliente.crudCliente.repository.TelefoneRepository;


/**
 * @author Felipe
 * @since Nov 2021
 * @version 1.0
 * Classe de Serviço do Telefone, responsável por mediar a comunicação da API aos dados na database 
 *
 */
@Service
public class TelefoneDAO {
	
	@Autowired
	TelefoneRepository repository;
	
	/**
	 * Método responsável por deletar um Telefone da base de dados.
	 * @param id o método receberá o id do Telefone que terá seus dados deletados do Banco de Dados
	 */
	//Metódo implementado para fazer a exclusão de um número se for do desejo do usuário
	public void delete(Integer id) {
		repository.findById(id);//verificamos se já existe um objeto, a final para exluir é necessário existir, se não existir a função retornará um exception e parará a execução
		repository.deleteById(id);//aqui exluimos o telefone da base.
		
	}

}
