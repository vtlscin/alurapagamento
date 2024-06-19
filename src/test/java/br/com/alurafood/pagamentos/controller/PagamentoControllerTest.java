package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("local")
class PagamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deveria devolver 200 no listar todos")
    @Order(1)
    public void devolveCodigo200ListarTodos() throws Exception {

        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/pagamentos")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    @DisplayName("Deveria devolver 200 no criar")
    @Order(2)
    public void devolveCodigo200Criar() throws Exception {

        PagamentoDto dto = new PagamentoDto(BigDecimal.TEN,"Thiago","12345678","10/29","123"
                ,1L,1L);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);


        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/pagamentos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    @DisplayName("Deveria devolver 200 no detalhar")
    @Order(3)
    public void devolveCodigo200Detalhar() throws Exception {

        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/pagamentos/1")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

}