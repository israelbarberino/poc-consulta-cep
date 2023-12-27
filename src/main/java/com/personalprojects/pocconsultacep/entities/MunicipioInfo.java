package com.personalprojects.pocconsultacep.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipioInfo {

    private String nome;
    private Microrregiao microrregiao;
}