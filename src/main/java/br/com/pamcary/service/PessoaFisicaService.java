package br.com.pamcary.service;

import br.com.pamcary.dto.PessoaFisicaDTO;
import br.com.pamcary.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public Page<PessoaFisicaDTO> findAll(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        return pessoaFisicaRepository.findAll(pageable).map(pessoaFisica -> new PessoaFisicaDTO(pessoaFisica));
    }
}
