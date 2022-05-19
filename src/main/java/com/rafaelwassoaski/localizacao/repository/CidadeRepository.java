package com.rafaelwassoaski.localizacao.repository;

import java.util.List;
import com.rafaelwassoaski.localizacao.entity.Cidade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>, JpaSpecificationExecutor{
    public List<Cidade> findByNome(String nome);

    @Query(nativeQuery = true, value = "select * from cidade as c where c.nome =:nome")
    public List<Cidade> findByNomeSQL(@Param("nome") String nome);

    public List<Cidade> findByNomeStartingWith(String nome);

    public List<Cidade> findByNomeEndingWith(String nome);
    
    public List<Cidade> findByNomeContaining(String nome);

    public List<Cidade> findByHabitantes(Long habitantes);

    @Query(value = "select c from Cidade c where lower(c.nome) like lower(?1)")
    public List<Cidade> findByNomeLike(String nome, Sort sort);

    public List<Cidade> findByHabitantesLessThan(Long habitantes);

    public List<Cidade> findByHabitantesGreaterThan(Long habitantes, Pageable pageable);

    public List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    public List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

    public List<Cidade> findByHabitantesLessThanOrNomeLike(Long habitantes, String nome);


}
