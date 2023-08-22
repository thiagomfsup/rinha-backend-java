package com.tmf.rinha.web;

import com.tmf.rinha.service.PessoaService;
import com.tmf.rinha.web.dto.AddPessoaDTO;
import com.tmf.rinha.web.dto.PessoaDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/pessoas")
    public ResponseEntity<?> addPessoa(@RequestBody AddPessoaDTO addPessoaDTO) {
        final String nome = addPessoaDTO.nome();
        final String apelido = addPessoaDTO.apelido();
        final String nascimento = addPessoaDTO.nascimento();
        final List<String> stack = addPessoaDTO.stack();

        if (nome == null || nome.isBlank() || nome.length() > 100 ||
            apelido == null || apelido.isBlank() || apelido.length() > 32 ||
            nascimento == null || nascimento.isBlank() ||
            (stack != null && stack.stream().anyMatch(s -> s.length() > 32) )) {

            return ResponseEntity.unprocessableEntity().build();
        }

        var uri = URI.create("/pessoas/" + pessoaService.addPessoa(addPessoaDTO));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/pessoas/{uuid}")
    public ResponseEntity<?> retrievePessoa(@PathVariable UUID uuid) {
        return ResponseEntity.of(pessoaService.retrieveById(uuid));
    }

    @GetMapping("/pessoas")
    public List<PessoaDTO> retrievePessoaByQuery(@RequestParam(name = "t") String queryTerm) {
        return pessoaService.retrieveByQueryTerm(queryTerm);
    }

    @GetMapping("/contagem-pessoas")
    public long countPessoas() {
        return pessoaService.countPessoas();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ MethodArgumentNotValidException.class, DataIntegrityViolationException.class })
    public void handleValidationExceptions(Exception ex) {
    }
}
