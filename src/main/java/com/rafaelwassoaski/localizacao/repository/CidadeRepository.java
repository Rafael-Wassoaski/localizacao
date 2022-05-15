package com.rafaelwassoaski.localizacao.repository;

import java.util.List;
import com.rafaelwassoaski.localizacao.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
    public List<Cidade> findByNome(String nome);

    public List<Cidade> findByNomeStartingWith(String nome);

    public List<Cidade> findByNomeEndingWith(String nome);
    
    public List<Cidade> findByNomeContaining(String nome);

    public List<Cidade> findByHabitantes(Long habitantes);

}
