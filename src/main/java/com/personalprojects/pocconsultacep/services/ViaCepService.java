package com.personalprojects.pocconsultacep.services;

import com.personalprojects.pocconsultacep.dtos.ViaCepResponse;

public interface ViaCepService {

    ViaCepResponse getCepInformation(String cep) throws RuntimeException;
}
