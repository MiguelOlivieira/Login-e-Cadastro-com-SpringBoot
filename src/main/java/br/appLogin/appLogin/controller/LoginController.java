package br.appLogin.appLogin.controller;


import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.Cookie;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

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
    public String paginaHome() {
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
