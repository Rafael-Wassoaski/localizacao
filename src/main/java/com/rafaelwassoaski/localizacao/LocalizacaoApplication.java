package com.rafaelwassoaski.localizacao;

import java.util.ArrayList;
import javax.transaction.Transactional;
import com.rafaelwassoaski.localizacao.entity.Cidade;
import com.rafaelwassoaski.localizacao.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import antlr.collections.List;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Aplicação iniciada");

		this.salvarCidade();
		this.listarCidades();
		this.listarCidadesPorNome();
		this.listarCidadesPorhabitantes();
	}

	@Transactional
	private void salvarCidade() {
		// Cidade cidade = new Cidade(1, "Canoinhas", 700000L);

		// cidadeRepository.save(cidade);
	}

	private void listarCidades() {
		ArrayList<Cidade> listarCidades = (ArrayList<Cidade>) cidadeRepository.findAll();

		listarCidades.forEach(System.out::println);
	}
 
	private void listarCidadesPorNome() {
		cidadeRepository.findByNome("Canoinhas").forEach(System.out::println);

		cidadeRepository.findByNomeStartingWith("C").forEach(System.out::println);

		cidadeRepository.findByNomeEndingWith("o").forEach(System.out::println);

		cidadeRepository.findByNomeContaining("3").forEach(System.out::println);

	}

	private void listarCidadesPorhabitantes() {
		cidadeRepository.findByHabitantes(12312L).forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}


}
