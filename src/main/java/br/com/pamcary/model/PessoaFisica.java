package br.com.pamcary.model;


import br.com.pamcary.dto.PessoaFisicaForm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_jpa_sequence_generator")
    @SequenceGenerator(name = "person_jpa_sequence_generator", sequenceName = "sequence_pessoa_fisica", allocationSize = 1)
    private Short codigo;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    private LocalDate dataNascimento;

    public PessoaFisica(PessoaFisicaForm pessoaFisicaForm) {
        this.codigo = pessoaFisicaForm.getCodigo();
        this.nome = pessoaFisicaForm.getNome();
        this.cpf = pessoaFisicaForm.getCpf();
        this.dataNascimento = pessoaFisicaForm.getDataNascimento();
    }
}
