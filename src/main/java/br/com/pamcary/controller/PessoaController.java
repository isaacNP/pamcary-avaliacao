package br.com.pamcary.controller;

import br.com.pamcary.dto.PessoaFisicaDTO;
import br.com.pamcary.service.PessoaFisicaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "PessoaFisica")
@RestController
public class PessoaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @ApiOperation(nickname = "findAll", value = "busca todos os registros de pessoas fisicas")
    @GetMapping("findAll")
    public ResponseEntity<Page<PessoaFisicaDTO>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(pessoaFisicaService.findAll(page, size));
    }

}
