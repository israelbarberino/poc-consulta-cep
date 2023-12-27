package com.personalprojects.pocconsultacep.services.impl;

import com.personalprojects.pocconsultacep.entities.User;
import com.personalprojects.pocconsultacep.repository.UserRepository;
import com.personalprojects.pocconsultacep.services.CepService;
import com.personalprojects.pocconsultacep.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CepService cepService;

    public UserServiceImpl(UserRepository userRepository, CepService cepService) {
        this.userRepository = userRepository;
        this.cepService = cepService;
    }

    public User createUser(User user) {
        var cep = user.getCep();
        var numero = user.getNumero();
        if (cep == null || cep.isEmpty()) {
            throw new RuntimeException("CEP is required");
        }
        if (numero == null || numero.isEmpty()) {
           numero = "S/N";
        }
        try {
            var cepDetailsInfo = cepService.getCepInformation(cep);
            user.setLogradouro(cepDetailsInfo.getLogradouro());
            user.setNumero(numero);
            user.setComplemento(cepDetailsInfo.getComplemento());
            user.setBairro(cepDetailsInfo.getBairro());
            user.setCidade(cepDetailsInfo.getLocalidade());
            user.setUf(cepDetailsInfo.getUf());
        } catch (Exception e) {
            throw new RuntimeException("CEP not found");
        }
        return userRepository.save(user);
    }
}