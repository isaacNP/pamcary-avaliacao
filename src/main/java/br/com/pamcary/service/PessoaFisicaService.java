package br.com.pamcary.service;

import br.com.pamcary.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
}
