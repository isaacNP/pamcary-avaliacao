package br.com.pamcary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@ApiModel(value = "PessoaFisicaForm")
public class PessoaFisicaForm {

    @ApiModelProperty(notes = "Utilizar somente quando for atualizar entidade Pessoa Fisica", example = "4")
    private Short codigo;

    @NotEmpty(message = "Variável nome não pode estar nula ou vazia")
    @Size(max = 60, message = "Variável nome não pode conter mais que 60 caracteres")
    @ApiModelProperty(example = "José da Silva")
    private String nome;

    @NotEmpty(message = "Variável cpf não pode estar nula ou vazia")
    @Size(min = 11, max = 11, message = "Variável cpf deve conter 11 caracteres")
    @ApiModelProperty(example = "50652237002")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(example = "01/01/2001")
    private LocalDate dataNascimento;

}
