package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.flywaydb.core.Flyway;
import org.h2.tools.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.SQLException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("local")
class PagamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    public static void initTest(@Autowired Flyway flyway) throws SQLException {
        flyway.clean();
        flyway.migrate();
//        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
//                .start();
    }

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

    @Test
    @DisplayName("Deveria devolver 200 no criar")
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
    public void devolveCodigo200Detalhar() throws Exception {

        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/pagamentos/2")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    @DisplayName("Deveria devolver 200 no atualizar")
    public void devolveCodigo200Atualizar() throws Exception {

        PagamentoDto dto = new PagamentoDto(BigDecimal.TEN,"Thiago","12345678","10/29","123"
                , Status.CANCELADO,1L,1L);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);


        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .put("/pagamentos/1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    @DisplayName("Deveria devolver 200 no remover")
    public void devolveCodigo200Remover() throws Exception {

        //ARRANGE + ACT
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/pagamentos/1")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

    }

}