package com.virat.fetchsecrets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {
    
    @Autowired
    private VaultTemplate vaultTemplate;

    @GetMapping("/creds")
    public String getCreds(){
        VaultResponse response = vaultTemplate.read("secret/data/vault-application");
        ObjectMapper objectMapper = new ObjectMapper();
        Credential credential = objectMapper.convertValue(response.getData().get("data"), Credential.class);
        String username = credential.getUsername();
        String password = credential.getPassword();
        return username+password;
    }
}
