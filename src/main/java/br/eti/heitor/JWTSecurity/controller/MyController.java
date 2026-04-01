package br.eti.heitor.JWTSecurity.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sesi3dia
 */
@RequestMapping("/")
public class MyController {
    
    //Apenas ADMIN pode ter acesso.
    @GetMapping("/manager")
    public Map<String, Object> privateManageEndpoint() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Manager Endpoint: Area apenas para ADMINS!!!");
        return model;
    }
    
    //Apenas logados podem ter acesso
    @GetMapping("/private")
    public Map<String, Object> privateEndpoint() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Private Endpoint: Area Restrita!");
        return model;
    }
    
    //Todos podem ter acesso
    @RequestMapping("/public")
    public Map<String, Object> publicEndpoint() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Public Endpoint: Area Publica!");
        return model;
    }
}
