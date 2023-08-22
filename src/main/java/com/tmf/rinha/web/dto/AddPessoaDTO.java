package com.tmf.rinha.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AddPessoaDTO(
    @NotBlank(message = "Nome não pode ser null ou vazio")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    String nome,
    @NotBlank(message = "Apelido não pode ser null ou vazio")
    @Size(max = 32, message = "Apelido deve ter no máximo 32 caracteres")
    String apelido,
    @NotBlank(message = "data de nascimento deve ser informada")
    String nascimento,
    // TODO validations
    List<@NotBlank @Size(max = 32) String> stack) {
}
