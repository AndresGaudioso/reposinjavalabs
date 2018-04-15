package es.sinjava.data;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.sinjava.data.jpa.app.domain.Usuario;
import es.sinjava.data.manager.app.UsuarioManagerImpl;

/**
 * Interceptor para controlar de forma transparente lo que entra: temas de
 * seguridad /etc etc
 * 
 * @author andgau@gmail.com
 *
 */
@EnableAsync
@EnableScheduling
@Component
public class InInterceptor extends AbstractPhaseInterceptor<Message> {

	private static final Logger LOG = LoggerFactory.getLogger(InInterceptor.class);

	private AtomicLong accessCounter = new AtomicLong(0);

	@Autowired
	private UsuarioManagerImpl usuarioManagerImpl;

	public InInterceptor() {
		super(Phase.READ);
	}

	public InInterceptor(String phase) {
		super(phase);
	}

	public void handleMessage(Message message) {

		LOG.debug("------ A la escucha el filtro {} ------------------------", accessCounter.getAndIncrement());
		String localeQuery = (String) message.get(Message.QUERY_STRING);
		LOG.debug("La consultad de la query :" + localeQuery);

		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
		for (String head : headers.keySet()) {
			// me llega en la cabera lo que quiero en el sitio apropiado
			// else throw ChuckNorrisException
			// LOG.debug("Cabecera : " + head);
		}
		String endpointURL = (String) message.get(Message.PATH_INFO);
		LOG.debug("Este es el metodo llamado :" + endpointURL);

		// Si contiene la petición de login adelante
		if (endpointURL.startsWith("/legacy/users/")) {
			LOG.debug("Petición de login");
			return;
		}

		if (headers.containsKey("token")) {
			String token = headers.get("token").get(0);
			LOG.debug("Token pasado :" + token);
			Usuario usuario = usuarioManagerImpl.findbyToken(token);
			// Si no existe el usuario con el token activo
			if (usuario == null) {
				LOG.debug("no encontrado el usuario logueado");
				Response response = Response.status(Status.UNAUTHORIZED).build();
				throw new NotAuthorizedException(response);
			}
		} else {
			// ni siquiera contiene el token
			LOG.debug("No se ha pasado el token");
			Response response = Response.status(Status.UNAUTHORIZED).build();
			throw new NotAuthorizedException(response);
		}

		LOG.debug("Consulta final {}", message.get(Message.QUERY_STRING));
		Exchange exchange = message.getExchange();
		for (Entry<String, Object> entry : exchange.entrySet()) {
			LOG.debug(entry.getKey() + "  - " + entry.getValue().toString());
		}
	}

	@PostConstruct
	public void listening() {
		LOG.debug("------ A la escucha el filtro {} ---------", accessCounter.getAndIncrement());
	}

	/**
	 * Se encarga de gestionar los tokens
	 */
	@Scheduled(fixedDelay = 1200000)
	public void updateTokens() {
		LOG.debug("Init {}", "actualizando el token");
		usuarioManagerImpl.resetToken();
	}

}
