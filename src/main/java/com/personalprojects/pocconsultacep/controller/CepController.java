package com.personalprojects.pocconsultacep.controller;

import com.personalprojects.pocconsultacep.dtos.ViaCepResponse;
import com.personalprojects.pocconsultacep.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final ViaCepService viaCepService;

    @Autowired
    public CepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> getCep(@PathVariable String cep) {
        ViaCepResponse viaCepResponse = viaCepService.getCepInformation(cep);
        return ResponseEntity.ok(viaCepResponse);
    }
}