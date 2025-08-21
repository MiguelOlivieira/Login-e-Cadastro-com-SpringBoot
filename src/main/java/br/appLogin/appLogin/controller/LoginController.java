package br.appLogin.appLogin.controller;


import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class LoginController {

    private UsuarioRepository ur;


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
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if(usuarioLogado!=null){
            CookieService.setCookie(response, "usuarioId", String.valueOf(usuarioLogado.getId()), 10000);
            CookieService.setCookie(response, "nomeUsuario", String.valueOf(usuarioLogado.getNome()), 10000);

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
    public String cadastro() {
        return "telaCadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {

        if(result.hasErrors()) {
            return "redirect:/cadastro";
        }
        ur.save(usuario);

        return "redirect:/login";
    }

}
