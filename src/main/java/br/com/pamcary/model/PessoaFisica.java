package br.com.pamcary.model;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_jpa_sequence_generator")
    @SequenceGenerator(name = "person_jpa_sequence_generator", sequenceName = "sequence_pessoa_fisica")
    private Short codigo;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    private LocalDate dataNascimento;

}
