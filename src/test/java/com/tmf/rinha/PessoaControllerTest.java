package com.tmf.rinha;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddPessoaWhenDataIsValid() throws Exception {
        final var validPessoa = """
            {
                "apelido" : "josé",
                "nome" : "José Roberto",
                "nascimento" : "2000-10-01",
                "stack" : ["C#", "Node", "Oracle"]
            }
            """;

        performPessoaPost(validPessoa).andExpectAll(
            MockMvcResultMatchers.status().isCreated(),
            MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION),
            MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, Matchers.matchesPattern("^/pessoas/[\\w-]*$"))); // TODO regex for validate uuid
    }

    @Test
    public void shouldNotAddPessoaWhenNomeIsNull() throws Exception {
        final var invalidPessoa = """
            {
                "apelido" : "ana",
                "nome" : null,
                "nascimento" : "1985-09-23",
                "stack" : null
            }
            """;

        performPessoaPost(invalidPessoa).andExpectAll(
            MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void shouldNotAddPessoaWhenNomeExceedsMaxNumberOfCharacters() throws Exception {
        final var invalidPessoa = """
            {
                "apelido" : "ana",
                "nome" : "123456789A123456789B123456789C123456789D123456789E123456789F123456789G123456789H123456789I123456789J_MoreThan100Chars",
                "nascimento" : "1985-09-23",
                "stack" : null
            }
            """;

        performPessoaPost(invalidPessoa).andExpectAll(
            MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void shouldNotAddPessoaWhenStackValueExceedsMaxNumberOfCharacters() throws Exception {
        final var invalidPessoa = """
            {
                "apelido" : "ana",
                "nome" : "Ana Bolena",
                "nascimento" : "1985-09-23",
                "stack" : ["123456789A123456789B123456789C12_MoreThan32Chars"]
            }
            """;

        performPessoaPost(invalidPessoa).andExpectAll(
            MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void shouldRetrievePessoaWhenAValidIdIsGiven() throws Exception {
        final var id = UUID.fromString("b9d8c7db-d26c-4e35-9b54-414166ed2f30");
        final var expectedPessoa = """
            {
                "id" : %s,
                "apelido" : "catarina",
                "nome" : "Catarina de Aragão",
                "nascimento" : "1485-12-15",
                "stack" : null
            }
            """.formatted(id);

        performPessoaGet(id.toString()).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().json(expectedPessoa)
        );
    }

    @Test
    public void shouldReturnNotFoundStatusWhenGivenIdDoesNotIdentifyAPessoa() throws Exception {
        final var id = UUID.randomUUID();
        performPessoaGet(id.toString()).andExpectAll(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void shouldReturnPessoasWhenSearchingByTerms() throws Exception {
        performPessoaGetByTerm("catarina").andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))
        );

        performPessoaGetByTerm("racle").andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1))
        );
    }

    @Test
    public void shouldReturnOkStatusEvenThoughNoPessoaIsRetrievedWhenSearchingByTerm() throws Exception {
        performPessoaGetByTerm("NonExistingParam").andExpectAll(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void shouldReturnBadRequestStatusWhenQueryTermIsMissed() throws Exception {
        performPessoaGetOmittingQueryParam().andExpect(
            MockMvcResultMatchers.status().isBadRequest()
        );
    }

    private ResultActions performPessoaPost(String withBody) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
            .post("/pessoas")
            .content(withBody)
            .contentType(MediaType.APPLICATION_JSON));
    }

    private ResultActions performPessoaGet(String withId) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
            .get("/pessoas/{id}", withId));
    }

    private ResultActions performPessoaGetByTerm(String term) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
            .get("/pessoas")
            .queryParam("t", term));
    }

    private ResultActions performPessoaGetOmittingQueryParam() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
            .get("/pessoas"));
    }
}
