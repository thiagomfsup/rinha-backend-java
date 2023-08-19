package com.tmf.rinha.web;

import com.tmf.rinha.web.dto.AddPessoaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    @PostMapping
    public ResponseEntity<?> addPessoa(@Valid @RequestBody AddPessoaDTO addPessoaDTO) {
        final UUID id = null;
        var uri = URI.create("/pessoas/" + id);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> retrievePessoa(@PathVariable UUID uuid) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> retrievePessoaByQuery(@RequestParam(name = "t", required = true) String query) {
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
