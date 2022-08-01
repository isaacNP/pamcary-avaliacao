package br.com.pamcary.controller;

import br.com.pamcary.dto.PessoaFisicaDTO;
import br.com.pamcary.dto.PessoaFisicaForm;
import br.com.pamcary.exception.DataNotFoundException;
import br.com.pamcary.service.PessoaFisicaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "PessoaFisica")
@RestController("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @ApiOperation(nickname = "findAll", value = "Busca todos os registros de pessoas fisicas")
    @GetMapping("findAll")
    public ResponseEntity<Page<PessoaFisicaDTO>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(pessoaFisicaService.findAll(page, size));
    }

    @ApiOperation(nickname = "findById", value = "Busca registro de pessoa fisica a partir do id")
    @GetMapping("findById/{id}")
    public ResponseEntity<PessoaFisicaDTO> findById(@PathVariable("id") Short codigo) throws DataNotFoundException {
        return ResponseEntity.ok(pessoaFisicaService.findById(codigo));
    }

    @ApiOperation(nickname = "deleteById", value = "Exclui registro de pessoa fisica a partir do id")
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Short codigo) throws DataNotFoundException {
        pessoaFisicaService.deleteByid(codigo);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(nickname = "create", value = "Insere um novo registro de pessoa fisica")
    @PostMapping("create")
    public ResponseEntity<Object> create(@Valid @RequestBody PessoaFisicaForm pessoaFisicaForm) {
        pessoaFisicaService.create(pessoaFisicaForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(nickname = "update", value = "Altera um registro de pessoa fisica já existente")
    @PutMapping("update")
    public ResponseEntity<Object> update(@Valid @RequestBody PessoaFisicaForm pessoaFisicaForm) throws DataNotFoundException {
        pessoaFisicaService.update(pessoaFisicaForm);
        return ResponseEntity.noContent().build();
    }

}
