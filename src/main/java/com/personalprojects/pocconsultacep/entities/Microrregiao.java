package com.personalprojects.pocconsultacep.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Microrregiao {

    private String nome;
    private Mesorregiao mesorregiao;
}