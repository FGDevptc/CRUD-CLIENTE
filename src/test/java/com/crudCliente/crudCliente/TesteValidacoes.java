package com.crudCliente.crudCliente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.crudCliente.crudCliente.DAO.ClienteDAO;
import com.crudCliente.crudCliente.api.ClienteAPI;
import com.crudCliente.crudCliente.model.Cliente;
import com.crudCliente.crudCliente.model.Telefone;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TesteValidacoes {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ClienteAPI clienteAPI;
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private ObjectMapper objectMapper;

	// Teste de inserção dos dados, correto.
	// Este teste tem que inserir os dados pois estão todos corretos.
	@Test
	void InserirTest1() throws Exception {
		Cliente cliente1 = new Cliente(null, "Nome de Teste", "Endereço de Teste", "Bairro teste");

		Telefone tel1 = new Telefone(null, "12 0345-6789", cliente1);
		Telefone tel2 = new Telefone(null, "98 7654-3210", cliente1);
		
		cliente1.setTelefones(Arrays.asList(tel1, tel2));

		mockMvc.perform(
				post("/clientes").contentType("application/json").content(objectMapper.writeValueAsString(cliente1)))
				.andExpect(status().is(201));
	}

	// Teste de inserção dos dados, Nome com menos de 10 caracteres.
	// Este teste não deverá inserir os dados pois o Nome fornecido viola a regra de no minimo 10 caracteres para ocampo Nome.
	@Test
	void InserirTest2() throws Exception {
		Cliente cliente1 = new Cliente(null, "Nome", "Endereço de Teste", "Bairro teste");

		Telefone tel1 = new Telefone(null, "12 0345-6759", cliente1);
		Telefone tel2 = new Telefone(null, "98 7654-3210", cliente1);
		
		cliente1.setTelefones(Arrays.asList(tel1, tel2));

		mockMvc.perform(
				post("/clientes").contentType("application/json").content(objectMapper.writeValueAsString(cliente1)))
				.andExpect(status().is(400));
	}

	// Teste de inserção dos dados, Inserção de nome null
	// Este teste não deverá inserir os dados pois não é fornecido nenhum dado e campo não pde ser vazio.
	@Test
	void InserirTest3() throws Exception {
		Cliente cliente1 = new Cliente(null, null, "Endereço de Teste", "Bairro teste");

		Telefone tel1 = new Telefone(null, "12 0345-6759", cliente1);
		Telefone tel2 = new Telefone(null, "98 7654-3210", cliente1);
		
		cliente1.setTelefones(Arrays.asList(tel1, tel2));

		mockMvc.perform(
				post("/clientes").contentType("application/json").content(objectMapper.writeValueAsString(cliente1)))
				.andExpect(status().is(400));
	}

	// Teste de inserção dos dados, Inserção de 2 telefones identicos
	// Este teste não deverá inserir os dados pois os telefones fornecidos viola a regra @Column(unique) e @OneToOne, declarada no modelo;
	@Test
	void InserirTest4() throws Exception {
		Cliente cliente1 = new Cliente(null, "Nome Completo mais de 10", "Endereço de Teste", "Bairro teste");

		Telefone tel1 = new Telefone(null, "12 0345-6759", cliente1);
		Telefone tel2 = new Telefone(null, "12 0345-6759", cliente1);
		
		cliente1.setTelefones(Arrays.asList(tel1, tel2));

		mockMvc.perform(
				post("/clientes").contentType("application/json").content(objectMapper.writeValueAsString(cliente1)))
				.andExpect(status().is(500));
	}

	// Teste de inserção dos dados, Inserção de telefone com caracteres repetidos.
	// Este teste não deverá inserir os dados pois os telefones fornecidos viola a regex criada no ClienteDAO;
	@Test
	void InserirTest5() throws Exception {
		Cliente cliente1 = new Cliente(null, "Nome Completo mais de 10", "Endereço de Teste", "Bairro teste");

		Telefone tel1 = new Telefone(null, "1111111111", cliente1);
		Telefone tel2 = new Telefone(null, "2222222222", cliente1);
		
		cliente1.setTelefones(Arrays.asList(tel1, tel2));

		mockMvc.perform(
				post("/clientes").contentType("application/json").content(objectMapper.writeValueAsString(cliente1)))
				.andExpect(status().is(500));
	}

}
