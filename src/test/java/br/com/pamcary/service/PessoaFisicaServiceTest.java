package br.com.pamcary.service;

import br.com.pamcary.dto.PessoaFisicaDTO;
import br.com.pamcary.dto.PessoaFisicaForm;
import br.com.pamcary.exception.BusinessException;
import br.com.pamcary.exception.DataNotFoundException;
import br.com.pamcary.model.PessoaFisica;
import br.com.pamcary.repository.PessoaFisicaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class PessoaFisicaServiceTest {

    @InjectMocks
    private PessoaFisicaService pessoaFisicaService;

    @Mock
    private PessoaFisica pessoaFisica;

    @Mock
    private PessoaFisicaForm pessoaFisicaForm;

    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;

    private final static String VALID_CPF = "54213325687";
    private final static String INVALID_CPF_REGEX = "5421332568x";
    private final static String INVALID_CPF_LENGTH = "5421332568";

    @Test
    @DisplayName("findAll OK")
    void findAllOk() {

        Pageable paging = mock(Pageable.class);

        List<PessoaFisica> pessoaFisicaList = IntStream.range(0,2).mapToObj(value -> pessoaFisica).collect(Collectors.toList());

        Page<PessoaFisica> findAll = new PageImpl<>(pessoaFisicaList.subList(0, 2), paging, pessoaFisicaList.size());

        when(pessoaFisicaRepository.findAll(any(Pageable.class))).thenReturn(findAll);

        Page<PessoaFisicaDTO> pageDto = pessoaFisicaService.findAll(0, 10);

        verify(pessoaFisicaRepository, Mockito.times(1)).findAll(any(Pageable.class));

        assertEquals(pageDto.stream().count(), findAll.stream().count());
    }

    @Test
    @DisplayName("findById OK")
    void findById() throws DataNotFoundException {

        Optional<PessoaFisica> findOne = Optional.of(pessoaFisica);

        when(pessoaFisicaRepository.findById(anyShort())).thenReturn(findOne);

        assertNotNull(pessoaFisicaService.findById(anyShort()));
    }

    @Test
    @DisplayName("findById DataNotFoundException")
    void findByIdDataNotFound() {

        Optional<PessoaFisica> findOne = Optional.empty();
        when(pessoaFisicaRepository.findById(anyShort())).thenReturn(findOne);

        assertThrows(
                DataNotFoundException.class, () -> pessoaFisicaService.findById(anyShort())
        );
    }

    @Test
    @DisplayName("deleteById OK")
    void deleteById() throws DataNotFoundException {
        Optional<PessoaFisica> findOne = Optional.of(pessoaFisica);
        when(pessoaFisicaRepository.findById(anyShort())).thenReturn(findOne);

        pessoaFisicaService.deleteByid(anyShort());

        verify(pessoaFisicaRepository, Mockito.times(1)).delete(any());
    }

    @Test
    @DisplayName("deleteById DataNotFoundException")
    void deleteByIdDataNotFound() {

        Optional<PessoaFisica> findOne = Optional.empty();
        when(pessoaFisicaRepository.findById(anyShort())).thenReturn(findOne);

        assertThrows(
                DataNotFoundException.class, () -> pessoaFisicaService.deleteByid(anyShort())
        );
    }

    @Test
    @DisplayName("Create OK")
    void create() {

        pessoaFisicaService.create(pessoaFisicaForm);
        verify(pessoaFisicaRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Update OK")
    void update() throws DataNotFoundException {

        when(pessoaFisicaRepository.existsById(anyShort())).thenReturn(true);

        pessoaFisicaService.update(pessoaFisicaForm);

        verify(pessoaFisicaRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Update DataNotFoundException Objects.isNull(getCodigo())")
    void updateObjectNull()  {

        when(pessoaFisicaForm.getCodigo()).thenReturn(null);
        when(pessoaFisicaRepository.existsById(anyShort())).thenReturn(true);

        assertThrows(
                DataNotFoundException.class, () -> pessoaFisicaService.update(pessoaFisicaForm)
        );

    }

    @Test
    @DisplayName("Update DataNotFoundException !pessoaFisicaRepository.existsById(getCodigo())")
    void updateDataNotExists()  {

        when(pessoaFisicaRepository.existsById(anyShort())).thenReturn(false);

        assertThrows(
                DataNotFoundException.class, () -> pessoaFisicaService.update(pessoaFisicaForm)
        );

    }

    @Test
    @DisplayName("findByCpf OK")
    void findByCpf() throws BusinessException {

        List<PessoaFisica> pessoaFisicaList = IntStream.range(0,2).mapToObj(value -> pessoaFisica).collect(Collectors.toList());


        when(pessoaFisicaRepository.findByCpf(anyString())).thenReturn(pessoaFisicaList);

        assertNotNull(pessoaFisicaService.findByCPF(VALID_CPF));
    }

    @Test
    @DisplayName("findByCpf BusinessExeption !cpf.matches(regex)")
    void findByCpfRegex() {

        assertThrows(
                BusinessException.class, () -> pessoaFisicaService.findByCPF(INVALID_CPF_REGEX)
        );
    }

    @Test
    @DisplayName("findByCpf BusinessExeption cpf.length() != 11")
    void findByCpfLength() {

        assertThrows(
                BusinessException.class, () -> pessoaFisicaService.findByCPF(INVALID_CPF_LENGTH)
        );
    }
}