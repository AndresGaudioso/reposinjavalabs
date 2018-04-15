package es.sinjava.data.manager.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.sinjava.data.jpa.app.domain.Usuario;
import es.sinjava.data.repository.app.UsuarioRepository;

@Component
public class UsuarioManagerImpl {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioManagerImpl.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> getAll() {
		Iterator<Usuario> usuariosIterator = usuarioRepository.findAll().iterator();
		List<Usuario> allUsuarios = new ArrayList<>();
		while (usuariosIterator.hasNext()) {
			allUsuarios.add(usuariosIterator.next());
		}
		return allUsuarios;
	}

	@Transactional(value="transactionManagerDerby" , propagation=Propagation.REQUIRES_NEW)
	public String getToken(String username, String password) {
		Usuario usuarioEncontrado = usuarioRepository.findOne(username);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		LOG.debug("Fecha {}", date.format(formatter));
		String token = "";
		if (usuarioEncontrado.getPassword().equals(password)) {
			token = DigestUtils.md5Hex(username + password + date.format(formatter));
		}
		usuarioEncontrado.setActiveToken(token);
		usuarioEncontrado.setLastLogin(date);
		// Propagation requiredNew y me evito tener que hacer un save explicito
//		usuarioRepository.save(usuarioEncontrado);
		return token;
	}

	public Usuario findbyToken(String token) {
		LOG.debug("Init : {}" + token);
		return usuarioRepository.findByActiveToken(token);
	}

	private List<Usuario> findbyTokenNotNull() {
		LOG.debug("Init : {}");
		return usuarioRepository.findByActiveTokenNotNull();
	}

	public void resetToken() {
		LOG.debug("Init");
		List<Usuario> usuariosList = findbyTokenNotNull();
		for (Usuario usuario : usuariosList) {
			LOG.debug("Borrado  el token del usuario " + usuario.getUsername());
			if (usuario.getLastLogin() == null) {
				usuario.setActiveToken(null);
			} else {
				// Es candidato a borrarse
				usuario.setLastLogin(null);
			}
		}
		usuarioRepository.save(usuariosList);
	}

}
