package com.github.hugovallada.forum.controller;

import com.github.hugovallada.forum.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
        System.out.println(form.getEmail()+ "   " +form.getSenha());

        return ResponseEntity.ok().build();
    }
}
