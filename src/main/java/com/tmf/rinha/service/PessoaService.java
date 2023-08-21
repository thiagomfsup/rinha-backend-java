package com.tmf.rinha.service;

import com.tmf.rinha.persistence.entity.PessoaEntity;
import com.tmf.rinha.web.dto.AddPessoaDTO;
import com.tmf.rinha.web.dto.PessoaDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaService {
    PessoaDTO addPessoa(AddPessoaDTO pessoa);
    Optional<PessoaEntity> retrieveById(UUID id);
    List<PessoaDTO> retrieveByQueryTerm(String queryTerm);
}
