package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findById(long Id);

    @Query(value="SELECT * FROM usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    public Usuario login(String email, String senha);
}
