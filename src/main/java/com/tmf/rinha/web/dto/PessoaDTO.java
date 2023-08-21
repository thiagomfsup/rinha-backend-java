package com.tmf.rinha.web.dto;

import java.util.List;
import java.util.UUID;

public record PessoaDTO(UUID id,
                        String nome,
                        String apelido,
                        String nascimento,
                        List<String> stack) {
}
