package com.andriele.aprendendospring.business;

import com.andriele.aprendendospring.infrastructure.entity.Usuario;
import com.andriele.aprendendospring.infrastructure.exceptions.ConflictException;
import com.andriele.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.andriele.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // gera um construtor somente com campos que contem o private final

public class UsuarioService {
    private final UsuarioRepository usuarioRepository; // final decla que é imutável
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario( Usuario usuario){
        try{
            emailExiste((usuario.getEmail()));
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return  usuarioRepository.save(usuario);
        } catch (ConflictException e){
            throw new ConflictException("Email já cadastrado"+ e.getCause());
        }

    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e){
            throw  new ConflictException("Email já cadastrado"+ e.getCause());
        }
    }
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);

    }

}
