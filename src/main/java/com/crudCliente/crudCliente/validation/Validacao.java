package com.crudCliente.crudCliente.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crudCliente.crudCliente.model.Telefone;
import com.crudCliente.crudCliente.repository.TelefoneRepository;

public class Validacao {
	
	@Autowired
	TelefoneRepository repository;
	
	
	
	public Validacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Metódo para veirificar se o telefone já  foi cadastrado
	public boolean existeTelefone(Telefone tel) {
		List<Telefone> telefones;
		telefones = repository.findAllByTelefone(tel.getNumeroTelefone());
		System.out.print("Dentro------");
		if(telefones.isEmpty()) return false;
		else return true;
	}
	
	public boolean charsIguais(String telefone) {
		int qtdeNum[] = new int[256]; 
		
		for(int i=0;i<256; i++) {
			qtdeNum[i] = 0;
			
		}
		
		for(int i=0; i<telefone.length();i++) {
			int ascii = (int) telefone.charAt(i);
			if(ascii>47 && ascii<58) {
				qtdeNum[ascii]++;
				if(qtdeNum[ascii]>1) { return true;}
			}
			
		}
		return false;
		
		
		//função em regex poreém validará apenas repentições em sequencia como 11 1111 111111
		//return telefone.matches("^(\\d)\\1{10}");
		
	}

}
