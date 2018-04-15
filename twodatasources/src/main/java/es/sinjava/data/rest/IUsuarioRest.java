package es.sinjava.data.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.sinjava.data.jpa.app.domain.Usuario;

@Path("/users")
public interface IUsuarioRest {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	List<Usuario> usuariosList();

	@GET
	@Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	Usuario usuariobyToken(@PathParam("token") String token);

	@POST
	@Path("/{username}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	String loginToken(@PathParam("username") String username, @FormParam("password") String password);

}
