package com.rafaelwassoaski.localizacao.service;

import java.util.List;
import com.rafaelwassoaski.localizacao.entity.Cidade;
import com.rafaelwassoaski.localizacao.repository.CidadeRepository;
import com.rafaelwassoaski.localizacao.repository.specs.CidadeSpecs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository repository) {
        this.cidadeRepository = repository;
    }

    public void getCidades() {
        cidadeRepository.findByNome("Canoinhas").forEach(System.out::println);

        cidadeRepository.findByNomeStartingWith("C").forEach(System.out::println);

        cidadeRepository.findByNomeEndingWith("o").forEach(System.out::println);

        cidadeRepository.findByNomeContaining("3").forEach(System.out::println);

        cidadeRepository.findByNomeLike("c", Sort.by("habitantes")).forEach(System.out::println);

        cidadeRepository.findByHabitantesLessThan(12313L).forEach(System.out::println);

        Pageable pageable = PageRequest.of(0, 10);
        cidadeRepository.findByHabitantesGreaterThan(12313L, pageable).forEach(System.out::println);

        cidadeRepository.findByHabitantesLessThanEqual(12313L).forEach(System.out::println);

        cidadeRepository.findByHabitantesLessThanAndNomeLike(700001L, "Can%")
                .forEach(System.out::println);

        cidadeRepository.findByNomeSQL("Canoinhas").stream().map(cidade -> new Cidade(cidade.getId(), cidade.getNome(), null)).forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIncludeNullValues()
                .withStringMatcher(StringMatcher.STARTING);

        Example<Cidade> example = Example.of(cidade, matcher);

        return cidadeRepository.findAll(example);
    }

    public void getCidadesBySpecs(Cidade filtro) {
        Specification<Cidade> specs =
                CidadeSpecs.nomeEqual("Canoinhas").and(CidadeSpecs.habitantesGreaterThan(700001L));

        cidadeRepository.findAll(specs).forEach(System.out::println);

        Specification<Cidade> specs2 = Specification
                .where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        // igual a "select * from cidade where 1 = 1"

        if (filtro.getId() != null) {
            specs2 = specs2.and(CidadeSpecs.idEquals(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            specs2 = specs2.and(CidadeSpecs.nomeLike(filtro.getNome()));
        }

        if (filtro.getHabitantes() != null) {
            specs2 = specs2.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
        }

        cidadeRepository.findAll(specs2).forEach(System.out::println);
    }

    public void nullableTest(@Nullable String nome){
        System.out.println(nome.toLowerCase());
    }

}
