package com.personalprojects.pocconsultacep.services.impl;

import com.personalprojects.pocconsultacep.entities.MunicipioInfo;
import com.personalprojects.pocconsultacep.entities.CepDetailsInfo;
import com.personalprojects.pocconsultacep.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepServiceImpl implements CepService {

    private final RestTemplate restTemplate;
    private final String viaCepBaseUrl;
    private final String ibgeApiBaseUrl;

    @Autowired
    public CepServiceImpl(RestTemplate restTemplate, @Value("${viacep.base-url}") String viaCepBaseUrl, @Value("${ibge.base-url}") String ibgeApiBaseUrl) {
        this.restTemplate = restTemplate;
        this.viaCepBaseUrl = viaCepBaseUrl;
        this.ibgeApiBaseUrl = ibgeApiBaseUrl;
    }

    @Override
    public CepDetailsInfo getCepInformation(String cep) {
        var formattedCed = cep.replace("-", "");
        var viaCepUrl = viaCepBaseUrl + formattedCed + "/json";
        var viaCepResponse = restTemplate.getForObject(viaCepUrl, CepDetailsInfo.class);

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
