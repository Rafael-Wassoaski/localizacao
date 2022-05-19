package com.rafaelwassoaski.localizacao;

import java.util.ArrayList;
import javax.transaction.Transactional;
import com.rafaelwassoaski.localizacao.entity.Cidade;
import com.rafaelwassoaski.localizacao.repository.CidadeRepository;
import com.rafaelwassoaski.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.Nullable;
import antlr.collections.List;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {	
	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Aplicação iniciada");

		Cidade cidade = new Cidade(1, "Canoinhas", 100L);

		cidadeService.getCidadesBySpecs(cidade);
		cidadeService.getCidades();
		cidadeService.nullableTest(null);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}


}
