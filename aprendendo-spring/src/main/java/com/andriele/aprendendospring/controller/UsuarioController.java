package com.andriele.aprendendospring.controller;

import com.andriele.aprendendospring.business.UsuarioService;
import com.andriele.aprendendospring.controller.dtos.UsuarioDTO;
import com.andriele.aprendendospring.infrastructure.entity.Usuario;
import com.andriele.aprendendospring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController // indica que essa classe é a nossa controller
@RequestMapping("/usuario")// aponta qual é a nossa URI da nossa controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario>salvaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario)) ;
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                    usuarioDTO.getSenha())
                );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }
    @GetMapping
    public ResponseEntity<Usuario> buscausuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
