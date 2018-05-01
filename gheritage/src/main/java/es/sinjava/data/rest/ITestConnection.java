package es.sinjava.data.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.sinjava.data.jpa.model.domain.City;

@Path("/pub")
public interface ITestConnection {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	City test(@QueryParam("id") Long x);

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	List<City> listado();

}
