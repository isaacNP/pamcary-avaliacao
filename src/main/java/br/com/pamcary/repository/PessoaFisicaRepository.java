package br.com.pamcary.repository;

import br.com.pamcary.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Short> {


    List<PessoaFisica> findByCpf(String cpf);
}
