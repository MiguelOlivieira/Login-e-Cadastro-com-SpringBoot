package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiController {

    @Autowired
    private UsuarioRepository ur;

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
}


