package br.com.pamcary.service;

import br.com.pamcary.dto.PessoaFisicaDTO;
import br.com.pamcary.dto.PessoaFisicaForm;
import br.com.pamcary.exception.BusinessException;
import br.com.pamcary.exception.DataNotFoundException;
import br.com.pamcary.model.PessoaFisica;
import br.com.pamcary.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    private final String regex = "\\d+";

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

    public void create(PessoaFisicaForm pessoaFisicaForm) {

        pessoaFisicaRepository.save(new PessoaFisica(pessoaFisicaForm));
    }

    public void update(PessoaFisicaForm pessoaFisicaForm) throws DataNotFoundException {

        if(Objects.isNull(pessoaFisicaForm.getCodigo()) || !pessoaFisicaRepository.existsById(pessoaFisicaForm.getCodigo())){
            throw new DataNotFoundException("Registro não encontrado");
        }

        pessoaFisicaRepository.save(new PessoaFisica(pessoaFisicaForm));
    }

    public List<PessoaFisicaDTO> findByCPF(String cpf) throws BusinessException {

        if(cpf.length() != 11 || !cpf.matches(regex)) {
            throw new BusinessException("CPF inválido");
        }

        return pessoaFisicaRepository.findByCpf(cpf).stream().map(PessoaFisicaDTO::new).collect(Collectors.toList());
    }
}
