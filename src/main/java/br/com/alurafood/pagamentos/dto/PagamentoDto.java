package br.com.alurafood.pagamentos.dto;

import br.com.alurafood.pagamentos.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDto {

    private Long id;

    private BigDecimal valor;

    private String nome;

    private String numero;

    private String expiracao;

    private String codigo;

    private Status status;

    private Long pedidoId;

    private Long formaDePagamentoId;

    public PagamentoDto(BigDecimal valor, String nome, String numero, String expiracao, String codigo, Long pedidoId, Long formaDePagamentoId) {
        this.valor = valor;
        this.nome = nome;
        this.numero = numero;
        this.expiracao = expiracao;
        this.codigo = codigo;
        this.pedidoId = pedidoId;
        this.formaDePagamentoId = formaDePagamentoId;
    }
}
