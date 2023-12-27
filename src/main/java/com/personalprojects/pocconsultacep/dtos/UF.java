package com.personalprojects.pocconsultacep.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UF {

    private int id;
    private String sigla;
    private String nome;
    private Regiao regiao;
}