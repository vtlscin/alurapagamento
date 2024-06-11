package br.com.alurafood.pagamentos.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("local")
class PagamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deveria devolver 200 no listar todos")
    public void devolveCodigo200ListarTodos() throws Exception {

        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/pagamentos")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

}