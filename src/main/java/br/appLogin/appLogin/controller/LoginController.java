package br.appLogin.appLogin.controller;


import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    private UsuarioRepository ur;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public LoginController(UsuarioRepository ur){
        this.ur = ur;
    }



    @GetMapping("/login") //identifica o /login no endereço e retorna pagina de login (telaLogin.html)
    public String login() {
        return "telaLogin";
    }

    @GetMapping("/")
    public String paginaHome(Model model,  HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome",  CookieService.getCookie(request, "nomeUsuario"));
        return "index";
    }

    @PostMapping("/logar")
    public String loginUsuario(@RequestParam String senha,
                               @RequestParam String email,
                               Model model,
                               HttpServletResponse response
                               ) throws UnsupportedEncodingException {


        Optional<Usuario> optUsuario = ur.findByEmail(email);

        if(optUsuario.isEmpty()) {
            return("telaLogin");
        }

        Usuario usuario = optUsuario.get();


        boolean valid = encoder.matches(senha, usuario.getSenha());


        if(valid){
            CookieService.setCookie(response, "usuarioId", String.valueOf(usuario.getId()), 10000);
            CookieService.setCookie(response, "nomeUsuario", String.valueOf(usuario.getNome()), 10000);
            return "redirect:/";
        }

        model.addAttribute("erro", "Usuario Inválido");
        return("telaLogin");
    }


    @GetMapping("/sair")
    public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
            CookieService.setCookie(response, "usuarioId", "",0);

            return "telaLogin";
        }



    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());

        return "telaCadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {

        if(result.hasErrors()) {
            return "redirect:/cadastro";
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        ur.save(usuario);

        return "redirect:/login";
    }

}
