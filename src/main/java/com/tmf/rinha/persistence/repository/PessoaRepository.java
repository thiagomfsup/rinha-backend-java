package com.tmf.rinha.persistence.repository;

import com.tmf.rinha.persistence.entity.PessoaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PessoaRepository extends CrudRepository<PessoaEntity, UUID> {
    @Query(value = "SELECT * FROM pessoas p WHERE p.search LIKE %:term% LIMIT 50", nativeQuery = true)
    List<PessoaEntity> findTop50ByTerm(@Param("term") String queryTerm);
}
