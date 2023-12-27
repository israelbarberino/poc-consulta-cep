package com.personalprojects.pocconsultacep.controller;

import com.personalprojects.pocconsultacep.entities.CepDetailsInfo;
import com.personalprojects.pocconsultacep.entities.User;
import com.personalprojects.pocconsultacep.services.CepService;
import com.personalprojects.pocconsultacep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;
    private final UserService userService;

    @Autowired
    public CepController(CepService cepService, UserService userService) {
        this.cepService = cepService;
        this.userService = userService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepDetailsInfo> getCep(@PathVariable String cep) {
        CepDetailsInfo cepDetailsInfo = cepService.getCepInformation(cep);
        return ResponseEntity.ok(cepDetailsInfo);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

}