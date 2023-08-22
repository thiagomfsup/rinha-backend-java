package com.tmf.rinha.web.dto;

import java.util.List;

public record AddPessoaDTO(
    String nome,
    String apelido,
    String nascimento,
    List<String> stack) {
}
