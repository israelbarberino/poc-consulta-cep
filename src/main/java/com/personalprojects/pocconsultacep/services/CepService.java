package com.personalprojects.pocconsultacep.services;

import com.personalprojects.pocconsultacep.entities.CepDetailsInfo;

public interface CepService {

    CepDetailsInfo getCepInformation(String cep) throws RuntimeException;
}
