package com.personalprojects.pocconsultacep.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Cep {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

}
