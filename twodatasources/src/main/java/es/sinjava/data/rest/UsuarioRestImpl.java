package es.sinjava.data.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sinjava.data.jpa.app.domain.Usuario;
import es.sinjava.data.manager.app.UsuarioManagerImpl;

@Component
public class UsuarioRestImpl implements IUsuarioRest {
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioRestImpl.class);

	@Autowired
	UsuarioManagerImpl usuariosManagerImpl;

	@Override
	public List<Usuario> usuariosList() {
		return usuariosManagerImpl.getAll();
	}

	@Override
	public String loginToken(String username, String password) {
		LOG.debug("Init {}  {}", username, password);
		return usuariosManagerImpl.getToken(username, password);
	}

	@Override
	public Usuario usuariobyToken(String token) {
		LOG.debug("Init {}", token);
		return usuariosManagerImpl.findbyToken(token);
	}

}
