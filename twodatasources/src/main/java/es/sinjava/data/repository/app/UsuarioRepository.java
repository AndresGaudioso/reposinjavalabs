package es.sinjava.data.repository.app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.sinjava.data.jpa.app.domain.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findByActiveToken(String token);
	List<Usuario> findByActiveTokenNotNull();
}
