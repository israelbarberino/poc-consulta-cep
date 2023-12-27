package com.personalprojects.pocconsultacep.services.impl;

import com.personalprojects.pocconsultacep.dtos.MunicipioInfo;
import com.personalprojects.pocconsultacep.dtos.ViaCepResponse;
import com.personalprojects.pocconsultacep.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepServiceImpl implements ViaCepService {

    private final RestTemplate restTemplate;
    private final String viaCepBaseUrl;
    private final String ibgeApiBaseUrl;

    @Autowired
    public ViaCepServiceImpl(RestTemplate restTemplate, @Value("${viacep.base-url}") String viaCepBaseUrl, @Value("${ibge.base-url}") String ibgeApiBaseUrl) {
        this.restTemplate = restTemplate;
        this.viaCepBaseUrl = viaCepBaseUrl;
        this.ibgeApiBaseUrl = ibgeApiBaseUrl;
    }

    @Override
    public ViaCepResponse getCepInformation(String cep) {
        var formattedCed = cep.replace("-", "");
        var viaCepUrl = viaCepBaseUrl + formattedCed + "/json";
        var viaCepResponse = restTemplate.getForObject(viaCepUrl, ViaCepResponse.class);

        if (viaCepResponse == null) throw new RuntimeException ("CEP não encontrado");

        var ibge = viaCepResponse.getIbge();
        var municipioInfo = obterInformacoesAdicionaisDoMunicipio(ibge);

        viaCepResponse.setMunicipioInfo(municipioInfo);

        return viaCepResponse;
    }

    private MunicipioInfo obterInformacoesAdicionaisDoMunicipio(String ibge) {
        var ibgeApiUrl = ibgeApiBaseUrl + "/localidades/municipios/" + ibge;
        return restTemplate.getForObject(ibgeApiUrl, MunicipioInfo.class);
    }
}
