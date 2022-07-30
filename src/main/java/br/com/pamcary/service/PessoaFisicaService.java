package br.com.pamcary.service;

import br.com.pamcary.dto.PessoaFisicaDTO;
import br.com.pamcary.exception.DataNotFoundException;
import br.com.pamcary.model.PessoaFisica;
import br.com.pamcary.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public Page<PessoaFisicaDTO> findAll(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        return pessoaFisicaRepository.findAll(pageable).map(PessoaFisicaDTO::new);
    }

    public PessoaFisicaDTO findById(Short codigo) throws DataNotFoundException {

        Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(codigo);

        if(!pessoaFisica.isPresent()){
            throw new DataNotFoundException("Registro não encontrado");
        }

        return new PessoaFisicaDTO(pessoaFisica.get());
    }

    public void deleteByid(Short codigo) throws DataNotFoundException {

        Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(codigo);

        if(!pessoaFisica.isPresent()){
            throw new DataNotFoundException("Registro não encontrado");
        }

        pessoaFisicaRepository.delete(pessoaFisica.get());
    }
}
