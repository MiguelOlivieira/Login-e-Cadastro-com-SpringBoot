package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiController {


    private final UsuarioRepository ur;
    private final PasswordEncoder encoder;

    @Autowired
    public UsuarioApiController(UsuarioRepository ur, PasswordEncoder encoder ){
        this.ur = ur;
        this.encoder = encoder;
    }


    @GetMapping("/listarTodos")
    public ResponseEntity<List<Usuario>>listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(ur.findAll());

    }

    @GetMapping("/buscarUsuario/{id}")
    Optional<Usuario> findById(@PathVariable("id") Long id){

        if(ur.findById(id).isEmpty()) {
            System.out.println("User not Found");
        }
        return ur.findById(id);
    }


    @GetMapping("/teste")
    public String teste() {
        System.out.println("Método teste foi chamado!");
        return "API está funcionando";
    }

    /*@GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String email,
                                                @RequestParam String senha) {

        Optional<Usuario> optUsuario = ur.findByEmail(email);

        if(optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = optUsuario.get();

        boolean valid = encoder.matches(senha, usuario.getSenha());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);

    }

     */
}


