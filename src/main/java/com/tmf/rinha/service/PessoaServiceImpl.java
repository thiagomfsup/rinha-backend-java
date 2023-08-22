package com.tmf.rinha.service;

import com.tmf.rinha.persistence.entity.PessoaEntity;
import com.tmf.rinha.persistence.repository.PessoaRepository;
import com.tmf.rinha.web.dto.AddPessoaDTO;
import com.tmf.rinha.web.dto.PessoaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID addPessoa(AddPessoaDTO pessoa) {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setId(UUID.randomUUID())
            .setApelido(pessoa.apelido())
            .setNome(pessoa.nome())
            .setNascimento(pessoa.nascimento())
            .setStack(stackListToString(pessoa.stack()));

        return repository.save(pessoaEntity).getId();
    }

    @Override
    public Optional<PessoaEntity> retrieveById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<PessoaDTO> retrieveByQueryTerm(String queryTerm) {
        return repository.findTop50ByTerm(queryTerm, Pageable.ofSize(50))
            .stream()
            .map(entity -> new PessoaDTO(entity.getId(), entity.getNome(), entity.getApelido(),
                 entity.getNascimento(), stringToStackList(entity.getStack())))
            .collect(Collectors.toList());
    }

    @Override
    public long countPessoas() {
        return repository.count();
    }

    private String stackListToString(List<String> stack) {
        return (stack == null) ? null : String.join(";", stack);
    }

    private List<String> stringToStackList(String stack) {
        return (stack == null) ? null : Arrays.asList(stack.split(";"));
    }
}
