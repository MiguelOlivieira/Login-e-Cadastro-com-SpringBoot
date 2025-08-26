package br.appLogin.appLogin.service.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {
    //evita que o usuario fique em looping no login caso ele não tenha cadastro


    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/login",
                "/logar",
                "/error",
                "/cadastro",
                "/api/usuarios/**"

        );
    }

}
