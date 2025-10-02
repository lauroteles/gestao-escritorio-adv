package com.gestaoescritorio.gestao.controller;

import com.gestaoescritorio.gestao.dto.AuthRequest;
import com.gestaoescritorio.gestao.entity.User;
import com.gestaoescritorio.gestao.repository.UserRepository;
import com.gestaoescritorio.gestao.service.PasswordConfig;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class UsuariosController {


    private final UserRepository userRepo;

    private final PasswordConfig passwordConfig;


    public UsuariosController(UserRepository userRepo, PasswordConfig passwordConfig ) {
        this.passwordConfig = passwordConfig;
        this.userRepo = userRepo;
    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if ( userRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuario ja existe");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordConfig.passwordEncoder().encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado!");
    }

    @GetMapping("user-name")
    public ResponseEntity<?> retornarNomeUsuario(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nome = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(nome);
    }
}
