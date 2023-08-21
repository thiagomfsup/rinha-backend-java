package com.tmf.rinha.persistence.repository;

import com.tmf.rinha.persistence.entity.PessoaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PessoaRepository extends CrudRepository<PessoaEntity, UUID> {
    @Query("FROM PessoaEntity p WHERE p.nome LIKE %:term% OR p.apelido LIKE %:term% OR p.stack LIKE %:term%")
    List<PessoaEntity> findTop50ByTerm(@Param("term") String queryTerm, Pageable pageable);
}
