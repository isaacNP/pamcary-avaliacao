package br.com.pamcary.dto;

import br.com.pamcary.model.PessoaFisica;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Getter
@ApiModel(value = "PessoaFisicaDTO")
public class PessoaFisicaDTO {

    private Short codigo;

    private String nome;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public PessoaFisicaDTO(PessoaFisica pessoaFisica) {
        this.codigo = pessoaFisica.getCodigo();
        this.nome = pessoaFisica.getNome();
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
    }
}
